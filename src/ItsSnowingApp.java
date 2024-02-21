import javax.swing.*;

/**
 * Main class "ItsSnowingApp" shows the output of the snowflakes
 */
public class ItsSnowingApp extends JFrame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Its Snowing!");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);

            SnowflakePanel snowflakePanel = new SnowflakePanel();
            frame.add(snowflakePanel);

            frame.setVisible(true);
        });
    }
}