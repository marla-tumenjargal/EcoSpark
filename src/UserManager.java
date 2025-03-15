import java.util.HashMap;
import java.util.Map;

class UserManager {
    private Map<String, String> users = new HashMap<>(); // Stores user credentials
    private String currentUser;

    public boolean registerUser(String email, String password) {
        if (!users.containsKey(email)) {
            users.put(email, password);
            return true;
        }
        return false;
    }

    public boolean loginUser(String email, String password) {
        if (users.containsKey(email) && users.get(email).equals(password)) {
            currentUser = email;
            return true;
        }
        return false;
    }

    public String getCurrentUser() {
        return currentUser;
    }
}