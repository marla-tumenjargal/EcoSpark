import java.awt.*;

/**
 * Snowflake class, creates new snowflakes
 */
class Snowflake {
    private int x;
    int y;

    /**
     * Constructor, constructs a new instance of a snowflaek
     * @param x, x
     * @param y, y
     */
    public Snowflake(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * draws a new snowflake
     * @param g, g
     */
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 5, 5);
    }

    /**
     * fall
     * @param dy, dy
     */
    public void fall(int dy) {
        y = y + dy;
    }
}