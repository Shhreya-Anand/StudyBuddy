import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    /**
     * Uses a try-catch block to handle user input, ensuring the entered value is an integer within the specified range.
     * If the input is not a valid integer, catches InputMismatchException and prompts the user for a valid input.
     */
    static int getIntInput(String prompt) {
        Scanner inputScanner = new Scanner(System.in);
        boolean validInput = false;
        int value = 0;
        if(!prompt.isEmpty()) {
            System.out.println(prompt);
        }
        while (!validInput) {
            try {
                value = inputScanner.nextInt();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.printf("Please enter a valid number\n");
                inputScanner.next();
            }
        }
        return value;
    }

    /**
     * Uses a try-catch block to handle user input, ensuring the entered value is an integer within the specified range.
     * If the input is not a valid integer, catches InputMismatchException and prompts the user for a valid input.
     */
    static int getIntInput(String prompt, int min, int max) {
        Scanner inputScanner = new Scanner(System.in);
        boolean validInput = false;
        int value = 0;
        if(!prompt.isEmpty()) {
            System.out.println(prompt);
        }
        while (!validInput) {
            try {
                value = inputScanner.nextInt();
                if (value < min) {
                    System.out.printf("Please enter a number greater than " + min + "\n");
                } else if (value > max) {
                    System.out.printf("Please enter a number less than " + max + "\n");
                } else {
                    validInput = true;
                }
            } catch (InputMismatchException e) {
                System.out.printf("Please enter a valid number\n");
                inputScanner.next();
            }
        }
        return value;
    }

    static String getStringInput(String prompt) {
        Scanner inputScanner = new Scanner(System.in);
        boolean validInput = false;
        String value = "";
        if(!prompt.isEmpty()) {
            System.out.println(prompt);
        }
        while (!validInput) {
            try {
                value = inputScanner.nextLine();
                validInput = true;
            } catch (InputMismatchException e) {
                System.out.printf("Please enter a valid number\n");
                inputScanner.next();
            }
        }
        return value;
    }

    public static String generateRandomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder randomString = new StringBuilder();

        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(characters.length());
            randomString.append(characters.charAt(index));
        }

        return randomString.toString();
    }

    public static boolean validateAmount(String a) {
        final String NUMBER_REGEX = "\\d{1,3}(?:[.,]\\d{3})*(?:[.,]\\d{2})";
        a.replaceAll("$", "");
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(a);
        return matcher.matches();
    }

//    public static boolean validateTicker(String s) {
//        ArrayList<String> stonks = Market.getTheMarket().getAllAvailableTickers();
//        boolean b = false;
//        for (String stonk : stonks) {
//            if (s.equals(stonk)) {
//                b = true;
//            }
//        }
//        return b;
//    }

    public static boolean validateNum(String s) {
        final String NUMBER_REGEX = "^[1-9][0-9]*$";
        Pattern pattern = Pattern.compile(NUMBER_REGEX);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
    
    public static boolean compareDates(Date d1, Date d2) {
        return d1.getYear() == d2.getYear() && d1.getMonth() == d2.getMonth() && d1.getDay() == d2.getDay();
    }

}