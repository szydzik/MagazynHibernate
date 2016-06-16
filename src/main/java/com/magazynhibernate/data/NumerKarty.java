/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.data;

import java.io.Serializable;
import java.util.Scanner;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.*;
/**
 *
 * @author xxbar
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class NumerKarty implements Comparable<NumerKarty>, Serializable {

    @Id
    @GeneratedValue
    private Long ID;
    private Integer NUMER;
    private Integer FIRMA;
    private Integer ROK;
    

    public NumerKarty(String s) {
        ID = 0l;
        Scanner scan = new Scanner(s).useDelimiter("/");
        NUMER = scan.hasNextInt() ? scan.nextInt() : null;
        if (NUMER == null) {
            scan.next();
        }
        FIRMA = scan.hasNextInt() ? scan.nextInt() : null;
        if (FIRMA == null) {
            scan.next();
        }
        ROK = scan.hasNextInt() ? scan.nextInt() : null;
    }


    @Override
    public String toString() {
        return NUMER + "/" + FIRMA + "/" + ROK;
    }

    @Override
    public int compareTo(NumerKarty t) {

        if (ROK.compareTo(t.getROK()) != 0) {
            return ROK.compareTo(t.getROK());
        }

        if (FIRMA.compareTo(t.getFIRMA()) != 0) {
            return FIRMA.compareTo(t.getFIRMA());
        }

        if (NUMER.compareTo(t.getNUMER()) != 0) {
            return NUMER.compareTo(t.getNUMER());
        }

        return 0;

    }

}
