package exercise2.strikeback.product;

import java.awt.*;

public class Oval implements Sprite {
    private int x;
    private int y;
    private int width;
    private int height;
    private Color color;


    /**
     * Create a box that has dimensions width and height, filled with
     * startColor.
     */
    public Oval(int x, int y,int width,int height, Color color) {
        this.x = x;
        this.y = y;
        this.width=width;
        this.height=height;
        this.color = color;
    }


    @Override
    public void draw(Graphics surface, int x, int y) {
        // Draw the object

        surface.setColor(color);
        surface.fillOval(x, y, width, height);
        surface.setColor(Color.BLACK);
        ((Graphics2D) surface).setStroke(new BasicStroke(3.0f));
        surface.drawOval(x,y,width,height);
        // surface.drawLine(x, y, length, slope);
    }


    @Override
    public int getLength() {
        return width;
    }


    @Override
    public int getHeight() {
        return height;
    }
}