package book_exercise.display_month_6;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DisplayMonth {
    public static void main(String[] args) {
        String[] monthName={"January","February","March","April","May","June",
                "July","August","September","October","November","December"};

        Scanner in=new Scanner(System.in);
        int year=0,month=0;
        try {
            System.out.print("Input year:");
            year=in.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("输入错误！");

        }

        int[] days=new int[]{31,28,31,30,31,30,31,31,30,31,30,31};
        if((year%4==0&&year%100!=0)||(year%400==0)){
            days[1]=29;
        }

        while (true){
            try {
                System.out.print("Input month:");
                month=in.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("输入错误！");

            }
            if(month<1||month>12){
                System.out.println("The input is incorrect");
            }
            else{
                break;
            }
        }
        System.out.println(monthName[month-1]+","+year+" has "+days[month-1]+" days");
        in.close();
    }

}
