package exercise2.strikeback.product;

import java.awt.*;

public interface Mover {
    public void draw(Graphics graphics);
    public void setMovementVector(int xIncrement, int yIncrement);
}
