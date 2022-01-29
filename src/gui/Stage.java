/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.InjectionList;
import tools.Color;
import tools.Validations;

/**
 *
 * @author QUANG HUY
 */
public class Stage {

    public static void main(String[] args) {
        int choice;
        InjectionList injection = new InjectionList();
        System.out.println("Welcome to Vaccine Management - @2021 <SE151224- Đoàn Vũ Quang Huy>");
        do {
            System.out.println(Color.CYAN_BACKGROUND + "=============================The Vaccine Management=============================" + Color.RESET);
            System.out.println("Select the options below");
            System.out.println("1. Show information all students have been injected.");
            System.out.println("2. Add student1's vaccine injection information.");
            System.out.println("3. Updating information of student's vaccine injection.");
            System.out.println("4. Delete student vaccine injection information. ");
            System.out.println("5. Search for injection information by StudentID.");
            System.out.println("6. Save information all students have been injection.");
            System.out.println("7. Quit");
            choice = Validations.inputIntMaxMin("Your choice: ", 1, 7, "You need to choose 1 to 7. Please try again !!! ");
            switch (choice) {
                case 1:
                    injection.printInjectiontList();
                    break;
                case 2:
                    injection.addInject();
                    break;
                case 3:
                    injection.updateInjection();
                    break;
                case 4:
                    injection.removeInjectionById();
                    break;
                case 5:
                    injection.searchInjectionByStudentId();
                    break;
                case 6:
                    injection.saveFileInjection();
                    break;
            }
        } while (choice < 7);
    }
}