package hotel_management;

import java.util.Scanner;

public class validCheck {
    static Scanner sc = new Scanner(System.in);
    public static String getString(String msg1, String msg2) {
        boolean check = true;
        String result = "";
        do {
            System.out.print(msg1);
            result = sc.nextLine().trim();
            if (result.isEmpty()) {
                System.out.println(msg2);
            } else {
                check = false;
            }
        } while (check);
        return result;
    }

    public static String getStringUpdate(String msg) {
        String result = "";
        
            System.out.print(msg);
            result = sc.nextLine().trim();
        return result;
    }

    public static int getIntUpdate(String msg, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(msg);
                String inputNumber = sc.nextLine().trim();

                if (inputNumber.length() > 10) {
                    System.out.println("Length must be less than or equal to 10! Please try again.");
                    check = true;
                } else {
                    number = Integer.parseInt(inputNumber);
    
                    if (number < min) {
                        System.out.println("Must be larger than " + min);
                        check = true;
                    } else {
                        check = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please try another number!");
            }
        } while (check || number < min);
        return number;
    }

    public static int getInt(String msg, int min) {
        boolean check = true;
        int number = 0;
        do {
            try {
                System.out.print(msg);
                String inputNumber = sc.nextLine().trim();

                if (inputNumber.isEmpty()) {
                    System.out.println("Input cannot be empty.");
                    check = true;
                } else if (inputNumber.length() > 10) {
                    System.out.println("Length must be less than or equal to 10! Please try again.");
                    check = true;
                } else {
                    number = Integer.parseInt(inputNumber);
    
                    if (number < min) {
                        System.out.println("Must be larger than " + min);
                        check = true;
                    } else {
                        check = false;
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Please try another number!");
            }
        } while (check || number < min);
        return number;
    }
    
}
