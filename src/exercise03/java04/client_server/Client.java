package exercise03.java04.client_server;

import exercise03.java04.GUI.LoginGUI;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        LoginGUI gui = new LoginGUI();
        gui.drawLogin();
    }
    private static String fileName="src\\exercise03\\java04\\client_server\\"+Client.class+System.nanoTime()  +".properties";
    private static void main_()  {
        int port = 7000;
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
        File file=new File(fileName);
        if (file.exists()) {
            try {
                assert outputStream != null;
                outputStream.write(fileName+"\n");
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public static void sendMessage(int type, String sqlStr, String[] data, JTextArea jTextArea){
        Property.write(fileName,type,data.length,sqlStr,data,jTextArea);
        main_();
    }
}
