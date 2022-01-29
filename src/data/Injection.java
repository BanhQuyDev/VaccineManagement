package data;

import java.io.Serializable;
import java.util.Date;
import tools.Validations;

/**
 *
 * @author QUANG HUY
 */
public class Injection implements Serializable {

    private String injectionID;
    private String injectionPlace1st;
    private String injectionPlace2nd;
    private Date injectionDate1st;
    private Date injectionDate2nd;
    private String studentID;
    private String vaccineID;

    public Injection() {

    }

    public Injection(String injectionID, String injectionPlace1st, Date injectionDate1st, String injectionPlace2nd, Date injectionDate2nd, String studentID, String vaccineID) {
        this.injectionID = injectionID;
        this.injectionPlace1st = injectionPlace1st;
        this.injectionPlace2nd = injectionPlace2nd;
        this.injectionDate1st = injectionDate1st;
        this.injectionDate2nd = injectionDate2nd;
        this.studentID = studentID;
        this.vaccineID = vaccineID;
    }

    public String getInjectionID() {
        return injectionID;
    }

    public void setInjectionID(String injectionID) {
        this.injectionID = injectionID;
    }

    public String getInjectionPlace1st() {
        return injectionPlace1st;
    }

    public void setInjectionPlace1st(String injectionPlace1st) {
        this.injectionPlace1st = injectionPlace1st;
    }

    public String getInjectionPlace2nd() {
        return injectionPlace2nd;
    }

    public void setInjectionPlace2nd(String injectionPlace2nd) {
        this.injectionPlace2nd = injectionPlace2nd;
    }

    public Date getInjectionDate1st() {
        return injectionDate1st;
    }

    public void setInjectionDate1st(Date injectionDate1st) {
        this.injectionDate1st = injectionDate1st;
    }

    public Date getInjectionDate2nd() {
        return injectionDate2nd;
    }

    public void setInjectionDate2nd(Date injectionDate2nd) {
        this.injectionDate2nd = injectionDate2nd;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getVaccineID() {
        return vaccineID;
    }

    public void setVaccineID(String vaccineID) {
        this.vaccineID = vaccineID;
    }

    public void showInformation() {
        System.out.printf("|%-14s|%-21s|%-20s|%-21s|%-19s|%-12s|%-12s|\n", injectionID, injectionPlace1st,
                Validations.convertDateToString(injectionDate1st), injectionPlace2nd, Validations.convertDateToString(injectionDate2nd),
                studentID, vaccineID);
    }
}
