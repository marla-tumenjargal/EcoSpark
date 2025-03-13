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
    private int pointsValue;
    private boolean completed;
    private Date completionDate;
    private static int taskIdCounter = 1;

    public Task(String title, String description, int pointsValue) {
        this.id = taskIdCounter++;
        this.title = title;
        this.description = description;
        this.pointsValue = pointsValue;
        this.completed = false;
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

        taskLibrary.add(new Task("Recycle a water bottle", "Collect and properly recycle plastic water bottles.", 5));
        taskLibrary.add(new Task("Use reusable bags", "Bring your own reusable bags when shopping.", 10));
        taskLibrary.add(new Task("Turn off lights", "Turn off lights when leaving a room to save energy.", 5));
        taskLibrary.add(new Task("Walk or bike", "Choose walking or biking instead of driving for short trips.", 15));
        taskLibrary.add(new Task("Plant a tree", "Plant a tree to help absorb CO2 from the atmosphere.", 50));
        taskLibrary.add(new Task("Save water", "Take shorter showers to conserve water.", 10));
        taskLibrary.add(new Task("Reduce meat consumption", "Have a meat-free day to reduce your carbon footprint.", 20));
        taskLibrary.add(new Task("Use public transport", "Take public transportation instead of driving alone.", 25));
        taskLibrary.add(new Task("Clean up litter", "Collect and dispose of litter in your neighborhood.", 30));
        taskLibrary.add(new Task("Reduce plastic use", "Avoid single-use plastics for a day.", 15));

        return taskLibrary;
    }
}