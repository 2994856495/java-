package exercise2.strikeback.factory;

import exercise2.strikeback.product.*;
import exercise2.strikeback.product.Rectangle;

import java.awt.*;

public class RectangleFacyory extends AbstractFactory{
    @Override
    public Sprite createSprite(Color color,int... a) {
        return new Rectangle(a[0],a[1],color);
    }
    @Override
    public StraightMover createStraightMover(int startX, int startY, Sprite sprite) {
        return new StraightMover(startX,startY,sprite);
    }
    @Override
    public Bouncer createBouncer(int startX, int startY, Sprite sprite) {
        return new Bouncer(startX,startY,sprite);
    }

    @Override
    public Mover createMover(int startX, int startY, Sprite sprite, Class clazz) {
        if(clazz ==Bouncer.class){
            return createBouncer(startX,startY,sprite);
        }
        else if(clazz==StraightMover.class){
            return createStraightMover(startX,startY,sprite);
        }
        else{
            return null;
        }

    }

}
