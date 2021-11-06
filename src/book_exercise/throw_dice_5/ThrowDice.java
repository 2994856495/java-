package book_exercise.throw_dice_5;

import java.util.Random;

public class ThrowDice {
    public static void main(String[] args) {
        Random random = new Random();

        int firstResult;
        int secondResult;
        int count=0;
        while (true){
            firstResult= random.nextInt(6);
            System.out.println("The first die comes up "+firstResult);
            secondResult= random.nextInt(6);
            System.out.println("The second die comes up "+secondResult);
            System.out.println("***************************************");
            count++;
            if (firstResult==1&&firstResult==secondResult){
                System.out.println("至少要抛 "+count+"次");
                break;
            }
        }
    }

}
