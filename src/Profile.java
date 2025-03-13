class Profile extends User {
    private String name;
    private int age;
    private String gender;
    private int points;
    private int totalTasksCompleted;

    public Profile(String email, String password, String name, int age, String gender) {
        super(email, password);
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.points = 0;
        this.totalTasksCompleted = 0;
    }

    @Override
    public boolean login(String email, String password) {
        if (this.email.equals(email) && this.password.equals(password)) {
            this.isLoggedIn = true;
            System.out.println("Login successful. Welcome, " + name + "!");
            return true;
        } else {
            System.out.println("Login failed. Please check your credentials.");
            return false;
        }
    }

    @Override
    public void logout() {
        this.isLoggedIn = false;
        System.out.println("You have been logged out. Goodbye, " + name + "!");
    }

    // Method to update profile information
    public void updateProfile(String name, int age, String gender) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        System.out.println("Profile updated successfully!");
    }

    // Method to add points when tasks are completed
    public void addPoints(int points) {
        this.points += points;
        this.totalTasksCompleted++;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getPoints() {
        return points;
    }

    public int getTotalTasksCompleted() {
        return totalTasksCompleted;
    }

    public void setPassword(String password) {
        this.password = password;
        System.out.println("Password updated successfully!");
    }

    // Method to display profile information
    public void displayProfile() {
        System.out.println("\n--- Profile Information ---");
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Gender: " + gender);
        System.out.println("Email: " + email);
        System.out.println("Points: " + points);
        System.out.println("Tasks Completed: " + totalTasksCompleted);
        System.out.println("--------------------------\n");
    }
}