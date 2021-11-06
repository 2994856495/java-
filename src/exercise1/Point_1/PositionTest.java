package exercise1.Point_1;

import org.junit.Test;
import java.util.Scanner;

public class PositionTest {

    @Test
    public void test1(){
        Scanner in =new Scanner(System.in);
        int[][] point=new int[3][2];

        for (int i=0;i<point.length-1;i++) {
            char ch0= (char) (i + 'a');
            System.out.println("点"+ch0+"的坐标为：");
            System.out.print("请输入坐标x:");
            point[i][0] = in.nextInt();
            System.out.print("请输入坐标y:");
            point[i][1] = in.nextInt();
        }

        Position position1=new Position(point[0][0],point[0][1]);
        Position position2=new Position(point[1][0],point[1][1]);
        Position position3=new Position().scale(2);
        //f默认为2

        System.out.println();
        System.out.println("position1点与position2点之间的距离为"+position1.distance(position2));
        System.out.println("position1点与position3点之间的距离为"+position1.distance(position3));
        System.out.println("position2点与position3点之间的距离为"+position2.distance(position3));

        in.close();
    }

    @Test
    public void test2(){
        Foo foo1=new Foo(1);
        Foo foo2=new Foo(foo1);
        //toString方法已重写
        System.out.println("foo1:"+foo1.toString()+"   foo2:"+foo2.toString());
    }

}
