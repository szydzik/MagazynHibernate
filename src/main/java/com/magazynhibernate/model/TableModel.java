/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magazynhibernate.model;

import com.magazynhibernate.dao.MagazynpDao;
import com.magazynhibernate.data.Magazynp;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author xxbar
 */
public class TableModel extends AbstractTableModel {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy.MM.dd HH:mm");
    List<Magazynp> list;

    public void refresh() {

        this.list = MagazynpDao.getInstance().findAll();

        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return list.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        
        switch (columnIndex) {
            case 0:
                return list.get(rowIndex).getID();
            case 1:
                return list.get(rowIndex).getNR_KARTY().toString();
//            case 2:
//                return list.get(rowIndex).getPracownik().getID_PRACOWNIK();
            case 2: {
                String s = "" + list.get(rowIndex).getODPAD().getGRUPA()
                        + "//" + list.get(rowIndex).getODPAD().getPODGRUPA()
                        + "//" + list.get(rowIndex).getODPAD().getRODZAJ();

                return s;
            }

//            case 4:
//                return list.get(rowIndex).getPacjent().getID_PACJENT();
            case 3: {
                return list.get(rowIndex).getNR_KLIENTA();
            }
            case 4: {
                return list.get(rowIndex).getFIRMA();
            }
            case 5: {
                return list.get(rowIndex).getJEDN();
            }
            case 6: {
                return list.get(rowIndex).getMASA();
            }
            case 7: {
                return DATE_FORMAT.format(list.get(rowIndex).getDATAD());
            }
            default:
                return "?";
        }
    }

}
