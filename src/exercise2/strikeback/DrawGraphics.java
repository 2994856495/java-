package exercise2.strikeback;

import exercise2.strikeback.factory.AbstractFactory;
import exercise2.strikeback.factory.OvalFactory;
import exercise2.strikeback.factory.RectangleFacyory;
import exercise2.strikeback.product.*;
import exercise2.strikeback.product.Rectangle;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**1）创建一个新类实现Sprite接口并画不同图形（非矩形）在窗口里跳动。更改DrawGraphics类，提供一个Bouncer的ArrayList，添加一个矩形和一个其他图形到ArrayList并且让它们移动至窗口边缘时会弹跳。
 实现步骤：

 4.  创建一个新的图形类 (例如，箭头)。
 5.  该图形类实现Sprite interface并实现接口所有的方法，目前只需要用大括号假实现。
 6.  用新图形类的对象代替DrawGraphics 里的Rectangles，此时ArrayList只包含一个Rectangle ，运行程序也仅仅看见一个Rectangle。
 7. 添加代码实现新图形的 draw()方法，如果图形不能正确跳动，需要从getWidth()和getHeight()返回正确的width和height。
 9. 最后运行程序可以看见两个矩形和一个新图形在窗口中移动，至边缘时可以弹跳至方框内。还可以添加一些不同的图形。
 */
public class DrawGraphics {
    ArrayList<Bouncer> movingSprite=new ArrayList<Bouncer>();
    ArrayList<StraightMover> straightMovers =new ArrayList<StraightMover>();
    ArrayList<Mover> movers=new ArrayList<Mover>();
    //Bouncer movingSprite;

    /** Initializes this class for drawing. */
    public DrawGraphics() {

        AbstractFactory factory=null;
        factory=new RectangleFacyory();
        Rectangle rectangle0= (Rectangle) factory.createSprite(Color.black,25,20);
        StraightMover straightMover1 = (StraightMover) factory.createMover(200,150,rectangle0,StraightMover.class);
        straightMover1.setMovementVector(3, 1);

        Rectangle rectangle1 = (Rectangle) factory.createSprite(Color.red,25,20);
        Bouncer bouncer1 = (Bouncer) factory.createMover(100,170,rectangle1,Bouncer.class);
        bouncer1.setMovementVector(3, 1);

        Rectangle rectangle2 = (Rectangle) factory.createSprite(Color.CYAN,35,20);
        Bouncer bouncer2 = (Bouncer) factory.createMover(10,10,rectangle2,Bouncer.class);
        bouncer2.setMovementVector(20, 11);

        factory=new OvalFactory();
        Oval oval1= (Oval) factory.createSprite(Color.BLUE,20,23,30,23);
        StraightMover straightMover2 = (StraightMover) factory.createMover(220,134,oval1,StraightMover.class);
        straightMover2.setMovementVector(1,1);

        Oval oval= (Oval) factory.createSprite(Color.GREEN,20,13,21,13);
        Bouncer bouncer3= (Bouncer) factory.createMover(120,134,oval,Bouncer.class);
        bouncer3.setMovementVector(2,1);

        movers.add(straightMover1);
        movers.add(straightMover2);
        movers.add(bouncer1);
        movers.add(bouncer2);
        movers.add(bouncer3);

    }

    /** Draw the contents of the window on surface. */
    public void draw(Graphics surface) {
        Iterator<Mover> iterator = movers.iterator();
        while (iterator.hasNext()){
            Mover next = iterator.next();
            next.draw(surface);
        }
    }
}
