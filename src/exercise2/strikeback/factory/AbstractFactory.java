package exercise2.strikeback.factory;

import exercise2.strikeback.product.Bouncer;
import exercise2.strikeback.product.Mover;
import exercise2.strikeback.product.Sprite;
import exercise2.strikeback.product.StraightMover;

import java.awt.*;

public abstract class AbstractFactory {
    public abstract Sprite createSprite(Color color,int...a);
    public abstract StraightMover createStraightMover(int startX,int startY,Sprite sprite);
    public abstract Mover createMover(int startX,int startY,Sprite sprite,Class clazz);
    public abstract Bouncer createBouncer(int startX, int startY, Sprite sprite);

}
