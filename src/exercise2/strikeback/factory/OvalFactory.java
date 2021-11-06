package exercise2.strikeback.factory;

import exercise2.strikeback.product.*;

import java.awt.*;

public class OvalFactory extends AbstractFactory{
    @Override
    public Sprite createSprite(Color color,int...a) {
        return new Oval(a[0],a[1],a[2],a[3], color);
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
