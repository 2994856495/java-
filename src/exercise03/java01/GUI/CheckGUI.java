package exercise03.java01.GUI;

import exercise03.java01.process_data.GetData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *查询功能：可以根据文本框里填入的家长姓名或者电话显示查询的宠物信息，包括就诊记录，
 * 如果没有查找到，应弹出对话框显示无此记录。
 * @author ASUS
 */
public class CheckGUI {
    static final JFrame FRAME = new JFrame("查询功能");
    private static final int LENGTH=4;
    public static JTextField[]  jTextFields;
    private static final String[] FIELDNAMES={"p.pet_name","p.pet_species","p.host_name","p.host_tele"};
    public static void checkGraphics(){
        jTextFields = new JTextField[LENGTH];
        FRAME.setSize(325,400);
        String[] names= {"宠物姓名","宠物种类","主人姓名","主人电话"};
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JButton[] jButtons= new JButton[LENGTH];
        for(int i=0;i<LENGTH;i++){
            jButtons[i]=new JButton(names[i]);
            jTextFields[i]=new JTextField();
            jButtons[i].setBounds(50,(i+1)*40,100,20);
            jTextFields[i].setColumns(10);
            jTextFields[i].setBounds(175,(i+1)*40,100,20);
            jPanel.add(jButtons[i]);
            jPanel.add(jTextFields[i]);
        }
        JButton add = new JButton("添加");
        add.setBounds(20,320,80,20);
        JButton cancel = new JButton("取消");
        cancel.setBounds(120,320,80,20);
        cancel.addActionListener(new Cancel());
        JButton exit = new JButton("退出");
        exit.setBounds(220,320,80,20);
        exit.addActionListener(new Exit());
        add.addActionListener(new Start());
        jPanel.add(add);
        jPanel.add(cancel);
        jPanel.add(exit);
        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);

    }

    public static class Start implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String[] s = GetData.getData(jTextFields);
            if(s.length == 0){
                System.out.println("sserror!!!!");
                return;
            }
            String sql="select p.pet_name,pet_species,pet_birthday,pet_variety,pet_weigth\n" +
                    ",p.host_name,p.host_tele,check_record,check_time " +
                    "from pet p INNER join `check` c on  p.host_name=c.pet_name where ";
            int count=0;
            for (String value : s) {
                if (value.trim().length() != 0) {
                    sql += FIELDNAMES[count] + " = \"" + value + "\" and ";
                    count++;
                }
            }
            sql+=" \"1\"=\"1\";";
            System.out.println(sql);
            FRAME.dispose();
            new TableGUI().drawTableGUI(sql);
        }
    }

    public static class Exit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            MenuGUI.menuGraphic();
        }
    }

    public static class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(JTextField j:jTextFields){
                j.setText("");
            }
        }
    }
}
