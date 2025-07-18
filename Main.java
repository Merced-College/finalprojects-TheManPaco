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
            if (catIdx < 0 || catIdx >= tm.categories.length) catIdx = 4;
            System.out.print("Recurring? (y/n): ");
            boolean recur = sc.next().equalsIgnoreCase("y");
            int recurCount = 0;
            if (recur) recurCount = Utility.getInt(sc, "How many times? ");
            Task t = new Task(title, due, priority, status, tm.categories[catIdx], recur, recurCount);
            tm.addTask(t);
            if (recur && recurCount > 0) {
                tm.addRecurringTasks(t, recurCount, 7); //repeat every 7 days
            }
            else if (choice == 2) {
                tm.listTasks();
                int idx = Utility.getInt(sc, "Index to remove: ");
                tm.removeTask(idx);
            }
            else if (choice == 3) {
                tm.listTasks();
                int idx = Utility.getInt(sc, "Index to edit: ");
                sx.nextLine();
                System.out.print("New Title: ");
                String nt = sx.nextLine();
                System.out.print("New due dat (YYYY-MM-DD): ");
                LocalDate nd = Utility.parseDate(sc.nextLine());
                tm.editTask(idx, nt, nd);
            }
            else if (choice == 4) {
                tm.listTasks();
            }
            else if (choice == 5) {
                tm.listOverdue();
            }
            else if (choice == 6) {
                tm.sortTasks();
                System.out.println("Tasks sorted.");
            }
            else if (choice == 7) {
                
            }
           }
        }
    }
}