import java.time.LocalDate;

public class Task {
    String title;
    LocalDate dueDate;
    int priority; // 1=High, 2=Medium, 3=Low
    String status;
    String category;
    boolean recurring;
    int recurCount;  //how many times to repeat

    //Constructor
    public Task(String title, LocalDate dueDate, int priority, String status, String category,boolean recurring, int recurCount) {
        this.title = title;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.category = category;
        this.recurring = recurring;
        this.recurCount = recurCount;
    }

    public String toString() {
        return title + " | Due: " + dueDate + " | Priority: " + priority + " | Status: " + status + " | Category: " + category + (recurring ? " | Repeats: " + recurCount : "");
    }
}