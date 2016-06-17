/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.data;

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import com.magazynhibernate.dao.OdpadDao;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Collectors;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import lombok.*;

/**
 *
 * @author xxbar
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class Magazynp implements Serializable {

    @Id
//    @GeneratedValue
    private Long ID;

    @Embedded
    private NumerKarty NR_KARTY;

    @OneToOne
    @JoinColumn(name = "ODPAD_ID")
    private Odpad ODPAD;

    private Integer NR_KLIENTA;
    private Integer FIRMA;
    private String JEDN;
    private Double MASA;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date DATAD;
    @Getter
    private static String[] propTym = {"NR_MAG", "NR_KARTY", "ODPAD", "NR_KLIENTA", "FIRMA", "JEDN", "MASA", "DATAD"};
    @Getter
    private static String[] colTym = {"NR_MAG", "NR_KARTY", "ODPAD", "NR_KLIENTA", "FIRMA", "JEDN", "MASA", "DATAD"};
    private static DateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy");

//    public Magazynp(String NR_MAG, String NR_KARTY, String NR_ODPADU, String NR_KLIENTA, String FIRMA, String JEDN, String MASA, String DATAD) {
//        ID = 0l;
//        this.NR_MAG = Integer.parseInt(NR_MAG.replaceAll("[^\\d.]", ""));
//
//        String[] fields1 = NR_KARTY.split("/");
//        this.NR_KARTY = new NumerKarty(Integer.parseInt(fields1[0]), Integer.parseInt(fields1[1]), Integer.parseInt(fields1[2]));
//
//        this.NR_ODPADU = Integer.parseInt(NR_ODPADU.replaceAll("[^\\d.]", ""));
//        this.NR_KLIENTA = Integer.parseInt(NR_KLIENTA.replaceAll("[^\\d.]", ""));
//        this.FIRMA = Integer.parseInt(FIRMA.replaceAll("[^\\d.]", ""));
//        this.JEDN = JEDN;
//        this.MASA = Double.parseDouble(MASA.replaceAll("[^\\d.]", ""));
//        try {
//            this.DATAD = new Date(DATE_FORMAT.parse(DATAD).getTime());
//        } catch (ParseException ex) {
//            Logger.getLogger(Magazynp.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("Błąd: " + ex);
//        }
//    }
    public Magazynp(String row, List<Odpad> odpady) {

        Scanner scan = new Scanner(row).useDelimiter(";");

        ID = scan.hasNextLong() ? scan.nextLong() : null;
        if (ID == null) {
            scan.next();
        }
        NR_KARTY = new NumerKarty(scan.hasNext() ? scan.next() : null);
        if (NR_KARTY == null) {
            scan.next();
        }

        Long NR_ODPADU = scan.hasNextLong() ? scan.nextLong() : null;
        if (NR_ODPADU != null) {
//            this.ODPAD = odpady.stream().filter(o -> Objects.equals(o.getID(), NR_ODPADU)).findAny().orElse(null);
               this.ODPAD = OdpadDao.getInstance().findOne(NR_ODPADU);
        }
        System.out.println("ODPAD: "+ODPAD);
//        ODPAD = null;
        if (ODPAD == null) {
            scan.next();
        }

        NR_KLIENTA = scan.hasNextInt() ? scan.nextInt() : null;
        if (NR_KLIENTA == null) {
            scan.next();
            
        }
        FIRMA = scan.hasNextInt() ? scan.nextInt() : null;
        if (FIRMA == null) {
            scan.next();
        }
        JEDN = scan.hasNext() ? scan.next() : null;
        if (JEDN == null) {
            scan.next();
        }
        this.MASA = scan.hasNext() ? Double.parseDouble(scan.next().replaceAll("[^\\d.]", "")) : null;
//        this.MASA = scan.hasNext() ? scan.nextDouble() : null;
        if (MASA == null) {
            scan.next();
        }
        try {
            this.DATAD = scan.hasNext() ? new Date(DATE_FORMAT.parse(scan.next()).getTime()) : null;
        } catch (ParseException ex) {
            this.DATAD = null;
            System.out.println("Błąd: " + ex);
        }
    }

    public static List<Magazynp> Open(Path pathMagazyn, List<Odpad> listOdpad) {
        try {

            List<String> lines = Files.readAllLines(pathMagazyn, StandardCharsets.ISO_8859_1);

            String firstLine = lines.remove(0);
            return lines.stream().map(row -> new Magazynp(row, listOdpad)).collect(Collectors.toList());
        } catch (IOException ex) {
            System.out.println("Błąd odczytu pliku: \n" + ex);
            return null;
        }
    }
}
