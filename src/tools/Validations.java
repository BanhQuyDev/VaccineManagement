/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author QUANG HUY
 */
public class Validations {

    private static Scanner sc = new Scanner(System.in);

    public static int inputIntMaxMin(String message, int min, int max, String errorMsg) {
        if (min > max) {
            int t = min;
            min = max;
            max = t;
        }
        int data;
        do {
            try {
                System.out.print(message);
                data = Integer.parseInt(sc.nextLine());
                if (data >= min && data <= max) {
                    break;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (true);
        return data;
    }

    public static String getAnString(String message, String errorMsg) {
        String a;
        while (true) {
            try {
                System.out.print(message);
                a = sc.nextLine();
                if (a.equals("")) {
                    throw new Exception();
                }
                return a;
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        }
    }

    public static String inputPattern(String message, String pattern, String errorMsg) {
        String data;
        do {
            System.out.print(message);
            data = sc.nextLine().trim().toUpperCase();
            try {
                if (!data.matches(pattern)) {
                    throw new Exception();
                } else {
                    return data;
                }
            } catch (Exception e) {
                System.out.println(errorMsg);
            }
        } while (true);

    }

    public static Date checkFormatDate(String msg) {
        Date present = null;
        String timeInput = null;
        SimpleDateFormat d = new SimpleDateFormat("dd/MM/yyyy");
        d.setLenient(false);
        do {
            do {
                try {
                    sc = new Scanner(System.in);
                    System.out.print(msg);
                    timeInput = sc.nextLine();
                    if (!timeInput.matches("^\\d{1,2}[/]\\d{1,2}[/]\\d{4}$")) {
                        throw new Exception();
                    }
                    break;
                } catch (Exception e) {
                    System.err.println("Wrong date format!(DD/MM/YYYY)");
                }
            } while (true);
            try {
                present = d.parse(timeInput);
                break;
            } catch (ParseException e) {
                System.err.println("Invalid date!");;
            }
        } while (true);
        return present;
    }

    public static String convertDateToString(Date date) {
        SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
        if (date == null) {
            return String.format("null", date);
        }
        return f.format(date);
    }

    public static Date convertStringToDate(String s) throws ParseException {
        DateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = s;
        Date dateObject = sdf.parse(dateString);
        return dateObject;
    }
}
