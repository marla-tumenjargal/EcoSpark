import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EcoSparkTest extends JFrame {

    private Profile currentUser;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    private JScrollPane scrollPane;

    // set up the login panels
    private JPanel loginPanel;
    private JPanel registerPanel;
    private JPanel dashboardPanel;
    private JPanel profilePanel;
    private JPanel tasksPanel;
    private JPanel carbonFootprintPanel;
    private JPanel quizPanel;

    // Main colors used in the design
    private static final Color PRIMARY_COLOR = new Color(70, 130, 95);
    private static final Color SECONDARY_COLOR = new Color(245, 245, 240);
    private static final Color ACCENT_COLOR = new Color(95, 102, 177);
    private static final Color BUTTON_BLUE = new Color(65, 90, 230);
    private static final Color TEXT_COLOR = new Color(30, 30, 30);

    // Fonts
    private Font HEADER_FONT;
    private Font BODY_FONT;
    private Font BUTTON_FONT;



    public EcoSparkTest() {
        setTitle("EcoSpark");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        // Load fonts
        loadFonts();

        // Create main scrollable panel
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBackground(Color.WHITE);

        // Add components to main panel
        createNavBar();
        createHeroSection();
        createOfferSection();
        createAboutSection();
        createFooterSection();

        // Create scroll pane and add main panel to it
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setBorder(null);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); // Prevent horizontal scrolling

        add(scrollPane);
        setVisible(true);
    }

    private void createRegisterPanel() {
        // Remove the existing mainPanel and scrollPane
        getContentPane().remove(scrollPane);

        // Create register panel
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
        namePanel.setBackground(new Color(240, 248, 255));
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        namePanel.add(nameLabel);
        namePanel.add(nameField);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.setBackground(new Color(240, 248, 255));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(new Color(240, 248, 255));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton registerButton = new JButton("Register");
        JButton backButton = new JButton("Back to Home");
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

                // Create and show dashboard
                createHeroSection();
            } else {
                statusLabel.setText("Registration failed. Please fill in all fields.");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            // Remove register panel and restore main panel
            getContentPane().remove(registerPanel);
            getContentPane().add(scrollPane);
            revalidate();
            repaint();
        });

        // Add register panel to frame and refresh
        getContentPane().add(registerPanel);
        revalidate();
        repaint();
    }

    private void createLoginPanel() {
        // Remove the existing mainPanel and scrollPane
        getContentPane().remove(scrollPane);

        // Create login panel
        loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));
        loginPanel.setBackground(new Color(240, 248, 255));

        // Title
        JLabel titleLabel = new JLabel("Login to EcoSpark");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Email field
        JPanel emailPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        emailPanel.setBackground(new Color(240, 248, 255));
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField(20);
        emailPanel.add(emailLabel);
        emailPanel.add(emailField);

        // Password field
        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        passwordPanel.setBackground(new Color(240, 248, 255));
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        passwordPanel.add(passwordLabel);
        passwordPanel.add(passwordField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.setBackground(new Color(240, 248, 255));
        JButton loginButton = new JButton("Login");
        JButton backButton = new JButton("Back to Home");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(backButton);
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

                // Create and show dashboard
                createHeroSection();
            } else {
                statusLabel.setText("Login failed. Please check your credentials.");
                statusLabel.setForeground(Color.RED);
            }
        });

        // Back button action
        backButton.addActionListener(e -> {
            // Remove login panel and restore main panel
            getContentPane().remove(loginPanel);
            getContentPane().add(scrollPane);
            revalidate();
            repaint();
        });

        // Register button action
        registerButton.addActionListener(e -> {
            // Remove login panel and show register panel
            getContentPane().remove(loginPanel);
            createRegisterPanel();
        });

        // Add login panel to frame and refresh
        getContentPane().add(loginPanel);
        revalidate();
        repaint();
    }

    private void createNavBar() {
        JPanel navPanel = new JPanel();
        navPanel.setLayout(new BorderLayout());
        navPanel.setBackground(Color.WHITE);
        navPanel.setMaximumSize(new Dimension(800, 70));
        navPanel.setPreferredSize(new Dimension(800, 70));

        // Logo
        JLabel logoLabel = new JLabel("EcoSpark");
        logoLabel.setFont(new Font("Arial", Font.BOLD, 20));
        logoLabel.setBorder(new EmptyBorder(0, 20, 0, 0));

        // Navigation items
        JPanel navItems = new JPanel();
        navItems.setLayout(new FlowLayout(FlowLayout.CENTER));
        navItems.setBackground(Color.WHITE);

        String[] navLabels = {"Dashboard", "Contact", "About", "My Carbon Footprint", "Tasks"};
        for (String label : navLabels) {
            JButton navButton = new JButton(label);
            navButton.setBorderPainted(false);
            navButton.setContentAreaFilled(false);
            navButton.setFocusPainted(false);
            navButton.setFont(BODY_FONT);
            navButton.setForeground(TEXT_COLOR);
            navItems.add(navButton);
        }

        // Profile icon
        JPanel profilePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        profilePanel.setBackground(Color.WHITE);

        JButton profileButton = new JButton();
        try {
            ImageIcon profileIcon = new ImageIcon(new ImageIcon(getClass().getResource("/com/hillcrest/visuals/profile_icon.png"))
                    .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
            profileButton.setIcon(profileIcon);
        } catch (Exception e) {
            profileButton.setText("Profile");
        }
        profileButton.setBorderPainted(false);
        profileButton.setContentAreaFilled(false);
        profileButton.setFocusPainted(false);
        profilePanel.add(profileButton);

        navPanel.add(logoLabel, BorderLayout.WEST);
        navPanel.add(navItems, BorderLayout.CENTER);
        navPanel.add(profilePanel, BorderLayout.EAST);

        // Add separator line
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));

        JPanel navContainer = new JPanel();
        navContainer.setLayout(new BoxLayout(navContainer, BoxLayout.Y_AXIS));
        navContainer.add(navPanel);
        navContainer.add(separator);
        navContainer.setBackground(Color.WHITE);
        navContainer.setMaximumSize(new Dimension(800, 71));

        mainPanel.add(navContainer);
    }

    private void createHeroSection() {
        JPanel heroPanel = new JPanel();
        heroPanel.setLayout(new BoxLayout(heroPanel, BoxLayout.X_AXIS));
        heroPanel.setBackground(Color.WHITE);
        heroPanel.setBorder(new EmptyBorder(0, 40, 40, 40));
        heroPanel.setMaximumSize(new Dimension(800, 400));

        // Text section
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel welcomeLabel = new JLabel("Welcome to EcoSpark");
        welcomeLabel.setFont(HEADER_FONT);
        welcomeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextArea descriptionText = new JTextArea(
                "Climate change is one of our world's most pressing issues. It " +
                        "threatens our forests, our freshwater supply, and our planet's " +
                        "livelihood. EcoSpark was made with our Earth in mind; we strive " +
                        "to create a community that supports, educates, and inspires our " +
                        "users to visualize their unique impact on climate change. Be the " +
                        "change, one step at a time."
        );
        descriptionText.setWrapStyleWord(true);
        descriptionText.setLineWrap(true);
        descriptionText.setEditable(false);
        descriptionText.setFocusable(false);
        descriptionText.setFont(BODY_FONT);
        descriptionText.setBackground(Color.WHITE);
        descriptionText.setAlignmentX(Component.LEFT_ALIGNMENT);
        descriptionText.setMaximumSize(new Dimension(350, 150));

        // Buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        buttonPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        JButton joinButton = new JButton("JOIN NOW");
        joinButton.setBackground(BUTTON_BLUE);
        joinButton.setForeground(BUTTON_BLUE);
        joinButton.setFont(BUTTON_FONT);
        joinButton.setFocusPainted(false);
        joinButton.setBorderPainted(false);
        joinButton.setPreferredSize(new Dimension(150, 40));
        joinButton.setMaximumSize(new Dimension(150, 40));
        joinButton.addActionListener(e -> createRegisterPanel());

        JButton loginButton = new JButton("LOG IN");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(TEXT_COLOR);
        loginButton.setFont(BUTTON_FONT);
        loginButton.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(150, 40));
        loginButton.setMaximumSize(new Dimension(150, 40));
        loginButton.addActionListener(e -> createLoginPanel());

        buttonPanel.add(joinButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(15, 0)));
        buttonPanel.add(loginButton);

        // Stats
        JPanel statsPanel = new JPanel();
        statsPanel.setLayout(new BoxLayout(statsPanel, BoxLayout.X_AXIS));
        statsPanel.setBackground(Color.WHITE);
        statsPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        statsPanel.setBorder(new EmptyBorder(30, 0, 0, 0));

        JPanel resourcesPanel = new JPanel();
        resourcesPanel.setLayout(new BoxLayout(resourcesPanel, BoxLayout.Y_AXIS));
        resourcesPanel.setBackground(Color.WHITE);

        JLabel resourcesNumber = new JLabel("30+");
        resourcesNumber.setFont(new Font("Arial", Font.BOLD, 20));
        resourcesNumber.setForeground(ACCENT_COLOR);
        resourcesNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel resourcesLabel = new JLabel("Informative Resources");
        resourcesLabel.setFont(BODY_FONT);
        resourcesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        resourcesPanel.add(resourcesNumber);
        resourcesPanel.add(resourcesLabel);

        JPanel actionsPanel = new JPanel();
        actionsPanel.setLayout(new BoxLayout(actionsPanel, BoxLayout.Y_AXIS));
        actionsPanel.setBackground(Color.WHITE);
        actionsPanel.setBorder(new EmptyBorder(0, 50, 0, 0));

        JLabel actionsNumber = new JLabel("100+");
        actionsNumber.setFont(new Font("Arial", Font.BOLD, 20));
        actionsNumber.setForeground(ACCENT_COLOR);
        actionsNumber.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel actionsLabel = new JLabel("Daily Actions");
        actionsLabel.setFont(BODY_FONT);
        actionsLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        actionsPanel.add(actionsNumber);
        actionsPanel.add(actionsLabel);

        statsPanel.add(resourcesPanel);
        statsPanel.add(actionsPanel);

        textPanel.add(welcomeLabel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        textPanel.add(descriptionText);
        textPanel.add(buttonPanel);
        textPanel.add(statsPanel);

        // Image section
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setLayout(new FlowLayout()); // Ensures the label is properly added

        JLabel imageLabel = new JLabel();
        try {
            ImageIcon homeGraphic = new ImageIcon(new ImageIcon(getClass().getResource("/com/hillcrest/visuals/homepagegraphic.png"))
                    .getImage().getScaledInstance(300, 300, Image.SCALE_SMOOTH));
            imageLabel.setIcon(homeGraphic);
        } catch (Exception e) {
            imageLabel.setText("Image Not Found");
            imageLabel.setFont(new Font("Arial", Font.BOLD, 14));
            imageLabel.setPreferredSize(new Dimension(300, 400));
            imageLabel.setBackground(new Color(220, 240, 255));
            imageLabel.setOpaque(true);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        }

        imagePanel.add(imageLabel);
        add(imagePanel, BorderLayout.CENTER);

        setVisible(true);

        heroPanel.add(textPanel);
        heroPanel.add(Box.createRigidArea(new Dimension(20, 0)));
        heroPanel.add(imagePanel);

        mainPanel.add(heroPanel);
    }

    private void createOfferSection() {
        JPanel offerPanel = new JPanel();
        offerPanel.setLayout(new BoxLayout(offerPanel, BoxLayout.Y_AXIS));
        offerPanel.setBackground(Color.WHITE);
        offerPanel.setBorder(new EmptyBorder(20, 40, 40, 40));
        offerPanel.setMaximumSize(new Dimension(800, 400));

        JLabel offerTitle = new JLabel("What We Offer");
        offerTitle.setFont(HEADER_FONT);
        offerTitle.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the title

        JPanel offerCards = new JPanel();
        offerCards.setLayout(new BoxLayout(offerCards, BoxLayout.X_AXIS));
        offerCards.setBackground(Color.WHITE);
        offerCards.setAlignmentX(Component.CENTER_ALIGNMENT); // Center the cards container
        offerCards.setBorder(new EmptyBorder(30, 0, 0, 0));

        // Card 1: Carbon Footprint Calculator (as clickable button)
        JPanel card1 = createOfferCardButton("Carbon Footprint Calculator", "/com/hillcrest/visuals/carbon_footprint_button_graphic.png", PRIMARY_COLOR);

        // Card 2: Informative Resources
        JPanel card2 = createOfferCard("Informative Resources", null, SECONDARY_COLOR);

        // Card 3: Personalized Actions
        JPanel card3 = createOfferCard("Personalized Actions", "/com/hillcrest/visuals/take_action.png", PRIMARY_COLOR);

        offerCards.add(card1);
        offerCards.add(Box.createRigidArea(new Dimension(15, 0)));
        offerCards.add(card2);
        offerCards.add(Box.createRigidArea(new Dimension(15, 0)));
        offerCards.add(card3);

        offerPanel.add(offerTitle);
        offerPanel.add(offerCards);

        mainPanel.add(offerPanel);
    }

    private JPanel createOfferCard(String title, String imagePath, Color backgroundColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(backgroundColor);
        card.setPreferredSize(new Dimension(220, 270));
        card.setMaximumSize(new Dimension(220, 270));

        JPanel imageContainer = new JPanel();
        imageContainer.setBackground(backgroundColor);
        imageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageContainer.setPreferredSize(new Dimension(200, 200));
        imageContainer.setMaximumSize(new Dimension(200, 200));

        JLabel imageLabel = new JLabel();
        try {
            if (imagePath != null) {
                ImageIcon cardImage = new ImageIcon(new ImageIcon(getClass().getResource(imagePath))
                        .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                imageLabel.setIcon(cardImage);
            } else if (title.equals("Informative Resources")) {
                // We need to create a placeholder for earth in hands
                // In a real app, you would have an image for this
                imageLabel.setText("Earth Image");
                imageLabel.setForeground(PRIMARY_COLOR);
                imageLabel.setFont(BODY_FONT);
                imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
                imageLabel.setPreferredSize(new Dimension(150, 150));
            }
        } catch (Exception e) {
            imageLabel.setText(title + " Icon");
            imageLabel.setForeground(backgroundColor.equals(PRIMARY_COLOR) ? Color.WHITE : PRIMARY_COLOR);
            imageLabel.setFont(BODY_FONT);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(150, 150));
        }

        imageContainer.add(imageLabel);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(backgroundColor.equals(PRIMARY_COLOR) ? Color.WHITE : TEXT_COLOR);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        card.add(Box.createVerticalGlue());
        card.add(imageContainer);
        card.add(titleLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private JPanel createOfferCardButton(String title, String imagePath, Color backgroundColor) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(backgroundColor);
        card.setPreferredSize(new Dimension(220, 270));
        card.setMaximumSize(new Dimension(220, 270));
        card.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)); // Change cursor to hand when hovering

        // Make the entire card clickable
        card.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // Carbon footprint calculator functionality would go here
                System.out.println("Carbon Footprint Calculator clicked");
            }

            public void mouseEntered(java.awt.event.MouseEvent evt) {
                card.setBackground(backgroundColor.darker());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                card.setBackground(backgroundColor);
            }
        });

        JPanel imageContainer = new JPanel();
        imageContainer.setBackground(backgroundColor);
        imageContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageContainer.setPreferredSize(new Dimension(200, 200));
        imageContainer.setMaximumSize(new Dimension(200, 200));
        imageContainer.setOpaque(false); // Make it transparent to show the panel's background color

        JLabel imageLabel = new JLabel();
        try {
            if (imagePath != null) {
                ImageIcon cardImage = new ImageIcon(new ImageIcon(getClass().getResource(imagePath))
                        .getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
                imageLabel.setIcon(cardImage);
            }
        } catch (Exception e) {
            imageLabel.setText(title + " Icon");
            imageLabel.setForeground(Color.WHITE);
            imageLabel.setFont(BODY_FONT);
            imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imageLabel.setPreferredSize(new Dimension(150, 150));
        }

        imageContainer.add(imageLabel);

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setBorder(new EmptyBorder(20, 0, 0, 0));

        card.add(Box.createVerticalGlue());
        card.add(imageContainer);
        card.add(titleLabel);
        card.add(Box.createVerticalGlue());

        return card;
    }

    private void createAboutSection() {
        JPanel aboutPanel = new JPanel();
        aboutPanel.setLayout(new BoxLayout(aboutPanel, BoxLayout.X_AXIS));
        aboutPanel.setBackground(Color.WHITE);
        aboutPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        aboutPanel.setMaximumSize(new Dimension(800, 350));

        // Text section
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setBackground(Color.WHITE);
        textPanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel learnMore = new JLabel("LEARN MORE");
        learnMore.setFont(new Font("Arial", Font.BOLD, 14));
        learnMore.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel titlePanel = new JPanel();
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.X_AXIS));
        titlePanel.setBackground(Color.WHITE);
        titlePanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel aboutLabel = new JLabel("About ");
        aboutLabel.setFont(HEADER_FONT);

        JLabel ecospark = new JLabel("EcoSpark");
        ecospark.setFont(HEADER_FONT);
        ecospark.setForeground(ACCENT_COLOR);

        titlePanel.add(aboutLabel);
        titlePanel.add(ecospark);

        JTextArea aboutText1 = new JTextArea(
                "Welcome to EcoSpark! We believe that every " +
                        "person has the power to be an everyday hero for " +
                        "our planet."
        );
        configureTextArea(aboutText1);

        JTextArea aboutText2 = new JTextArea(
                "Our mission is to provide you with the tools, " +
                        "knowledge, and support you need to make a real " +
                        "difference in your daily life."
        );
        configureTextArea(aboutText2);

        JTextArea aboutText3 = new JTextArea(
                "We strive to provide our users with the resources " +
                        "they need to contribute to lessening the effects of " +
                        "global warming one action at a time."
        );
        configureTextArea(aboutText3);

        textPanel.add(learnMore);
        textPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        textPanel.add(titlePanel);
        textPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        textPanel.add(aboutText1);
        textPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        textPanel.add(aboutText2);
        textPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        textPanel.add(aboutText3);

        // Image section
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(PRIMARY_COLOR);
        imagePanel.setPreferredSize(new Dimension(250, 250));
        imagePanel.setMaximumSize(new Dimension(250, 250));
        imagePanel.setAlignmentY(Component.TOP_ALIGNMENT);

        JLabel imageLabel = new JLabel();
        try {
            ImageIcon aboutGraphic = new ImageIcon(new ImageIcon(getClass().getResource("/com/hillcrest/visuals/about_ecospark_graphic.png"))
                    .getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH));
            imageLabel.setIcon(aboutGraphic);
        } catch (Exception e) {
            imageLabel.setText("About Graphic");
            imageLabel.setFont(BODY_FONT);
            imageLabel.setForeground(Color.WHITE);
        }

        imagePanel.add(imageLabel);

        aboutPanel.add(textPanel);
        aboutPanel.add(Box.createHorizontalGlue());
        aboutPanel.add(imagePanel);

        mainPanel.add(aboutPanel);
    }

    private void loadFonts() {
        try {
            // Try to load custom fonts if available
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

            // For demonstration, if fonts aren't available, use system fonts with similar properties
            HEADER_FONT = new Font("Arial", Font.BOLD, 28);
            BODY_FONT = new Font("Arial", Font.PLAIN, 14);
            BUTTON_FONT = new Font("Arial", Font.BOLD, 14);

            // Note: In a production environment, you would load the fonts from files:
            // Font poppinsFont = Font.createFont(Font.TRUETYPE_FONT, new File("Poppins-Medium.ttf"));
            // Font dmSansFont = Font.createFont(Font.TRUETYPE_FONT, new File("DMSans-Regular.ttf"));
            // ge.registerFont(poppinsFont);
            // ge.registerFont(dmSansFont);
            // HEADER_FONT = poppinsFont.deriveFont(28f);
            // BODY_FONT = dmSansFont.deriveFont(14f);
            // BUTTON_FONT = dmSansFont.deriveFont(Font.BOLD, 14f);

        } catch (Exception e) {
            System.out.println("Error loading fonts: " + e.getMessage());
            // Fall back to system fonts
            HEADER_FONT = new Font("Arial", Font.BOLD, 28);
            BODY_FONT = new Font("Arial", Font.PLAIN, 14);
            BUTTON_FONT = new Font("Arial", Font.BOLD, 14);
        }
    }

    private void configureTextArea(JTextArea textArea) {
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setFocusable(false);
        textArea.setFont(BODY_FONT);
        textArea.setBackground(Color.WHITE);
        textArea.setAlignmentX(Component.LEFT_ALIGNMENT);
        textArea.setMaximumSize(new Dimension(400, 70));
    }

    private void createFooterSection() {
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new BoxLayout(footerPanel, BoxLayout.Y_AXIS));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new EmptyBorder(40, 40, 40, 40));
        footerPanel.setMaximumSize(new Dimension(800, 200));

        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(230, 230, 230));
        separator.setMaximumSize(new Dimension(720, 1));
        separator.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.X_AXIS));
        contentPanel.setBackground(Color.WHITE);
        contentPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        contentPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // Contact information
        JPanel contactPanel = new JPanel();
        contactPanel.setLayout(new BoxLayout(contactPanel, BoxLayout.Y_AXIS));
        contactPanel.setBackground(Color.WHITE);

        JLabel contactLabel = new JLabel("Contact:");
        contactLabel.setFont(new Font("Arial", Font.BOLD, 14));
        contactLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel emailLabel = new JLabel("ecospark@gmail.com");
        emailLabel.setFont(BODY_FONT);
        emailLabel.setAlignmentX(Component.LEFT_ALIGNMENT);
        emailLabel.setBorder(new EmptyBorder(5, 0, 15, 0));

        JLabel inquiriesLabel = new JLabel("General Inquiries:");
        inquiriesLabel.setFont(new Font("Arial", Font.BOLD, 14));
        inquiriesLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel phoneLabel = new JLabel("385-371-9236");
        phoneLabel.setFont(BODY_FONT);
        phoneLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        contactPanel.add(contactLabel);
        contactPanel.add(emailLabel);
        contactPanel.add(inquiriesLabel);
        contactPanel.add(phoneLabel);

        // Quick links
        JPanel linksPanel = new JPanel();
        linksPanel.setLayout(new BoxLayout(linksPanel, BoxLayout.Y_AXIS));
        linksPanel.setBackground(Color.WHITE);
        linksPanel.setBorder(new EmptyBorder(0, 100, 0, 0));

        JLabel linksLabel = new JLabel("Quick Links:");
        linksLabel.setFont(new Font("Arial", Font.BOLD, 14));
        linksLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        String[] linkLabels = {"Home", "Contact", "About Us", "Courses", "Resources"};
        for (String label : linkLabels) {
            JLabel link = new JLabel(label);
            link.setFont(BODY_FONT);
            link.setForeground(TEXT_COLOR);
            link.setAlignmentX(Component.LEFT_ALIGNMENT);
            link.setBorder(new EmptyBorder(5, 0, 5, 0));
            link.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            linksPanel.add(link);
        }

        // Social Media Icons
        JPanel socialPanel = new JPanel();
        socialPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        socialPanel.setBackground(Color.WHITE);

        String[] socialIcons = {"Facebook", "Twitter", "Instagram", "LinkedIn"};
        for (String social : socialIcons) {
            JButton socialButton = new JButton(social);
            socialButton.setFont(new Font("Arial", Font.PLAIN, 12));
            socialButton.setFocusPainted(false);
            socialButton.setContentAreaFilled(false);
            socialButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            socialPanel.add(socialButton);
        }

        contentPanel.add(contactPanel);
        contentPanel.add(linksPanel);
        contentPanel.add(Box.createHorizontalGlue());
        contentPanel.add(socialPanel);

        // Copyright
        JPanel copyrightPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        copyrightPanel.setBackground(Color.WHITE);

        JLabel copyrightLabel = new JLabel("© 2025 EcoSpark. All rights reserved.");
        copyrightLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        copyrightPanel.add(copyrightLabel);

        footerPanel.add(separator);
        footerPanel.add(contentPanel);
        footerPanel.add(copyrightPanel);

        mainPanel.add(footerPanel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new EcoSparkTest();
            }
        });
    }
}