# cpsc39-finalProjects
Francisco Vazquez
CPSC-39
7/18/25
Overview: On What the App Does
The Task Management System is a consolebased productivity app designed to help users organize and manage their daily tasks efficiently. It allows users to:
•	Add, edit, and remove tasks
•	Sort tasks by due date and priority
•	List overdue tasks
•	Detect scheduling conflicts
•	Add recurring weekly tasks
•	Customize preferences 
This application is both useful and user-friendly, targeting individuals who want a minimalistic yet powerful way to keep track of their to dos.
Algorithms Used in the App
Here are three key algorithms implemented in the project:
Algorithm 1: Sorting Tasks
Purpose: Sorts tasks by due date first and then by priority.
Time Complexity: O(n log n) — due to Collections.sort using a comparison-based sort.
Code :
public void sortTasks() {
    Collections.sort(tasks, new Comparator<Task>() {
        public int compare(Task a, Task b) {
            int dateCmp = a.dueDate.compareTo(b.dueDate);
            if (dateCmp != 0) return dateCmp;
            return Integer.compare(a.priority, b.priority);
        }
    });
}
Flowchart Step:
[Start] → [Call sortTasks()] → [Sort by due date] → [If equal, sort by priority] → [Done]
Algorithm 2: Conflict Detection
Purpose: Finds and prints pairs of tasks that share the same due date (conflict).
Time Complexity: O(n^2) — nested loop compares each pair of tasks.
Code :
public void detectConflicts() {
    for (int i = 0; i < tasks.size(); i++) {
        for (int j = i + 1; j < tasks.size(); j++) {
            if (tasks.get(i).dueDate.equals(tasks.get(j).dueDate)) {
                System.out.println("Conflict: " + tasks.get(i).title + " and " + tasks.get(j).title);
            }
        }
    }
}
Flowchart Step:
[Start] → [For each task i] → [For each task j > i] → [Compare due dates] → [If match, report conflict]
Algorithm 3: Add Recurring Tasks (Recursive)
Purpose: Automatically generates a series of recurring weekly tasks.
Time Complexity: O(r) — linear with the number of repetitions.
Code :
public void addRecurringTasks(Task base, int count, int intervalDays) {
    if (count <= 0) return;
    Task t = new Task(base.title, base.dueDate.plusDays(intervalDays), base.priority, base.status, base.category, true, count-1);
    tasks.add(t);
    addRecurringTasks(t, count-1, intervalDays);
}
Flowchart Step:
[Start] → [Base case: count <= 0?] → [If not, create new task] → [Add task to list] → [Recurse with count-1]
How the Algorithms Were Designed and Implemented
Each algorithm was designed based on core functionalities users would expect in a productivity app.
•	For sorting, I knew I needed a comparator. I wrote the logic to first compare due dates, and if equal, sort by priority. I used Java’s Collections.sort() which guarantees O(n log n) performance.
•	For conflict detection, I manually implemented a nested loop instead of using a Map<LocalDate, List<Task>> to keep it simple and readable, although I recognize the latter would be more efficient.
•	For recurring tasks, recursion seemed natural for repetition. I structured the base case and recursive case myself. ChatGPT helped clarify logic around recursive structures but didn’t write this method outright.
I used ChatGPT as a resource for:
•	Confirming time complexity
•	Validating use of recursion
•	Brainstorming ways to sort and detect conflicts
However, I made sure to implement, modify, and test each algorithm entirely on my own.
Along with the GitHub copilot ai assistant to get help on small errors towards the end of making the application.
Data Structures Used
LinkedList<Task>
•	Chosen for fast insertions/removals, especially useful when adding/removing multiple tasks.
•	Suits the dynamic nature of the task list better than arrays.
HashMap<String, String> (UserPreferences)
•	Enables fast lookup and storage of preferences.
•	Used for user-defined settings like default priority and notification toggles.
Opportunity During Development
While implementing recurring tasks, I realized recursion could make the code both elegant and efficient. Originally, I planned to use a loop but then saw an opportunity to practice recursion, which aligned with class learning goals. It made the feature more modular and easier to test.
Error Encountered and How I Fixed It  
I found a bug when parsing invalid dates, like 2024-02-30, which would crash the program. I fixed this by creating the Utility.parseDate() method. I used a try-catch block to handle DateTimeParseException and return null for invalid dates. I then check for null before moving on.  
Improvements for Future Versions
•	UI: Build a graphical or web-based front-end using JavaFX or React.
•	Storage: Persist tasks using a database or file I/O so data isn't lost after exit.
•	Reminders: Add scheduled notifications based on due dates.
•	Conflict Detection Optimization: Use a HashMap by date to reduce conflict detection from O(n^2) to O(n).

