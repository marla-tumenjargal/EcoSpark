import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

class SnowflakePanel extends JPanel implements ActionListener {
    private ArrayList<Snowflake> snowflakes;
    private Timer timer;
    private Random random;

    public SnowflakePanel() {
        setBackground(Color.WHITE);
        snowflakes = new ArrayList<>();
        timer = new Timer(50, this);
        random = new Random();
        timer.start();

    }

    public void addRandomSnowflake() {
        int x = random.nextInt(getWidth());
        int y = -5;
        snowflakes.add(new Snowflake(x, y));
    }

    public void moveSnowflakes() {
        for (Snowflake snowflake : snowflakes) {
            snowflake.fall(1);
            if (snowflake.y > getHeight()) {
                snowflakes.remove(snowflake);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Snowflake snowflake : snowflakes) {
            snowflake.draw(g);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        addRandomSnowflake();
        moveSnowflakes();
        repaint();
    }
}