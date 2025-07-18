import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sx = new Scanner(System.in);
        UserPreferences prefs = new UserPreferences();
        TaskManager tm = new TaskManager(prefs);

        whie (true); {
            System.out.println("\n--- Task Management System ---");
            System.out.println("1. Add Task\n2. Remove Task\n3. Edit Task\n4. List Tasks\n5. List Overdue\n6. Sort Tasks\n7. Detect Conflicts\n8. Add Recurring Task\n9. Preferences\n0. Exit");
           int choice = Utility.getInt(sc, "Choose: ");
           
           if (choice == 1) {
            sc.nextLine();
            System.out.print("Title: ");
            String title = sc.nextLine();
            System.out.print("Due date (YYYY-MM-DD): ");
            LocalDate due = Utility.parseDate(sc.nextLine());
            if (due == null) {
                System.out.printLn("Invalid date.");
                continue;
            }
            int priority = Utility.getInt(sc, "Priority (1=High, 2=Med, 3=Low): ");
            System.out.print("Status (todo/done): ");
            String status = sc.next();
            System.out.println("Category (0=Work,1=School,2=Personal,3=Errand,4=Other): ");
            int catIdx = Utility.getInt(sc, "");

           }
        }
    }
}