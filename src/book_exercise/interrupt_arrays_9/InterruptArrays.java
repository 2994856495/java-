package book_exercise.interrupt_arrays_9;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InterruptArrays {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        int[] array;
        System.out.println("输入数的个数与差值：");
        int maxCapacity= 0;
        int differNum= 0;
        try {
            maxCapacity = in.nextInt();
            differNum = in.nextInt();
        } catch (InputMismatchException e) {
            e.printStackTrace();
            System.out.println("输入错误！");
        }
        array = new int[maxCapacity];
        System.out.print("输入"+maxCapacity+"个整数：");
        for (int i=0;i<maxCapacity;i++){
            try {
                array[i]=in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误！");
            }
        }
        System.out.println();
        for(int i=0;i<maxCapacity-1;i++){
            if((array[i+1]-array[i])>differNum){
                System.out.println("数列从第"+(i+1)+"个数开始中断");
                System.exit(0);
            }
        }
        System.out.println("这是"+maxCapacity+"个数组成的连续数列"+",差值是"+differNum);
        // System.out.println("这"+maxCapacity+"个数字组成的连续数列，差值为"+differNum);

    }
}
