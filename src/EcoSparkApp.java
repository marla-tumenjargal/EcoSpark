import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EcoSparkApp extends JFrame {
    private Profile currentUser;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    // Panels
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel dashboardPanel;
    private JPanel profilePanel;
    private JPanel tasksPanel;
    private JPanel carbonFootprintPanel;
    private JPanel quizPanel;

    public EcoSparkApp() {
        // Set up the main window
        setTitle("EcoSpark");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Initialize panels
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // Create and add panels
        createLoginPanel();
        createRegisterPanel();
        createDashboardPanel();
        createProfilePanel();
        createTasksPanel();
        createCarbonFootprintPanel();
        createQuizPanel();

        // Add panels to main panel
        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(dashboardPanel, "dashboard");
        mainPanel.add(profilePanel, "profile");
        mainPanel.add(tasksPanel, "tasks");
        mainPanel.add(carbonFootprintPanel, "carbonFootprint");
        mainPanel.add(quizPanel, "quiz");

        // Show login panel first
        cardLayout.show(mainPanel, "login");

        // Add main panel to frame
        add(mainPanel);
    }

    // Create the login panel
    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        loginPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Login to EcoSpark");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Status message
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to login panel
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(titleLabel);
        loginPanel.add(Box.createVerticalStrut(30));
        loginPanel.add(emailPanel);
        loginPanel.add(Box.createVerticalStrut(10));
        loginPanel.add(passwordPanel);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(buttonPanel);
        loginPanel.add(Box.createVerticalStrut(20));
        loginPanel.add(statusLabel);

        // Login button action
        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Simulate login (replace with actual backend logic)
            if (email.equals("user@example.com") && password.equals("password")) {
                currentUser = new Profile(email, password, "John Doe", 25, "Male");
                statusLabel.setText("Login successful! Redirecting...");
                statusLabel.setForeground(Color.GREEN);

                // Clear fields
                emailField.setText("");
                passwordField.setText("");

                // Switch to dashboard
                cardLayout.show(mainPanel, "dashboard");
            } else {
                statusLabel.setText("Login failed. Please check your credentials.");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Register button action
        registerButton.addActionListener(e -> cardLayout.show(mainPanel, "register"));
    }

    // Create the register panel
    private void createRegisterPanel() {
        registerPanel = new JPanel();
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));
        registerPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        registerPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Register for EcoSpark");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name field
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Login");
        buttonPanel.add(registerButton);
        buttonPanel.add(backButton);

        // Status message
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to register panel
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(titleLabel);
        registerPanel.add(Box.createVerticalStrut(30));
        registerPanel.add(namePanel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(emailPanel);
        registerPanel.add(Box.createVerticalStrut(10));
        registerPanel.add(passwordPanel);
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(buttonPanel);
        registerPanel.add(Box.createVerticalStrut(20));
        registerPanel.add(statusLabel);

        // Register button action
        registerButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            // Simulate registration (replace with actual backend logic)
            if (!name.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
                currentUser = new Profile(email, password, name, 25, "Male");
                statusLabel.setText("Registration successful! Redirecting...");
                statusLabel.setForeground(Color.GREEN);

                // Clear fields
                nameField.setText("");
                emailField.setText("");
                passwordField.setText("");

                // Switch to dashboard
                cardLayout.show(mainPanel, "dashboard");
            } else {
                statusLabel.setText("Registration failed. Please fill in all fields.");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "login"));
    }

    // Create the dashboard panel
    private void createDashboardPanel() {
        dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BoxLayout(dashboardPanel, BoxLayout.Y_AXIS));
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        dashboardPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Welcome to EcoSpark, " + (currentUser != null ? currentUser.getName() : "User") + "!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Buttons
        JButton profileButton = new JButton("Edit Profile");
        JButton tasksButton = new JButton("View Tasks");
        JButton carbonFootprintButton = new JButton("Calculate Carbon Footprint");
        JButton quizButton = new JButton("Take Quiz");
        JButton logoutButton = new JButton("Logout");

        // Add components to dashboard panel
        dashboardPanel.add(Box.createVerticalStrut(20));
        dashboardPanel.add(titleLabel);
        dashboardPanel.add(Box.createVerticalStrut(30));
        dashboardPanel.add(profileButton);
        dashboardPanel.add(Box.createVerticalStrut(10));
        dashboardPanel.add(tasksButton);
        dashboardPanel.add(Box.createVerticalStrut(10));
        dashboardPanel.add(carbonFootprintButton);
        dashboardPanel.add(Box.createVerticalStrut(10));
        dashboardPanel.add(quizButton);
        dashboardPanel.add(Box.createVerticalStrut(20));
        dashboardPanel.add(logoutButton);

        // Button actions
        profileButton.addActionListener(e -> cardLayout.show(mainPanel, "profile"));
        tasksButton.addActionListener(e -> cardLayout.show(mainPanel, "tasks"));
        carbonFootprintButton.addActionListener(e -> cardLayout.show(mainPanel, "carbonFootprint"));
        quizButton.addActionListener(e -> cardLayout.show(mainPanel, "quiz"));
        logoutButton.addActionListener(e -> {
            currentUser = null;
            cardLayout.show(mainPanel, "login");
        });
    }

    // Create the profile panel
    private void createProfilePanel() {
        profilePanel = new JPanel();
        profilePanel.setLayout(new BoxLayout(profilePanel, BoxLayout.Y_AXIS));
        profilePanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        profilePanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Edit Profile");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Name field
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton saveButton = new JButton("Save Changes");
        JButton backButton = new JButton("Back to Dashboard");
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);

        // Status message
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add components to profile panel
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(titleLabel);
        profilePanel.add(Box.createVerticalStrut(30));
        profilePanel.add(namePanel);
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(emailPanel);
        profilePanel.add(Box.createVerticalStrut(10));
        profilePanel.add(passwordPanel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(buttonPanel);
        profilePanel.add(Box.createVerticalStrut(20));
        profilePanel.add(statusLabel);

        // Save button action
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (currentUser != null) {
                currentUser.setName(name);
                currentUser.setEmail(email);
                currentUser.setPassword(password);
                statusLabel.setText("Profile updated successfully!");
                statusLabel.setForeground(Color.GREEN);
            } else {
                statusLabel.setText("Error: No user logged in.");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
    }

    // Create the tasks panel
    private void createTasksPanel() {
        tasksPanel = new JPanel();
        tasksPanel.setLayout(new BoxLayout(tasksPanel, BoxLayout.Y_AXIS));
        tasksPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        tasksPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Tasks");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Task list with checkboxes
        JPanel taskListPanel = new JPanel();
        taskListPanel.setLayout(new BoxLayout(taskListPanel, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(taskListPanel);

        // Long description area
        JTextArea descriptionArea = new JTextArea(5, 30);
        descriptionArea.setEditable(false);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);

        // Load tasks
        List<Task> tasks = Task.createTaskLibrary();
        for (Task task : tasks) {
            JCheckBox taskCheckBox = new JCheckBox(task.getTitle());
            taskCheckBox.addActionListener(e -> {
                if (taskCheckBox.isSelected()) {
                    descriptionArea.setText(task.getLongDescription());
                } else {
                    descriptionArea.setText("");
                }
            });
            taskListPanel.add(taskCheckBox);
        }

        // Complete task button
        JButton completeTaskButton = new JButton("Complete Selected Task");
        completeTaskButton.addActionListener(e -> {
            for (int i = 0; i < tasks.size(); i++) {
                JCheckBox checkBox = (JCheckBox) taskListPanel.getComponent(i);
                if (checkBox.isSelected()) {
                    Task selectedTask = tasks.get(i);
                    selectedTask.completeTask(currentUser);
                    JOptionPane.showMessageDialog(tasksPanel, "Task completed! You earned " + selectedTask.getPointsValue() + " points.");
                    checkBox.setEnabled(false); // Disable checkbox after completion
                }
            }
        });

        // Back button
        JButton backButton = new JButton("Back to Dashboard");

        // Add components to tasks panel
        tasksPanel.add(Box.createVerticalStrut(20));
        tasksPanel.add(titleLabel);
        tasksPanel.add(Box.createVerticalStrut(30));
        tasksPanel.add(scrollPane);
        tasksPanel.add(Box.createVerticalStrut(20));
        tasksPanel.add(descriptionScrollPane);
        tasksPanel.add(Box.createVerticalStrut(20));
        tasksPanel.add(completeTaskButton);
        tasksPanel.add(Box.createVerticalStrut(10));
        tasksPanel.add(backButton);

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
    }

    // Create the carbon footprint panel
    private void createCarbonFootprintPanel() {
        carbonFootprintPanel = new JPanel();
        carbonFootprintPanel.setLayout(new BoxLayout(carbonFootprintPanel, BoxLayout.Y_AXIS));
        carbonFootprintPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        carbonFootprintPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Carbon Footprint Calculator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Input fields
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(8, 2, 10, 10));
        inputPanel.setBackground(new Color(240, 248, 255));

        JLabel electricityLabel = new JLabel("Electricity Usage (kWh):");
        JTextField electricityField = new JTextField();
        JLabel gasLabel = new JLabel("Gas Usage (therms):");
        JTextField gasField = new JTextField();
        JLabel carMilesLabel = new JLabel("Car Miles Driven:");
        JTextField carMilesField = new JTextField();
        JLabel publicTransportLabel = new JLabel("Public Transport Miles:");
        JTextField publicTransportField = new JTextField();
        JLabel flightMilesLabel = new JLabel("Flight Miles:");
        JTextField flightMilesField = new JTextField();
        JLabel vegetarianLabel = new JLabel("Are you vegetarian? (Yes/No):");
        JTextField vegetarianField = new JTextField();
        JLabel meatMealsLabel = new JLabel("Meat Meals per Week:");
        JTextField meatMealsField = new JTextField();
        JLabel wasteLabel = new JLabel("Waste Produced (kg):");
        JTextField wasteField = new JTextField();

        inputPanel.add(electricityLabel);
        inputPanel.add(electricityField);
        inputPanel.add(gasLabel);
        inputPanel.add(gasField);
        inputPanel.add(carMilesLabel);
        inputPanel.add(carMilesField);
        inputPanel.add(publicTransportLabel);
        inputPanel.add(publicTransportField);
        inputPanel.add(flightMilesLabel);
        inputPanel.add(flightMilesField);
        inputPanel.add(vegetarianLabel);
        inputPanel.add(vegetarianField);
        inputPanel.add(meatMealsLabel);
        inputPanel.add(meatMealsField);
        inputPanel.add(wasteLabel);
        inputPanel.add(wasteField);

        // Calculate button
        JButton calculateButton = new JButton("Calculate Footprint");
        calculateButton.setBackground(new Color(50, 205, 50)); // Green color
        calculateButton.setForeground(Color.WHITE);
        calculateButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Result display
        JTextArea resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false);
        resultArea.setLineWrap(true);
        resultArea.setWrapStyleWord(true);
        resultArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane resultScrollPane = new JScrollPane(resultArea);

        // Back button
        JButton backButton = new JButton("Back to Dashboard");
        backButton.setBackground(new Color(255, 99, 71)); // Tomato color
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Arial", Font.BOLD, 14));

        // Add components to carbon footprint panel
        carbonFootprintPanel.add(Box.createVerticalStrut(20));
        carbonFootprintPanel.add(titleLabel);
        carbonFootprintPanel.add(Box.createVerticalStrut(30));
        carbonFootprintPanel.add(inputPanel);
        carbonFootprintPanel.add(Box.createVerticalStrut(20));
        carbonFootprintPanel.add(calculateButton);
        carbonFootprintPanel.add(Box.createVerticalStrut(20));
        carbonFootprintPanel.add(resultScrollPane);
        carbonFootprintPanel.add(Box.createVerticalStrut(20));
        carbonFootprintPanel.add(backButton);

        // Calculate button action
        calculateButton.addActionListener(e -> {
            try {
                double electricityUsage = Double.parseDouble(electricityField.getText());
                double gasUsage = Double.parseDouble(gasField.getText());
                double carMiles = Double.parseDouble(carMilesField.getText());
                double publicTransportMiles = Double.parseDouble(publicTransportField.getText());
                double flightMiles = Double.parseDouble(flightMilesField.getText());
                boolean isVegetarian = vegetarianField.getText().equalsIgnoreCase("Yes");
                int meatMealsPerWeek = Integer.parseInt(meatMealsField.getText());
                double wasteProduced = Double.parseDouble(wasteField.getText());

                CarbonFootprint footprint = new CarbonFootprint();
                footprint.calculateFootprint(electricityUsage, gasUsage, carMiles, publicTransportMiles, flightMiles, isVegetarian, meatMealsPerWeek, wasteProduced);

                // Display results
                resultArea.setText("--- Your Carbon Footprint ---\n" +
                        "Calculation Date: " + footprint.getCalculationDate() + "\n\n" +
                        "Breakdown by Category (tons of CO2):\n" +
                        "Home: " + String.format("%.2f", footprint.getHomeEmissions()) + " tons\n" +
                        "Transport: " + String.format("%.2f", footprint.getTransportEmissions()) + " tons\n" +
                        "Food: " + String.format("%.2f", footprint.getFoodEmissions()) + " tons\n" +
                        "Waste: " + String.format("%.2f", footprint.getWasteEmissions()) + " tons\n\n" +
                        "Total Emissions: " + String.format("%.2f", footprint.getTotalEmissions()) + " tons of CO2 per year\n\n");
            } catch (NumberFormatException ex) {
                resultArea.setText("Invalid input. Please enter numbers only.");
            }
        });

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
    }


    // Create the quiz panel
    private void createQuizPanel() {
        quizPanel = new JPanel();
        quizPanel.setLayout(new BoxLayout(quizPanel, BoxLayout.Y_AXIS));
        quizPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        quizPanel.setBackground(new Color(240, 248, 255)); // Light blue background

        // Title
        JLabel titleLabel = new JLabel("Climate Change Quiz");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Question area
        JLabel questionLabel = new JLabel();
        questionLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Options panel
        JPanel optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        ButtonGroup optionsGroup = new ButtonGroup();
        JRadioButton[] optionButtons = new JRadioButton[4];

        // Next question button
        JButton nextButton = new JButton("Next Question");

        // Result display
        JLabel resultLabel = new JLabel();
        resultLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Load quiz
        Quiz quiz = Quiz.createClimateChangeQuiz();

        // Display first question
        displayQuestion(quiz, questionLabel, optionsPanel, optionsGroup, optionButtons);

        // Next button action
        nextButton.addActionListener(e -> {
            // Check answer
            for (int i = 0; i < optionButtons.length; i++) {
                if (optionButtons[i].isSelected()) {
                    char answer = (char) ('A' + i);
                    boolean isCorrect = quiz.checkAnswer(answer);
                    resultLabel.setText(isCorrect ? "Correct!" : "Incorrect!");
                    break;
                }
            }

            // Move to next question
            if (quiz.nextQuestion()) {
                displayQuestion(quiz, questionLabel, optionsPanel, optionsGroup, optionButtons);
            } else {
                JOptionPane.showMessageDialog(quizPanel, "Quiz completed!");
                cardLayout.show(mainPanel, "dashboard");
            }
        });

        // Back button
        JButton backButton = new JButton("Back to Dashboard");

        // Add components to quiz panel
        quizPanel.add(Box.createVerticalStrut(20));
        quizPanel.add(titleLabel);
        quizPanel.add(Box.createVerticalStrut(30));
        quizPanel.add(questionLabel);
        quizPanel.add(Box.createVerticalStrut(20));
        quizPanel.add(optionsPanel);
        quizPanel.add(Box.createVerticalStrut(20));
        quizPanel.add(nextButton);
        quizPanel.add(Box.createVerticalStrut(10));
        quizPanel.add(resultLabel);
        quizPanel.add(Box.createVerticalStrut(20));
        quizPanel.add(backButton);

        // Back button action
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "dashboard"));
    }

    private void displayQuestion(Quiz quiz, JLabel questionLabel, JPanel optionsPanel, ButtonGroup optionsGroup, JRadioButton[] optionButtons) {
        Question currentQuestion = quiz.getCurrentQuestion();
        if (currentQuestion != null) {
            questionLabel.setText(currentQuestion.getQuestionText());

            // Clear previous options
            optionsPanel.removeAll();
            optionsGroup.clearSelection();

            // Add new options
            List<String> options = currentQuestion.getOptions();
            for (int i = 0; i < options.size(); i++) {
                optionButtons[i] = new JRadioButton(options.get(i));
                optionsGroup.add(optionButtons[i]);
                optionsPanel.add(optionButtons[i]);
            }

            // Refresh panel
            optionsPanel.revalidate();
            optionsPanel.repaint();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            EcoSparkApp app = new EcoSparkApp();
            app.setVisible(true);
        });
    }
}