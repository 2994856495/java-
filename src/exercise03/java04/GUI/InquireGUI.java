package exercise03.java04.GUI;

import exercise03.java04.process_data.GetData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static exercise03.java04.client_server.Client.sendMessage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * 宠物就诊功能：创建文本框添加就诊时间、就诊记录。
 */
public class InquireGUI {
    private   int INQUIREGUI=-1;
    private String[] data;
    private String sqlStr;
    private   JFrame jFrame=new JFrame("就诊功能");
    private  JTextArea jTextArea;
    private   final int LENGTH=2;
    public  JTextField[] jTextFields;
    private  JLabel check;
    public  void inquireGraphics(){
        INQUIREGUI=-1;
        check=new JLabel("所有均为必填项(不能填充空格)");
        check.setFont(new Font("微软雅黑", Font.BOLD, 10));
        check.setBounds(80,0,150,50);
        check.setForeground(Color.red);
        check.setVisible(false);

        jTextFields=new JTextField[LENGTH];
        jFrame.setSize(320,400);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JButton jButton=new JButton("宠物姓名");
        jTextFields[0]=new JTextField();
        jButton.setBounds(50,50,100,20);
        jTextFields[0].setColumns(10);
        jTextFields[0].setBounds(175,50,100,20);
        jPanel.add(jButton);
        jPanel.add(jTextFields[0]);
        JButton jButton1=new JButton("主人电话");
        jTextFields[1]=new JTextField();
        jButton1.setBounds(50,100,100,20);
        jTextFields[1].setColumns(10);
        jTextFields[1].setBounds(175,100,100,20);
        jPanel.add(jButton1);
        jPanel.add(jTextFields[1]);

        JButton jButton2 = new JButton("就诊信息");
        jTextArea = new JTextArea(9,10);
        jTextArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(jTextArea);
        Dimension size = jTextArea.getPreferredSize();
        jButton2.setBounds(50,150,100,20);
        scrollPane.setBounds(175,150,size.width,size.height);
        jPanel.add(jButton2);
        jPanel.add(scrollPane);

        JButton add = new JButton("添加");
        add.setBounds(10,320,80,20);
        add.addActionListener(new Add());
        JButton cancel = new JButton("取消");
        cancel.setBounds(110,320,80,20);
        JButton exit = new JButton("退出");
        exit.setBounds(210,320,80,20);
        jPanel.add(add);
        cancel.addActionListener(new Cancel());
        jPanel.add(cancel);
        exit.addActionListener(new Exit());
        jPanel.add(exit);
        jPanel.add(check);
        jFrame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
    }
    public  class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            jTextArea.setText("");
            Component[] components = jFrame.getRootPane().getContentPane().getComponents();
            for(Component c:components){
                if(c instanceof JTextField){
                    ((JTextField) c).setText("");
                }
            }
        }
    }
    public  class Add implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            inquireStart();

        }
    }
    private  void inquireStart(){
        check.setVisible(false);
        data = GetData.getData(jTextFields);
        if(data.length < LENGTH){
            check.setVisible(true);
            System.out.println("error!!!");
            return;
        }
        for (String s1:data){
            if(s1==null||s1.trim().length()==0||jTextArea.getText().trim().length()==0){
                check.setVisible(true);
                System.out.println("error!!!");
                break;
            }
            sqlStr="insert into `check`(check_time,check_record,pet_name,host_tele)" +
                    " values(?,?,?,?)  ;";
            INQUIREGUI=2;
            sendMessage(INQUIREGUI,sqlStr,data,jTextArea);
        }

    }
    public  class Exit  implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            INQUIREGUI=-1;
            jFrame.dispose();
            new MenuGUI().menuGraphic();

        }
    }



}
