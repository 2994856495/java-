package exercise03.java04.client_server;

import exercise03.java04.GUI.MenuGUI;
import exercise03.java04.jdbc.Sql;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.Properties;

class SingleServer implements Runnable {

    private Socket socket;
    private int clientNo;

    public SingleServer(Socket socket, int clientNo) {
        this.socket = socket;
        this.clientNo = clientNo;
    }
    public void check(String sqlStr,String[] data,String[] FIELDNAMES){
        int count=0;
        for (String value : data) {
            if (value!=null&&value.trim().length() != 0) {
                sqlStr += FIELDNAMES[count] + " = \"" + value + "\" and ";
                count++;
                System.out.println(value);
            }
        }
        sqlStr +=" \"1\"=\"1\";";
        System.out.println(sqlStr);
        new TableGUI().drawTableGUI(sqlStr,0);
    }
    private static String[] FIELDNAMES={"p.pet_name","p.pet_species","p.host_name","p.host_tele"};
    @Override
    public void run() {
        BufferedReader inputStream = null;
        try {
            inputStream = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedWriter outputStream = null;
        try {
            outputStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            String fileName = null;
            assert inputStream != null;

            try {

                fileName = inputStream.readLine();

            } catch (IOException e) {
                e.printStackTrace();
            }
            assert fileName != null;
            File file = new File(fileName);
            while (file.exists()){
                Properties prop = Property.read(fileName);
                int type= Integer.parseInt(prop.getProperty("type"));
                String sql= prop.getProperty("sql");
                String[] data = new String[Integer.parseInt(prop.getProperty("length"))];
                String t="data";
                for (int i=0;i<data.length;i++){
                    String m=t+i;
                    data[i]= String.valueOf(prop.getProperty(m));
                }
                if (type==0) {
                    check(sql,data, FIELDNAMES);

                }
                else if(type==1){
                    int i = exercise03.java04.jdbc.Sql.excuteUpdateSQL(sql, data);
                    if (i!=0){
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "插入成功", "提示框", JOptionPane.QUESTION_MESSAGE);
                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "插入失败", "提示框", JOptionPane.QUESTION_MESSAGE);
                    }

                }
                else if (type==2){
                    String textArea = prop.getProperty("JTextArea");
                    int updateSQL = Sql.excuteUpdateSQL(sql,String.valueOf(new Date()), textArea, data[0],data[1]);
                    if (updateSQL!=0){
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "添加成功", "提示框", JOptionPane.QUESTION_MESSAGE);
                    }

                }
                else if(type==3){
                    ResultSet resultSet = Sql.executeQuerySQL(sql,data);
                    try {
                        assert resultSet != null;
                        if (resultSet.next()) {
                            new MenuGUI().menuGraphic();
                        }
                        else {
                            Toolkit.getDefaultToolkit().beep();
                            JOptionPane.showMessageDialog(null, "登录失败", "提示框", JOptionPane.QUESTION_MESSAGE);
                            new MenuGUI().menuGraphic();
                        }

                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    finally {
                        if (resultSet!=null){
                            try {
                                resultSet.close();
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                }
                else if (type==4){
                    System.out.println(type);
                    new TableGUI().drawTableGUI(sql,type);
                }
                else if(type==6){
                    if(Sql.excuteUpdateSQL(sql,data)==0){
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "注册失败", "提示框", JOptionPane.QUESTION_MESSAGE);
                    }
                    else{
                        Toolkit.getDefaultToolkit().beep();
                        JOptionPane.showMessageDialog(null, "注册成功", "提示框", JOptionPane.QUESTION_MESSAGE);
                    }
                }
                if (outputStream != null) {
                    try {
                        outputStream.flush();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                Property.delete(fileName);
            }
        }
    }
}
