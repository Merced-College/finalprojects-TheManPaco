import java.util.LinkedList;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDate;

public class TaskManager {
    LinkedList<Task> tasks;
    String[] categories;
    UserPreferences userPrefs;

    public TaskManager(UserPreferences prefs) {
        tasks = new LinkedList<>();
        categories = new String[]{"Work", "School", "Personal", "Errand", "Other"};
        userPrefs = prefs;
    }

    //add a task ( algoritm: adds to linked list)
    public void addTask(Task t) {
        tasks.add(t);
    }

    //remove task by index
    public void removeTask(int idx) {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.remove(idx);
        }
    }
    
    //edit task title and due date for simplicity
    public void editTask(int idx, String newTitle, LocalDate newDueDate) {
        if (idx >= 0 && idx < tasks.size()) {
            tasks.get(idx).title = newTitle;
            tasks.get(idx).dueDate = newDueDate;
        }
    }

    // Algorithm 1: sort tasks bu due date and then priority
    public void sortTasks() {
        // O(n log n)
        Collections.sort(tasks, new Comparator<Task>() {
            public int compare(Task a, Task b) {
                int dateCmp = a.dueDate.compareTo(b.dueDate);
                if (dateCmp != 0) return dateCmp;
                return Integer.compare(a.priority, b.priority);
            }
        });
    }

    // Algorithm 2: Detect conflicts (overlapping due dates)
    public void detectConflicts() {
        boolean found = false;
        for (int i = 0; i < tasks.size(); i++) {
            for (int j = i + 1; j < tasks.size(); j++) {
                if (tasks.get(i).dueDate.equals(tasks.get(j).dueDate)) {
                    System.out.println("Conflict: " + tasks.get(i).title + " and " + tasks.get(j).title + " are due on " + tasks.get(i).dueDate);
                    found = true;
                }
            }
        }
        if (!found) System.out.println("No task conflicts detected.");
    }

    // Algorithm 3: revursivel generate recurring tasks
    public void addRecurringTasks(Task base, int count, int intervalDays) {
        // O(r)
        if ( count <= 0) return;
        Task t = new Task(base.title, base.dueDate.plusDays(intervalDays), base.priority, base.status, base.category, true, count-1);
        tasks.add(t);
        addRecurringTasks(t, count-1, intervalDays);
    }

    //List all tasks
    public void listTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks.");
            return;
        }
        int i = 0;
        for (Task t : tasks) {
            System.out.println((i++) + ". " + t.toString());
        }
    }

    //List overdue tasks
    public void listOverdue() {
        boolean found = false;
        for (Task t : tasks) {
            if (t.dueDate.isBefore(LocalDate.now()) && !"done".equalsIgnoreCase(t.status)) {
                System.out.println("[OVERDUE] " + t.toString());
                found = true;
            }
        }
        if (!found) System.out.println("No overdue tasks.");
    }
}