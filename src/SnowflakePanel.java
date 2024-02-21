import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * SnowFlakePanel class
 */
class SnowflakePanel extends JPanel implements ActionListener {
    private ArrayList<Snowflake> snowflakes;
    private Timer timer;
    private Random random;

    /**
     * Constructor, constrcuts a new instance of a Snowflake Panel
     */
    public SnowflakePanel() {
        setBackground(Color.WHITE);
        snowflakes = new ArrayList<>();
        timer = new Timer(50, this);
        random = new Random();
        timer.start();

    }

    /**
     * Adds a new snowflake
     */
    public void addRandomSnowflake() {
        int x = random.nextInt(getWidth());
        int y = -5;
        snowflakes.add(new Snowflake(x, y));
    }

    /**
     * moveSnowFlakes()
     */
    public void moveSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.fall(1);
            if (snowflake.y > getHeight()) {
                snowflakes.remove(snowflake);
            }
        }
    }

    /**
     * overridden paintComponent() method
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Snowflake snowflake : snowflakes) {
            snowflake.draw(g);
        }
    }

    /**
     * overridgen actionPerformed() method
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        addRandomSnowflake();
        moveSnowflakes();
        repaint();
    }
}