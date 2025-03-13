import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class EcoSparkApp {

    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    private String currentUser = null;
    // Store user credentials (email -> password)
    private Map<String, String> userCredentials = new HashMap<>();
    // Store user names (email -> name)
    private Map<String, String> userNames = new HashMap<>();
    // Store user task completion status (email_taskId -> completed)
    private Map<String, Boolean> taskCompletionStatus = new HashMap<>();

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EcoSparkApp().initialize());
    }

    private void initialize() {
        frame = new JFrame("EcoSpark");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        mainPanel = new JPanel();
        cardLayout = new CardLayout();
        mainPanel.setLayout(cardLayout);

        createLoginPanel();
        createRegisterPanel();
        createDashboardPanel();
        createTasksPanel();
        createQuizPanel();

        frame.add(mainPanel);
        frame.setVisible(true);

        // Start with login screen
        cardLayout.show(mainPanel, "login");
    }

    private void createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titleLabel = new JLabel("Login to EcoSpark");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton registerRedirectButton = new JButton("Register New Account");

        buttonPanel.add(loginButton);
        buttonPanel.add(registerRedirectButton);

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                statusLabel.setText("Please enter email and password");
                statusLabel.setForeground(Color.RED);
                return;
            }

            // Check credentials
            if (userCredentials.containsKey(email) && userCredentials.get(email).equals(password)) {
                statusLabel.setText("Login successful!");
                statusLabel.setForeground(Color.GREEN);
                currentUser = email;

                // Update welcome message
                updateWelcomeMessage();

                // Switch to dashboard after a short delay
                Timer timer = new Timer(1000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        cardLayout.show(mainPanel, "dashboard");

                        // Clear fields for next login
                        emailField.setText("");
                        passwordField.setText("");
                        statusLabel.setText(" ");
                    }
                });
                timer.setRepeats(false);
                timer.start();
            } else {
                statusLabel.setText("Invalid email or password");
                statusLabel.setForeground(Color.RED);
            }
        });

        registerRedirectButton.addActionListener(e -> {
            emailField.setText("");
            passwordField.setText("");
            statusLabel.setText(" ");
            cardLayout.show(mainPanel, "register");
        });

        loginPanel.add(titleLabel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(emailPanel);
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        loginPanel.add(buttonPanel);
        loginPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        loginPanel.add(statusLabel);

        mainPanel.add(loginPanel, "login");
    }

    private void createRegisterPanel() {
        JPanel registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

        JLabel titleLabel = new JLabel("Register for EcoSpark");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        JPanel agePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel ageLabel = new JLabel("Age:");
        JTextField ageField = new JTextField(5);
        agePanel.add(ageLabel);
        agePanel.add(ageField);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel genderLabel = new JLabel("Gender:");
        JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Other"});
        genderPanel.add(genderLabel);
        genderPanel.add(genderComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("Register");
        JButton loginRedirectButton = new JButton("Back to Login");

        buttonPanel.add(registerButton);
        buttonPanel.add(loginRedirectButton);

        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        registerButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String name = nameField.getText();
            String ageText = ageField.getText();
            String gender = (String) genderComboBox.getSelectedItem();

            if (email.isEmpty() || password.isEmpty() || name.isEmpty() || ageText.isEmpty()) {
                statusLabel.setText("Please fill all fields");
                statusLabel.setForeground(Color.RED);
                return;
            }

            if (userCredentials.containsKey(email)) {
                statusLabel.setText("Email already registered");
                statusLabel.setForeground(Color.RED);
                return;
            }

            try {
                int age = Integer.parseInt(ageText);

                // Store the user details
                userCredentials.put(email, password);
                userNames.put(email, name);

                statusLabel.setText("Registration successful! You can now login.");
                statusLabel.setForeground(Color.GREEN);

                // Clear fields
                emailField.setText("");
                passwordField.setText("");
                nameField.setText("");
                ageField.setText("");

                // Switch to login after a short delay
                Timer timer = new Timer(2000, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e1) {
                        cardLayout.show(mainPanel, "login");
                        statusLabel.setText(" ");
                    }
                });
                timer.setRepeats(false);
                timer.start();

            } catch (NumberFormatException ex) {
                statusLabel.setText("Age must be a valid number");
                statusLabel.setForeground(Color.RED);
            }
        });

        loginRedirectButton.addActionListener(e -> {
            emailField.setText("");
            passwordField.setText("");
            nameField.setText("");
            ageField.setText("");
            statusLabel.setText(" ");
            cardLayout.show(mainPanel, "login");
        });

        registerPanel.add(titleLabel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(emailPanel);
        registerPanel.add(passwordPanel);
        registerPanel.add(namePanel);
        registerPanel.add(agePanel);
        registerPanel.add(genderPanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        registerPanel.add(buttonPanel);
        registerPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        registerPanel.add(statusLabel);

        mainPanel.add(registerPanel, "register");
    }

    private JLabel welcomeLabel;

    private void updateWelcomeMessage() {
        if (currentUser != null && userNames.containsKey(currentUser)) {
            welcomeLabel.setText("Welcome, " + userNames.get(currentUser) + "!");
        } else {
            welcomeLabel.setText("Welcome to EcoSpark!");
        }
    }

    private void createDashboardPanel() {
        JPanel dashboardPanel = new JPanel(new BorderLayout());
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        welcomeLabel = new JLabel("Welcome to EcoSpark!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerPanel.add(welcomeLabel, BorderLayout.WEST);

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(e -> {
            currentUser = null;
            cardLayout.show(mainPanel, "login"); // Navigate to the login page after logout
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);

        JPanel menuPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JButton profileButton = new JButton("My Profile");
        JButton tasksButton = new JButton("Eco Tasks");
        JButton quizButton = new JButton("Take Quiz");
        JButton carbonFootprintButton = new JButton("Calculate Carbon Footprint");

        profileButton.addActionListener(e -> {
            // You can implement profile functionality here
            JOptionPane.showMessageDialog(frame, "Profile functionality coming soon!");
        });

        tasksButton.addActionListener(e -> {
            refreshTasksPanel(); // Refresh tasks before showing
            cardLayout.show(mainPanel, "tasks");
        });

        quizButton.addActionListener(e -> cardLayout.show(mainPanel, "quiz"));

        carbonFootprintButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Carbon footprint calculator coming soon!");
        });

        menuPanel.add(profileButton);
        menuPanel.add(tasksButton);
        menuPanel.add(quizButton);
        menuPanel.add(carbonFootprintButton);

        dashboardPanel.add(headerPanel, BorderLayout.NORTH);
        dashboardPanel.add(menuPanel, BorderLayout.CENTER);

        mainPanel.add(dashboardPanel, "dashboard");
    }

    private JPanel tasksListPanel;

    private void createTasksPanel() {
        JPanel tasksPanel = new JPanel(new BorderLayout());
        tasksPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Eco-Friendly Tasks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
        headerPanel.add(backButton, BorderLayout.EAST);

        tasksListPanel = new JPanel();
        tasksListPanel.setLayout(new BoxLayout(tasksListPanel, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(tasksListPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Available Tasks"));

        tasksPanel.add(headerPanel, BorderLayout.NORTH);
        tasksPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(tasksPanel, "tasks");
    }

    private void refreshTasksPanel() {
        tasksListPanel.removeAll();

        // Create eco-friendly tasks
        createTaskItem("task1", "Use reusable bags for grocery shopping", "Replace plastic bags with reusable bags for at least one week");
        createTaskItem("task2", "Reduce water usage", "Take shorter showers (5 minutes or less) for one week");
        createTaskItem("task3", "Energy conservation", "Turn off lights and electronics when not in use for a week");
        createTaskItem("task4", "Sustainable transportation", "Walk, bike, or use public transport instead of driving for 3 days");
        createTaskItem("task5", "Reduce food waste", "Plan meals to minimize food waste for one week");
        createTaskItem("task6", "Meatless Monday", "Go vegetarian for a full day to reduce carbon footprint");
        createTaskItem("task7", "Electronics recycling", "Properly recycle old electronics instead of throwing them away");
        createTaskItem("task8", "Plant a tree or garden", "Contribute to local biodiversity by planting native species");
        createTaskItem("task9", "Switch to LED bulbs", "Replace at least 3 conventional light bulbs with LED alternatives");
        createTaskItem("task10", "Zero waste day", "Go one full day without producing any disposable waste");
        createTaskItem("task11", "Litter cleanup", "Spend 30 minutes picking up litter in a public place");
        createTaskItem("task12", "Digital declutter", "Clean up your digital storage to save energy on servers");

        tasksListPanel.revalidate();
        tasksListPanel.repaint();
    }

    private void createTaskItem(String taskId, String title, String description) {
        JPanel taskPanel = new JPanel(new BorderLayout());
        taskPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY),
                BorderFactory.createEmptyBorder(10, 5, 10, 5)
        ));

        JPanel taskInfoPanel = new JPanel();
        taskInfoPanel.setLayout(new BoxLayout(taskInfoPanel, BoxLayout.Y_AXIS));

        JLabel taskTitleLabel = new JLabel(title);
        taskTitleLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel taskDescLabel = new JLabel(description);
        taskDescLabel.setFont(new Font("Arial", Font.PLAIN, 12));

        taskInfoPanel.add(taskTitleLabel);
        taskInfoPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        taskInfoPanel.add(taskDescLabel);

        String taskKey = (currentUser != null) ? currentUser + "_" + taskId : taskId;
        boolean isCompleted = taskCompletionStatus.getOrDefault(taskKey, false);

        JButton actionButton = new JButton(isCompleted ? "Completed" : "Mark as Completed");
        if (isCompleted) {
            actionButton.setBackground(new Color(144, 238, 144)); // Light green
            actionButton.setEnabled(false);
        }

        actionButton.addActionListener(e -> {
            if (currentUser == null) {
                JOptionPane.showMessageDialog(frame, "Please log in to track task completion");
                return;
            }

            taskCompletionStatus.put(taskKey, true);
            actionButton.setText("Completed");
            actionButton.setBackground(new Color(144, 238, 144)); // Light green
            actionButton.setEnabled(false);

            // Add point system or rewards here if needed
            JOptionPane.showMessageDialog(frame, "Great job! You've completed this eco-task!");
        });

        taskPanel.add(taskInfoPanel, BorderLayout.CENTER);
        taskPanel.add(actionButton, BorderLayout.EAST);

        tasksListPanel.add(taskPanel);
    }

    private void createQuizPanel() {
        JPanel quizPanel = new JPanel(new BorderLayout());
        quizPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Climate Change Knowledge Quiz");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel, BorderLayout.WEST);

        JButton backButton = new JButton("Back to Dashboard");
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
        headerPanel.add(backButton, BorderLayout.EAST);

        JPanel quizContentPanel = new JPanel();
        quizContentPanel.setLayout(new BoxLayout(quizContentPanel, BoxLayout.Y_AXIS));

        JLabel quizInstructionsLabel = new JLabel("Test your knowledge about climate change and its impacts");
        quizInstructionsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        quizInstructionsLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel questionPanel = new JPanel();
        questionPanel.setLayout(new BoxLayout(questionPanel, BoxLayout.Y_AXIS));
        questionPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel questionLabel = new JLabel("1. What is the main greenhouse gas contributing to climate change?");
        questionLabel.setFont(new Font("Arial", Font.BOLD, 14));

        JRadioButton option1 = new JRadioButton("Carbon Dioxide (CO₂)");
        JRadioButton option2 = new JRadioButton("Oxygen (O₂)");
        JRadioButton option3 = new JRadioButton("Nitrogen (N₂)");
        JRadioButton option4 = new JRadioButton("Hydrogen (H₂)");

        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        buttonGroup.add(option3);
        buttonGroup.add(option4);

        JButton submitButton = new JButton("Submit Answer");
        submitButton.addActionListener(e -> {
            if (option1.isSelected()) {
                JOptionPane.showMessageDialog(frame, "Correct! Carbon dioxide is the primary greenhouse gas contributing to climate change.");
            } else {
                JOptionPane.showMessageDialog(frame, "Incorrect. The correct answer is Carbon Dioxide (CO₂).");
            }
        });

        questionPanel.add(questionLabel);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        questionPanel.add(option1);
        questionPanel.add(option2);
        questionPanel.add(option3);
        questionPanel.add(option4);
        questionPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        questionPanel.add(submitButton);

        quizContentPanel.add(quizInstructionsLabel);
        quizContentPanel.add(questionPanel);

        JScrollPane scrollPane = new JScrollPane(quizContentPanel);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Questions"));

        quizPanel.add(headerPanel, BorderLayout.NORTH);
        quizPanel.add(scrollPane, BorderLayout.CENTER);

        mainPanel.add(quizPanel, "quiz");
    }
}