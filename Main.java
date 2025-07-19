import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserPreferences prefs = new UserPreferences();
        TaskManager tm = new TaskManager(prefs);

        while (true) {
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
                    System.out.println("Invalid date.");
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
            } else if (choice == 2) {
                tm.listTasks();
                int idx = Utility.getInt(sc, "Index to remove: ");
                tm.removeTask(idx);
            } else if (choice == 3) {
                tm.listTasks();
                int idx = Utility.getInt(sc, "Index to edit: ");
                sc.nextLine();
                System.out.print("New Title: ");
                String nt = sc.nextLine();
                System.out.print("New due date (YYYY-MM-DD): ");
                LocalDate nd = Utility.parseDate(sc.nextLine());
                tm.editTask(idx, nt, nd);
            } else if (choice == 4) {
                tm.listTasks();
            } else if (choice == 5) {
                tm.listOverdue();
            } else if (choice == 6) {
                tm.sortTasks();
                System.out.println("Tasks sorted.");
            } else if (choice == 7) {
                tm.detectConflicts();
            } else if (choice == 8) {
                System.out.println("This creates a recurring task (weekly, n times).");
                sc.nextLine();
                System.out.print("Title: ");
                String title = sc.nextLine();
                System.out.print("First due date (YYYY-MM-DD): ");
                LocalDate due = Utility.parseDate(sc.nextLine());
                int priority = Utility.getInt(sc, "Priority (1=High,2=Med,3=Low): ");
                System.out.print("Status (todo/done): ");
                String status = sc.next();
                System.out.println("Category (0=Work,1=School,2=Personal,3=Errand,4=Other): ");
                int catIdx = Utility.getInt(sc, "");
                if (catIdx < 0 || catIdx >= tm.categories.length) catIdx = 4;
                System.out.print("How many times to repeat? ");
                int recurCount = Utility.getInt(sc, "");
                Task t = new Task(title, due, priority, status, tm.categories[catIdx], true, recurCount);
                tm.addTask(t);
                tm.addRecurringTasks(t, recurCount, 7);
            } else if (choice == 9) {
                System.out.println("Preferences: 1. Set default priority 2. Toggle notifications");
                int p = Utility.getInt(sc, "");
                if (p == 1) {
                    int newPri = Utility.getInt(sc, "New Default priority: ");
                    prefs.setPref("defaultpriority", Integer.toString(newPri));
                    System.out.println("Set.");
                } else if (p == 2) {
                    String cur = prefs.getPref("notifications");
                    prefs.setPref("notifications", cur.equals("on") ? "off" : "on");
                    System.out.println("Notifications " + prefs.getPref("notifications"));
                }
            } else if (choice == 0) {
                System.out.println("Goodbye!");
                break;
            }
        }
        sc.close();
    }
}