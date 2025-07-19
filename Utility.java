//Francisco Vazquez
//CPSC-39
//7/18/25
//Final Project: Task Management System
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utility {
    //Parse date from string
    public static LocalDate parseDate(String s) {
        try {
            return LocalDate.parse(s);
        }catch (DateTimeParseException e) {
            return null;
        }
    }

    // Prompt for integer with message
    public static int getInt(Scanner sc, String msg) {
        System.out.print(msg);
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number: ");
            sc.next();
        }
        return sc.nextInt();
    }
}