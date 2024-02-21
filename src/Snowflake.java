import java.awt.*;

class Snowflake {
    private int x;
    int y;

    public Snowflake(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(x, y, 5, 5);
    }

    public void fall(int dy) {
        y = y + dy;
    }
}