import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Task class for managing eco-friendly tasks
class Task {
    private int id;
    private String title;
    private String description;
    private String longDescription; // Added for detailed task info
    private int pointsValue;
    private boolean completed;
    private Date completionDate;
    private static int taskIdCounter = 1;

    public Task(String title, String description, String longDescription, int pointsValue) {
        this.id = taskIdCounter++;
        this.title = title;
        this.description = description;
        this.longDescription = longDescription; // Initialize long description
        this.pointsValue = pointsValue;
        this.completed = false;
    }

    // Getters and setters
    public String getLongDescription() {
        return longDescription;
    }

    // Method to mark task as completed
    public void completeTask(Profile profile) {
        if (!this.completed) {
            this.completed = true;
            this.completionDate = new Date();
            profile.addPoints(this.pointsValue);
            System.out.println("Task '" + this.title + "' completed! You earned " + this.pointsValue + " points.");
        } else {
            System.out.println("This task has already been completed.");
        }
    }


    // Getters
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getPointsValue() {
        return pointsValue;
    }

    public boolean isCompleted() {
        return completed;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    // Display task details
    public void displayTaskDetails() {
        System.out.println("\nTask #" + id + ": " + title);
        System.out.println("Description: " + description);
        System.out.println("Points: " + pointsValue);
        System.out.println("Status: " + (completed ? "Completed on " + completionDate : "Not completed"));
    }

    // Create a library of predefined tasks
    public static List<Task> createTaskLibrary() {
        List<Task> taskLibrary = new ArrayList<>();

        taskLibrary.add(new Task("Recycle a water bottle",
                "Collect and properly recycle plastic water bottles.",
                "Recycling plastic bottles helps reduce waste and conserve resources. Make sure to rinse the bottle before recycling.",
                5));
        taskLibrary.add(new Task("Use reusable bags",
                "Bring your own reusable bags when shopping.",
                "Using reusable bags reduces plastic waste and helps protect marine life. Keep a few bags in your car or bag for convenience.",
                10));
        // Add more tasks with long descriptions
        return taskLibrary;
    }
}