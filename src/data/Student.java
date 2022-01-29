
package data;

import java.io.Serializable;

public class Student implements Serializable {

    private String name;
    private String studentID;
    private int numberOfInjection;

    public Student(String studentID, String name, int numberOfInjection) {
        this.name = name;
        this.studentID = studentID;
        this.numberOfInjection = numberOfInjection;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudentID() {
        return studentID;
    }

    public int getNumberOfInjection() {
        return numberOfInjection;
    }

    public void setNumberOfInjection(int numberOfInjection) {
        this.numberOfInjection = numberOfInjection;
    }
}
