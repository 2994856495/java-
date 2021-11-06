package book_exercise.throw_dice_4;

import java.util.Random;

public class ThrowTwice {
    public static void main(String[] args) {
        /**
         * 扔色子游戏，一人两次，求平均值
         * */
        int result;
        Random random = new Random();
        result= random.nextInt(6);
        System.out.println("The first die comes up "+result);
        int result2;
        result2= random.nextInt(6);
        System.out.println("The second die comes up "+result2);
        int total=(result+result2);
        System.out.println("Your total roll is "+total);
        System.out.println("The average die comes up "+total/2);

    }
}
