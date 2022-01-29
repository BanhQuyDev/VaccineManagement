package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import tools.Color;

public class StudentList {

    ArrayList<Student> studentList;
    File F = new File("student.dat");

    public StudentList() {
        studentList = new ArrayList<>();
    }

    public int findStudentById(String id) {
        if (studentList.isEmpty()) {
            return -1;
        }
        for (int i = 0; i < studentList.size(); i++) {
            if (studentList.get(i).getStudentID().toLowerCase().equalsIgnoreCase(id)) {
                return i;
            }
        }
        return -1;
    }

    public Student searchStudentById(String id) {
        for (Student student : studentList) {
            if (student.getStudentID().equalsIgnoreCase(id)) {
                return student;
            }
        }
        return null;
    }

    public void printStudentList() {
        System.out.println(Color.GREEN + "===========The list of student information===========" + Color.RESET);
        System.out.println(Color.YELLOW + "Injection 1: Yellow " + Color.RESET + ',' + Color.GREEN + ",Injection 2: Green" + Color.RESET
                + Color.RED + ",No injection : Red" + Color.RESET);
        System.out.print("");
        System.out.printf("| Id's student | Name's student |\n");
        for (Student student : studentList) {
            switch (student.getNumberOfInjection()) {
                case 1:
                    System.out.printf(Color.YELLOW + "|%-14s|%-16s|\n" + Color.RESET, student.getStudentID(), student.getName());
                    break;
                case 2:
                    System.out.printf(Color.GREEN + "|%-14s|%-16s|\n" + Color.RESET, student.getStudentID(), student.getName());
                    break;
                default:
                    System.out.printf(Color.RED + "|%-14s|%-16s|\n" + Color.RESET, student.getStudentID(), student.getName());
                    break;
            }
        }
        System.out.println("");
    }

    public void saveFile() {
        try {
            FileOutputStream fos = new FileOutputStream(F);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            if (studentList.isEmpty()) {
                studentList.add(new Student("SE151074", "Xuan Thiep", 1));
                studentList.add(new Student("SE151224", "Quang Huy", 1));
                studentList.add(new Student("SE151234", "Nguyen Vu", 0));
                studentList.add(new Student("SE151167", "Thanh Tung", 0));
                studentList.add(new Student("SE141184", "Thuy Tien", 1));
                studentList.add(new Student("SE123456", "Tan Thanh", 0));
                studentList.add(new Student("SE133344", "Tu Anh", 2));
                studentList.add(new Student("SE151086", "Duc Thinh", 0));
                studentList.add(new Student("SE151125", "Minh Tri", 0));
                studentList.add(new Student("SE151143", "Lam Truong", 0));
            } else {
                for (Student student : studentList) {
                    oos.writeObject(student);
                }
            }
            System.out.println(Color.GREEN + "Saved file success" + Color.RESET);
            oos.close();
            fos.close();

        } catch (Exception e) {
            System.out.println(Color.RED + "Save fail!!!" + Color.RESET);
        }
    }

    public void takeDataStudent() {
        if (F.exists()) {
            try {
                FileInputStream fis = new FileInputStream(F);
                ObjectInputStream ois = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Student s = (Student) ois.readObject();
                    studentList.add(s);
                }
                fis.close();
                ois.close();
            } catch (Exception e) {
            }
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(F);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                studentList.add(new Student("SE151074", "Xuan Thiep", 1));
                studentList.add(new Student("SE151224", "Quang Huy", 1));
                studentList.add(new Student("SE151234", "Nguyen Vu", 0));
                studentList.add(new Student("SE151167", "Thanh Tung", 0));
                studentList.add(new Student("SE141184", "Thuy Tien", 1));
                studentList.add(new Student("SE123456", "Tan Thanh", 0));
                studentList.add(new Student("SE133344", "Tu Anh", 2));
                studentList.add(new Student("SE151086", "Duc Thinh", 0));
                studentList.add(new Student("SE151125", "Minh Tri", 0));
                studentList.add(new Student("SE151143", "Lam Truong", 0));
                for (Student student : studentList) {
                    oos.writeObject(student);
                }
                oos.close();
                fos.close();
            } catch (Exception e) {
            }
        }
    }
}
