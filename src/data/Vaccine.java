package data;

import java.io.Serializable;

public class Vaccine implements Serializable {

    private String vaccineID;
    private String name;

    public Vaccine(String vaccineID, String name) {
        this.vaccineID = vaccineID;
        this.name = name;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void showInformation() {
        System.out.printf("|%-14s|%-16s|\n", vaccineID, name);
    }
}
