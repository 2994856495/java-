package exercise03.java04.GUI;

import exercise03.java04.process_data.GetData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import static exercise03.java04.client_server.Client.sendMessage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * 1）添加宠物信息功能：创建文本框输入宠物名字、种类（猫狗或者其他）、生日、品种、体重、家长姓名电话，
 * 当点击Add按钮时，将信息添加到数据库里，并且具备更新表格，显示新添加的记录。
 */
public class InfoGUI {
    private String[] data;
    private String sqlStr;
    private  int INFOGUI=-1;
    private  final JFrame FRAME = new JFrame("添加宠物信息");
    private  final int COUNT=7;
    private   static JLabel check;
    private   JTextField[] jTextFields;
    private  JButton[] jButtons;
    private  JPanel jPanel ;

    public  void  infoGraphics(){
        INFOGUI=1;
        check = new JLabel("所有均为必填项且体重号码为数字(不能填充空格)");
        check.setBounds(100,20,200,20);
        check.setFont(new Font("微软雅黑", Font.BOLD, 9));
        check.setForeground(Color.red);
        check.setVisible(false);

        FRAME.setSize(325,400);
        String[] names= {"宠物名字","宠物种类","宠物生日","宠物品种","宠物体重",
                "主人姓名","主人电话"};
        jPanel=new JPanel();
        jPanel.setLayout(null);
        jButtons= new JButton[COUNT];
        jTextFields= new JTextField[COUNT];
        for(int i=0;i<COUNT;i++){
            jButtons[i]=new JButton(names[i]);
            jTextFields[i]=new JTextField();
            jButtons[i].setBounds(50,(i+1)*40,100,20);
            jTextFields[i].setColumns(10);
            jTextFields[i].setBounds(175,(i+1)*40,100,20);
            jPanel.add(jButtons[i]);
            jPanel.add(jTextFields[i]);
        }
        FRAME.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        jTextFields[4].addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key=e.getKeyChar();
                if(!(key>='0'&&key<='9')){
                    e.consume();
                }
            }
        });
        jTextFields[COUNT-1].addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char key=e.getKeyChar();
                if(!(key>='0'&&key<='9')){
                    e.consume();
                }
            }
        });
        JButton add = new JButton("添加");
        add.addActionListener(new Add());
        add.setBounds(20,320,80,20);
        JButton cancel = new JButton("取消");
        cancel.setBounds(120,320,80,20);
        cancel.addActionListener(new Cancel());
        JButton exit = new JButton("返回");
        exit.setBounds(220,320,80,20);
        exit.addActionListener(new Exit());
        jPanel.add(add);
        jPanel.add(check);
        jPanel.add(cancel);
        jPanel.add(exit);
        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);
    }
    public  class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            for(JTextField j:jTextFields){
                j.setText("");
            }
        }
    }

    private  class Add implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            infoStart();
        }
    }
    private  void infoStart(){
        data = GetData.getData(jTextFields);
        if(data.length < COUNT){
            check.setVisible(true);
            return;
        }
        check.setVisible(false);
        for (String s1:data){
            if(s1==null||s1.trim().length()==0){
                check.setVisible(true);
                break;
            }
            sqlStr="insert into pet(pet_name,pet_species,pet_birthday,pet_variety" +
                    ",pet_weigth,host_name,host_tele" +
                    ") values(?,?,?,?,?,?,?)";

            INFOGUI=1;
            sendMessage(INFOGUI,sqlStr,data,null);
            break;

        }

    }
    private class Exit  implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            INFOGUI=-1;
            FRAME.dispose();
            new MenuGUI().menuGraphic();
        }
    }

}
