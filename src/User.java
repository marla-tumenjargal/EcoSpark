abstract class User {
    protected String email;
    protected String password;
    protected boolean isLoggedIn;

    public User(String email, String password) {
        this.email = email;
        this.password = password;
        this.isLoggedIn = false;
    }

    // Abstract methods to be implemented by subclasses
    public abstract boolean login(String email, String password);
    public abstract void logout();

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }
}