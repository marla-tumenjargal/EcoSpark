import javax.swing.*;

public class ItsSnowingApp extends JFrame {
    public ItsSnowingApp() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);

        SnowflakePanel snowflakePanel = new SnowflakePanel();
        add(snowflakePanel);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ItsSnowingApp());
    }
}