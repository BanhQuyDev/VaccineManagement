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
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import tools.Color;
import tools.Validations;

/**
 *
 * @author QUANG HUY
 */
public class InjectionList {

    StudentList student = new StudentList();
    VaccineList vaccine = new VaccineList();
    ArrayList<Injection> injectionList;
    File W = new File("injection.dat");

    public InjectionList() {
        injectionList = new ArrayList<>();
        student.takeDataStudent();
        vaccine.takeDataVaccine();
        readFile();
    }

    public int findInjectionById(String id) {
        if (injectionList.isEmpty()) {
            return -1;
        } else {
            for (int i = 0; i < injectionList.size(); i++) {
                if (injectionList.get(i).getInjectionID().toLowerCase().equalsIgnoreCase(id)) {
                    return i;
                }
            }
        }
        return -1;
    }

    public boolean findInjectionByVaccineId(String id) {
        for (int i = 0; i < injectionList.size(); i++) {
            if (injectionList.get(i).getVaccineID().equalsIgnoreCase(id)) {
                return true;
            }
        }
        return false;
    }

    public Injection searchInjectById(String id) {
        if (injectionList.isEmpty()) {
            return null;
        }
        for (Injection injection : injectionList) {
            if (injection.getInjectionID().equalsIgnoreCase(id)) {
                return injection;
            }
        }
        return null;
    }

    public Injection searchInjectByStudentId(String id) {
        if (injectionList.isEmpty()) {
            return null;
        }
        for (Injection injection : injectionList) {
            if (injection.getStudentID().equalsIgnoreCase(id)) {
                return injection;
            }
        }
        return null;
    }

    public void printInjectiontList() {
        System.out.println(Color.GREEN + "=============================================The list of injection information=============================================" + Color.RESET);
        System.out.println("");
        System.out.printf("| Injection Id | Injection Place 1st | Injection Date 1st | Injection Place 2nd |Injection Date 2nd | Student Id | Vaccine Id |\n");
        for (Injection injection : injectionList) {
            injection.showInformation();
        }
        System.out.println("");
    }

    public boolean addInject(Injection I) {
        return injectionList.add(I);
    }

    public void addInject() {
        do {
            Injection I = new Injection();
            do {
                I.setInjectionID(Validations.inputPattern("Input Injection ID: ", "[0-9]{1,}",
                        Color.RED + "Injection's ID is required!Please input the number" + Color.RESET));
                if (findInjectionById(I.getInjectionID()) != -1) {
                    System.out.println(Color.RED + "Injection id is duplicated.Please input another id!!" + Color.RESET);
                }
            } while (findInjectionById(I.getInjectionID()) != -1);
            student.printStudentList();
            do {
                do {
                    I.setStudentID(Validations.inputPattern("Input Student ID: ", "[sS]{1}[eE]{1}[0-9]{6}",
                            Color.RED + "Please enter correct student id in student list" + Color.RESET));
                    if (student.searchStudentById(I.getStudentID()) == null) {
                        System.out.println(Color.RED + "This Student ID has not exsits." + Color.RESET);
                    }
                } while (student.searchStudentById(I.getStudentID()) == null);

                if (searchInjectByStudentId(I.getStudentID()) == null) {
                    System.out.println(Color.ANSI_RED_BACKGROUND + "This student has not injected before." + Color.RESET);
                    break;
                } else {
                    System.out.printf("| Injection Id | Injection Place 1st | Injection Date 1st | Injection Place 2nd |Injection Date 2nd | Student Id | Vaccine Id |\n");
                    searchInjectByStudentId(I.getStudentID()).showInformation();
                    if (searchInjectByStudentId(I.getStudentID()).getInjectionDate2nd() == null
                            || searchInjectByStudentId(I.getStudentID()).getInjectionPlace2nd().equalsIgnoreCase("NULL")) {
                        System.out.println(Color.ANSI_YELLOW_BACKGROUND + "Student had first injection" + Color.RESET);
                        System.out.println("If you want to update Student's 2nd place and 2nd date.");
                        System.out.println("Please return to the menu and select update function.");
                        System.out.println("Please select another student.");
                    } else {
                        System.out.println(Color.ANSI_GREEN_BACKGROUND + "This student has completed 2 injections." + Color.RESET);
                        System.out.println("Please select another student.");
                    }
                }
            } while (true);
            vaccine.printVaccinetList();
            do {
                I.setVaccineID(Validations.inputPattern("Input Vaccine ID: ", "[cC]{1}[oO]{1}[vV]{1}[iI]{1}[dD]{1}[-]{1}[vV]{1}[0-9]{3}",
                        Color.RED + "Please enter correct vaccine id in vaccine list" + Color.RESET));
                if (vaccine.searchVaccineById(I.getVaccineID()) == null) {
                    System.out.println(Color.RED + "This Vaccine ID has not exsits." + Color.RESET);
                }
            } while (vaccine.searchVaccineById(I.getVaccineID()) == null);
            I.setInjectionPlace1st(Validations.getAnString("Input the first place: ", Color.RED + "Please try again !!!" + Color.RESET));
            I.setInjectionDate1st(Validations.checkFormatDate("Input first date: "));
            System.out.println("Do you want to record 2nd information (place and date)?(Y/N)");
            String choice = Validations.inputPattern("=> Your choice: ", "[ynYN]{1}", Color.RED + "Please input Y/N" + Color.RESET);
            if (choice.equalsIgnoreCase("N")) {
                I.setInjectionPlace2nd("NULL");
                I.setInjectionDate2nd(null);
                student.searchStudentById(I.getStudentID()).setNumberOfInjection(1);
            } else {
                I.setInjectionPlace2nd(Validations.getAnString("Input the second place: ", Color.RED + "Please try again !!!" + Color.RESET));
                Date injectionDate2_1 = Validations.checkFormatDate("Input second date: ");
                boolean injectionDate2_2 = checkDate(I.getInjectionDate1st(), injectionDate2_1);
                if (injectionDate2_2) {
                    I.setInjectionDate2nd(injectionDate2_1);
                } else {
                    while (injectionDate2_2 != true) {
                        System.out.println(Color.RED + "Vaccine must be give 4 to 12 weeks after the first injection!!!" + Color.RESET);
                        I.setInjectionDate2nd(Validations.checkFormatDate("Input second date: "));
                        injectionDate2_2 = checkDate(I.getInjectionDate1st(), I.getInjectionDate2nd());
                    }
                }
                student.searchStudentById(I.getStudentID()).setNumberOfInjection(2);
            }
            if (addInject(I)) {
                System.out.println(Color.GREEN + "Added successfully" + Color.RESET);
            } else {
                System.err.println("Add fail!");
            }
            System.out.println("Do you want to continue?(Y/N)");
            String choice1 = Validations.inputPattern("=> Your choice: ", "[ynYN]{1}", Color.RED + "Please input Y/N" + Color.RESET);
            if (choice1.equalsIgnoreCase("N")) {
                break;
            }
        } while (true);
    }

