import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class LoginPanel {

    private JPanel mainPanel;
    private CardLayout cardLayout;

    public LoginPanel(JPanel mainPanel, CardLayout cardLayout) {
        this.mainPanel = mainPanel;
        this.cardLayout = cardLayout;
        createLoginPanel();
    }

    private void createLoginPanel() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));
        loginPanel.setBorder(BorderFactory.createEmptyBorder(30, 50, 30, 50));

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

        // Buttons panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register");
        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        // Status message
        JLabel statusLabel = new JLabel(" ");
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Add login action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());

                if (email.isEmpty() || password.isEmpty()) {
                    statusLabel.setText("Please enter email and password.");
                    statusLabel.setForeground(Color.RED);
                    return;
                }

                try {
                    // Sending login data as form parameters
                    String response = sendPostRequest("/users/login", email, password);

                    if (response != null && response.contains("Login successful")) {
                        // If login is successful, navigate to dashboard
                        statusLabel.setText("Login successful! Redirecting...");
                        statusLabel.setForeground(Color.GREEN);

                        // Clear fields
                        emailField.setText("");
                        passwordField.setText("");

                        // Switch to dashboard after a short delay
                        Timer timer = new Timer(2000, new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e1) {
                                cardLayout.show(mainPanel, "dashboard");
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();
                    } else {
                        statusLabel.setText("Login failed: " + response);
                        statusLabel.setForeground(Color.RED);
                    }
                } catch (Exception ex) {
                    statusLabel.setText("Error: " + ex.getMessage());
                    statusLabel.setForeground(Color.RED);
                    ex.printStackTrace();
                }
            }
        });

        // Add register action
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

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

        // Add to main panel
        mainPanel.add(loginPanel, "login");
    }

    // Simplified POST request to send login data
    private String sendPostRequest(String endpoint, String email, String password) throws Exception {
        // Example of sending login data without JSON using form parameters
        String urlString = "http://example.com" + endpoint; // Replace with your backend URL
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);

        // Send form parameters
        String params = "email=" + email + "&password=" + password;
        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = params.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }

        // Read response
        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString(); // Return the response from the server
    }
}
