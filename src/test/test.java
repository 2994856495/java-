package test;

import org.junit.Test;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class test {
    public static long concurrentTime1, concurrentTime2, concurrentMemory1, concurrentMemory2;

    public static void start() {
        //得到程序开始时的系统时间（纳秒级，最终转化毫秒，保留小数点后两位）
        concurrentTime1 = System.nanoTime();
        //得到虚拟机运行、程序开始执行时jvm所占用的内存。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory1 = runtime.totalMemory()-runtime.freeMemory();
    }

    public static void end() {
        //得到程序执行完毕时的系统时间（毫秒级）
        concurrentTime2 = System.nanoTime();
        //得到虚拟机运行、所要测试的执行代码执行完毕时jvm所占用的内存（byte）。
        Runtime runtime = Runtime.getRuntime();
        concurrentMemory2 = runtime.totalMemory()-runtime.freeMemory();

        //计算start和end之间的代码执行期间所耗时间(ms)与内存(M)。
        // 1毫秒(ms) = 1000微秒(us) = 1000 000纳秒(ns)
        // 1M = 1*2^20 byte = 1024 * 1024 byte;
        long time=concurrentTime2-concurrentTime1;
//        String time = String.valueOf((double)(concurrentTime2 - concurrentTime1));
        long memory = (concurrentMemory2-concurrentMemory1) ;
        System.out.println("---------------您的代码执行时间为：" + time+ " ns, 消耗内存：" + memory + " byte + !---------------");
    }


    @Test
    public void test1(){
        int port = 6000;
        String host = "127.0.0.1";
        // 创建一个套接字并将其连接到指定端口号
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            Toolkit.getDefaultToolkit().beep();
            JOptionPane.showMessageDialog(null, "请连接服务器", "提示框", JOptionPane.QUESTION_MESSAGE);
            e.printStackTrace();
        }


        BufferedWriter outputStream = null;
        try {
            if (socket != null) {
                outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader inputStream=null;
        try {
            if (socket != null) {
                inputStream=new BufferedReader(new InputStreamReader(socket.getInputStream()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



        try {
            if (socket != null) {
                socket.close();
            }
            if (outputStream!=null){
                outputStream.close();
            }
            if (inputStream!=null){
                inputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
//在此处输入测试代码段
//         Examination.end();
//         其中end方法会输出代码段的时间和内存。其中要注意的是以下几行代码的执行本身就要消耗一定的时间：
//
//         concurrentTime1 = System.nanoTime();
//         Runtime runtime = Runtime.getRuntime();
//         concurrentMemory1 = runtime.totalMemory()-runtime.freeMemory();
//
//         concurrentTime2 = System.nanoTime();
//         Runtime runtime = Runtime.getRuntime();
//         concurrentMemory2 = runtime.totalMemory()-runtime.freeMemory();