    public void updateInjection() {
        if (injectionList.isEmpty()) {
            System.out.println("Injection List is empty");
            System.out.println("Please add a new injection to use this selection.");
            return;
        }
        printInjectiontList();
        do {
            String chocie = Validations.inputPattern("Enter the injection ID you want to update: ", "[0-9]{1,}",
                    Color.RED + "Please input again !!!" + Color.RESET);
            if (searchInjectById(chocie) == null) {
                System.out.println(Color.RED + "Injection does not exits." + Color.RESET);
            } else {
                for (Injection injection : injectionList) {
                    if (injection.getInjectionID().equalsIgnoreCase(chocie)) {
                        injection.showInformation();
                        if (injection.getInjectionPlace2nd().equalsIgnoreCase("NULL") == false
                                || injection.getInjectionDate2nd() != null) {
                            System.out.println(Color.GREEN + "Student has completed 2 injections!" + Color.RESET);
                            System.out.println("Do you want to change new information in 2nd injection (place and date)?(Y/N)");
                            String choice1 = Validations.inputPattern("=> Your choice: ", "[ynYN]{1}", Color.RED + "Please input Y/N" + Color.RESET);
                            if (choice1.equalsIgnoreCase("Y")) {
                                injection.setInjectionPlace2nd(Validations.getAnString("Input the new second place: ", Color.RED + "Please try again !!!" + Color.RESET));
                                Date injectionDate2_1 = Validations.checkFormatDate("Input new second date: ");
                                boolean injectionDate2_2 = checkDate(injection.getInjectionDate1st(), injectionDate2_1);
                                if (injectionDate2_2) {
                                    injection.setInjectionDate2nd(injectionDate2_1);
                                } else {
                                    while (injectionDate2_2 != true) {
                                        System.out.println(Color.RED + "Vaccine must be give 4 to 12 weeks after the first injection!!!" + Color.RESET);
                                        injection.setInjectionDate2nd(Validations.checkFormatDate("Input second date: "));
                                        injectionDate2_2 = checkDate(injection.getInjectionDate1st(), injection.getInjectionDate2nd());
                                    }
                                }
                                student.searchStudentById(injection.getStudentID()).setNumberOfInjection(2);
                                System.out.println(Color.GREEN + "=============================================After update,here is the injection of information=============================================" + Color.RESET);
                                injection.showInformation();
                                System.out.println(Color.GREEN + "Upadte sucessfully" + Color.RESET);
                                System.out.println(Color.GREEN + "Student has completed 2 injections!" + Color.RESET);
                            } else {
                                System.out.println(Color.GREEN + "Thank you for using this function" + Color.RESET);
                            }
                        } else {
                            String vaccineId;
                            do {
                                do {
                                    vaccineId = Validations.inputPattern("Enter a 2nd vaccine id: ", "[cC]{1}[oO]{1}[vV]{1}[iI]{1}[dD]{1}[-]{1}[vV]{1}[0-9]{3}", Color.RED + "Please try aagin!!!" + Color.RESET);
                                    if (vaccine.searchVaccineById(vaccineId) == null) {
                                        System.out.println(Color.RED + "This vaccine ID has not exsits." + Color.RESET);
                                    }
                                } while (vaccine.searchVaccineById(vaccineId) == null);
                                if (injection.getVaccineID().equalsIgnoreCase(vaccineId) == false) {
                                    System.out.println(Color.YELLOW + "This student has a one injection." + Color.RESET);
                                    System.out.println(Color.RED + "Please enter same type vaccine:" + vaccine.searchVaccineById(injection.getVaccineID()).getName()
                                            + "(" + vaccine.searchVaccineById(injection.getVaccineID()).getVaccineID() + ")" + Color.RESET);
                                }
                            } while (injection.getVaccineID().equalsIgnoreCase(vaccineId) == false);
                            injection.setInjectionPlace2nd(Validations.getAnString("Input the new second place: ", Color.RED + "Please try again !!!" + Color.RESET));
                            Date injectionDate2_1 = Validations.checkFormatDate("Input new second date: ");
                            boolean injectionDate2_2 = checkDate(injection.getInjectionDate1st(), injectionDate2_1);
                            if (injectionDate2_2) {
                                injection.setInjectionDate2nd(injectionDate2_1);
                            } else {
                                while (injectionDate2_2 != true) {
                                    System.out.println(Color.RED + "Vaccine must be give 4 to 12 weeks after the first injection!!!" + Color.RESET);
                                    injection.setInjectionDate2nd(Validations.checkFormatDate("Input second date: "));
                                    injectionDate2_2 = checkDate(injection.getInjectionDate1st(), injection.getInjectionDate2nd());
                                }
                            }
                            student.searchStudentById(injection.getStudentID()).setNumberOfInjection(2);
                            System.out.println(Color.GREEN + "=============================================After update,here is the injection of information=============================================" + Color.RESET);
                            injection.showInformation();
                            System.out.println(Color.GREEN + "Upadte sucessfully" + Color.RESET);
                            System.out.println(Color.GREEN + "Student has completed 2 injections!" + Color.RESET);
                        }
                    }
                }
                break;
            }
        } while (true);

    }

