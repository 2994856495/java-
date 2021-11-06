package exercise03.java04.client_server;

import javax.swing.*;
import java.io.*;
import java.util.Properties;

public class Property {

    public static Properties read(String  fileName){
        Properties prop = new Properties();
        InputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            prop.load(in);     ///加载属性列表
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert in != null;
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
    public static void delete(String fileName){
        File file = new File(fileName);
        file.delete();
    }
    public static void write(String  fileName, int type, int length, String sql, String[] data, JTextArea jTextArea){
        Properties prop = new Properties();
        File file=new File(fileName);
        if (!file.exists()){
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            file.delete();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream oFile = null;//true表示追加打开
        try {
            oFile = new FileOutputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        prop.setProperty("type", String.valueOf(type));
        prop.setProperty("sql",sql);
        prop.setProperty("length", String.valueOf(length));
        if (jTextArea!=null) {
            prop.setProperty("JTextArea",jTextArea.getText());
        }
        String t="data";
        for (int i=0;i<data.length;i++){
            String m=t+i;
            prop.setProperty(m,data[i]);
        }

        try {
            prop.store(oFile, "The New properties file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            assert oFile != null;
            oFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
