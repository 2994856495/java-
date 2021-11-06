package book_exercise.guess_fist_7;

import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class GuessFist {
    /**石头剪刀布
     * */
    static final int SCISSORS=0;
    static final int CLOTH=1;
    static final int STONE=2;

    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        Random random = new Random();
        String[] choices={"剪刀","布","石头"};
        int robotResult= random.nextInt(3);
        String robotChoice=choices[robotResult];
        System.out.println("剪刀 0，布 1，石头 2");
        System.out.println("请输入你的选择：");
        int personResult= 0;
        try {
            personResult = in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误,程序退出");
            System.exit(0);
        }
        System.out.println("机器出的是："+robotChoice);
        if(personResult>STONE||personResult<SCISSORS){
            System.out.println("输入错误，程序退出");
            System.exit(0);
        }
        if((personResult==SCISSORS&&robotResult==CLOTH)||(personResult==CLOTH&&robotResult==STONE)||(personResult==STONE&&robotResult==SCISSORS)){
            System.out.println("胜利");
        }
        else if(personResult==robotResult){
            System.out.println("平局");
        }
        else{
            System.out.println("失败");
        }
        in.close();

    }

}