    public boolean removeInjectionById(String id) {
        Injection p = searchInjectById(id);
        if (p == null) {
            System.out.println(Color.RED + "ID doesn't exist.Please input another id" + Color.RESET);
            return false;
        }
        System.out.printf("| Injection Id | Injection Place 1st | Injection Date 1st | Injection Place 2nd |Injection Date 2nd | Student Id | Vaccine Id |\n");
        searchInjectById(id).showInformation();
        System.out.println(Color.RED + "Are you sure that your choice?(Y/N)" + Color.RESET);
        String choice = Validations.inputPattern("=> Your choice: ",
                "[ynYN]{1}", Color.RED + "Please input Y/N" + Color.RESET);
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println(Color.GREEN + "Removed successfully" + Color.RESET);
            student.searchStudentById(p.getStudentID()).setNumberOfInjection(0);
            return injectionList.remove(p);
        }
        return false;
    }

    public void removeInjectionById() {
        if (injectionList.isEmpty()) {
            System.out.println(Color.RED + "Don't have injection in here!!!" + Color.RESET);
        } else {
            String idDelete;
            do {
                idDelete = Validations.inputPattern("Input id injection to delete:", "[0-9]{1,}",
                        Color.RED + "Please input the number!!!" + Color.RESET);

                if (removeInjectionById(idDelete)) {
                    break;
                }
            } while (true);
        }
    }

    public void searchInjectionByStudentId() {
//        if (injectionList.isEmpty()) {
//            System.out.println(Color.RED + "Don't have any here.Please add the injection!!!" + Color.RESET);
//            return;
//        }
//        do {
//            int result = 0;
//            String keyword = Validations.inputPattern("Input student id you want to search: ",
//                    "[sS]{1}[eE]{1}[0-9]{6}", Color.RED + "Please input correct format id(SE******)" + Color.RESET);
//            System.out.println(Color.GREEN + "==============================After searching,here is the injection of one student information==============================\n" + Color.RESET);
//            System.out.printf("| Injection Id | Injection Place 1st | Injection Date 1st | Injection Place 2nd |Injection Date 2nd | Student Id | Vaccine Id |\n");
//            for (Injection injection : injectionList) {
//                if (injection.getStudentID().equalsIgnoreCase(keyword)) {
//                    injection.showInformation();
//                    result++;
//                }
//            }
//            System.out.println(Color.RED + "*****************Have " + result + " result in here*****************" + Color.RESET);
//            if (result == 0) {
//                System.out.println(Color.RED + "This injection does not exist" + Color.RESET);
//            }
//            System.out.println("Do you want to continue?(Y/N)");
//            String choice1 = Validations.inputPattern("=> Your choice: ", "[ynYN]{1}", Color.RED + "Please input Y/N" + Color.RESET);
//            System.out.println("");
//            if (choice1.equalsIgnoreCase("N")) {
//                break;
//            }
//        } while (true);
        if (injectionList.isEmpty()) {
            System.out.println("Don't have injection list in here !!!");
        }
        do {
            int result = 0;
            String keyword = Validations.inputPattern("Input id student to delete: ", "[sS]{1}[eE]{1}[0-9]{6}", "Please input correct format SE******");
            System.out.println("After searching, here is the injection list");
            for (Injection injection : injectionList) {
                if (injection.getStudentID().equalsIgnoreCase(keyword)) {
                    injection.showInformation();
                    result++;
                }
            }
            System.out.println("Have" + result + "result in here!!!");
            if (result == 0) {
                System.out.println("This injection doesn't exist!!!");
            }
            System.out.println("Do you want to continue?(Y/N)");
            String choice = Validations.inputPattern("=>your choice: ", "[yYnN]{1}", "please input Y/N!!!");
            if (choice.equalsIgnoreCase("N")) {
                break;
            }
        } while (true);
    }

    public boolean checkDate(Date firstDate, Date secondDate) {
//        String date1 = Validations.convertDateToString(firstDate);
//        String date2 = Validations.convertDateToString(secondDate);
//        LocalDate d1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        LocalDate d2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
//        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
//        long diffDays = diff.toDays();
//        if (diffDays >= 28 && diffDays <= 84) {
//            return true;
//        }
//        return false;
        String date1 = Validations.convertDateToString(firstDate);
        String date2 = Validations.convertDateToString(secondDate);
        LocalDate d1 = LocalDate.parse(date1, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        LocalDate d2 = LocalDate.parse(date2, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        Duration diff = Duration.between(d1.atStartOfDay(), d2.atStartOfDay());
        long diffDays = diff.toDays();
        if (diffDays >= 28 && diffDays <= 84) {
            return true;
        }
        return false;
    }

    public void saveFileInjection() {
        try {
            FileOutputStream fos = new FileOutputStream(W);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            for (Injection vc : injectionList) {
                oos.writeObject(vc);
            }
            student.saveFile();
            System.out.println(Color.GREEN + "Save file success" + Color.RESET);
            oos.close();
            fos.close();
        } catch (Exception e) {
            System.out.println(Color.RED + "Save fail!!!" + Color.RESET);
        }
    }

    public void readFile() {
        if (W.exists()) {
            try {
                FileInputStream fis = new FileInputStream(W);
                ObjectInputStream oos = new ObjectInputStream(fis);
                while (fis.available() > 0) {
                    Injection v = (Injection) oos.readObject();
                    injectionList.add(v);
                }
                fis.close();
                oos.close();
            } catch (Exception e) {
            }
        } else {
            try {
                FileOutputStream fos = new FileOutputStream(W);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                injectionList.add(new Injection("1", "Tinh Dong Nai", Validations.convertStringToDate("20/09/2021"), "NULL", null, "SE151074", "COVID-V001"));
                injectionList.add(new Injection("2", "Quan Tan Binh", Validations.convertStringToDate("15/08/2021"), "NULL", null, "SE151224", "COVID-V002"));
                injectionList.add(new Injection("3", "Tinh Binh Duong", Validations.convertStringToDate("05/06/2021"), "NULL", null, "SE141184", "COVID-V004"));
                injectionList.add(new Injection("4", "Quan 12", Validations.convertStringToDate("08/03/2021"), "Quan 12", Validations.convertStringToDate("05/05/2021"), "SE133344", "COVID-V005"));
                for (Injection vc : injectionList) {
                    oos.writeObject(vc);
                }
                oos.close();
                fos.close();
            } catch (Exception e) {
                System.out.println(Color.RED + "Save fail!!!" + Color.RESET);
            }
        }

    }
}
