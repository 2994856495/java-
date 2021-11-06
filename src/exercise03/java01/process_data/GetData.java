package exercise03.java01.process_data;
import javax.swing.*;

public class GetData {
    public static String[] getData(JTextField[] jTextFields){
        String[] text=new String[jTextFields.length];
        String temp;
        for(int i=0;i<jTextFields.length;i++){
            temp = jTextFields[i].getText().trim();
            text[i]=temp;
        }
        return text;
    }
}
