import javax.swing.*;
import java.awt.*;

class BresenhamCircle {
    private int xc, yc, r;

    // Constructor
    public BresenhamCircle(int xc, int yc, int r) {
        this.xc = xc;
        this.yc = yc;
        this.r = r;
    }

    // Draw circle using Bresenham algorithm
    public void drawCircle(Graphics g) {
        int x = 0, y = r;
        int d = 3 - 2 * r;

        while (x <= y) {
            plotPoints(g, x, y);

            if (d < 0) {
                d = d + 4 * x + 6;
            } else {
                d = d + 4 * (x - y) + 10;
                y--;
            }
            x++;
        }
    }

    // Plot 8 symmetric points
    private void plotPoints(Graphics g, int x, int y) {
        g.fillRect(xc + x, yc + y, 1, 1);
        g.fillRect(xc - x, yc + y, 1, 1);
        g.fillRect(xc + x, yc - y, 1, 1);
        g.fillRect(xc - x, yc - y, 1, 1);
        g.fillRect(xc + y, yc + x, 1, 1);
        g.fillRect(xc - y, yc + x, 1, 1);
        g.fillRect(xc + y, yc - x, 1, 1);
        g.fillRect(xc - y, yc - x, 1, 1);
    }
}

class CirclePattern extends JPanel {

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Outer Circle
        new BresenhamCircle(250, 200, 120).drawCircle(g);

        // Inner Circle
        new BresenhamCircle(250, 200, 50).drawCircle(g);

        // Triangle inside
        drawTriangle(g, 250, 100, 150, 300, 350, 300);
    }

    // Simple line drawing for triangle (using Graphics)
    private void drawTriangle(Graphics g, int x1, int y1, int x2, int y2, int x3, int y3) {
        g.drawLine(x1, y1, x2, y2);
        g.drawLine(x2, y2, x3, y3);
        g.drawLine(x3, y3, x1, y1);
    }
}

public class BresenhamCircleDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bresenham Circle Drawing");
        CirclePattern panel = new CirclePattern();

        frame.add(panel);
        frame.setSize(500, 450);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
