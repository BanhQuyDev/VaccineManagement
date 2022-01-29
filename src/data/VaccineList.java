/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import tools.Color;

/**
 *
 * @author QUANG HUY
 */
public class VaccineList {

    File Q = new File("vaccine.dat");
    ArrayList<Vaccine> vaccineList;

    public VaccineList() {
        vaccineList = new ArrayList<>();
    }

    public int findVaccineById(String id) {
        for (int i = 0; i < vaccineList.size(); i++) {
            if (vaccineList.get(i).getVaccineID().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }
    public Vaccine searchVaccineById(String id) {
        for (Vaccine vaccine : vaccineList) {
            if (vaccine.getVaccineID().toLowerCase().equalsIgnoreCase(id)) {
                return vaccine;
            }
        }
        return null;
    }

    public void printVaccinetList() {
        System.out.println(Color.GREEN + "===========The list of vaccine information===========" + Color.RESET);
        System.out.println("");
        System.out.printf("| Id's vaccine | Name's vaccine |\n");
        for (Vaccine vaccine : vaccineList) {
            vaccine.showInformation();
        }
        System.out.println("");
    }

    public void takeDataVaccine() {
        if (Q.exists()) {
            try {
                FileInputStream fis = new FileInputStream(Q);
                ObjectInputStream oos = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Vaccine v = (Vaccine) oos.readObject();
                    vaccineList.add(v);
                }
                fis.close();
                oos.close();
            } catch (Exception e) {
            }
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(Q);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                vaccineList.add(new Vaccine("COVID-V001", "AstraZeneca"));
                vaccineList.add(new Vaccine("COVID-V002", "SPUTNIK V"));
                vaccineList.add(new Vaccine("COVID-V003", "Vero Cell"));
                vaccineList.add(new Vaccine("COVID-V004", "Pfizer"));
                vaccineList.add(new Vaccine("COVID-V005", "Moderna"));
                for (Vaccine vc : vaccineList) {
                    oos.writeObject(vc);
                }
                oos.close();
                fos.close();
            } catch (Exception e) {
            }
        }

    }

}
