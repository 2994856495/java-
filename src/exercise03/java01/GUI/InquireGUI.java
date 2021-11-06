package exercise03.java01.GUI;

import exercise03.java01.jdbc.Sql;
import exercise03.java01.process_data.GetData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * 宠物就诊功能：创建文本框添加就诊时间、就诊记录。
 * @author ASUS
 */
public class InquireGUI {

    public static JFrame jFrame=new JFrame("就诊功能");
    private static final Sql SQL=new Sql();
    private static JTextArea jTextArea;
    private static final int LENGTH=2;
    public static JTextField[] jTextFields;
    private static JLabel checkTime;
    private static JLabel check;
    private static JLabel checkExist;
    private static JLabel checkSuccessful;
    public static void inquireGraphics(){
        check=new JLabel("所有均为必填项(不能填充空格)");
        check.setFont(new Font("微软雅黑", Font.BOLD, 10));
        check.setBounds(80,0,150,50);
        check.setForeground(Color.red);
        check.setVisible(false);

        checkExist=new JLabel("无该宠物信息或信息不对");
        checkExist.setFont(new Font("微软雅黑", Font.BOLD, 10));
        checkExist.setBounds(80,0,150,50);
        checkExist.setForeground(Color.red);
        checkExist.setVisible(false);

        checkSuccessful=new JLabel("信息添加成功");
        checkSuccessful.setFont(new Font("微软雅黑", Font.BOLD, 15));
        checkSuccessful.setBounds(100,0,150,50);
        checkSuccessful.setForeground(Color.red);
        checkSuccessful.setVisible(false);



        checkTime=new JLabel(String.valueOf(new Date()));
        checkTime.setBounds(50,240,240,40);
        checkTime.setVisible(false);

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
        jPanel.add(checkSuccessful);
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
        jPanel.add(checkExist);

        jPanel.add(checkTime);
        jPanel.add(check);
        jFrame.setContentPane(jPanel);
        jFrame.setVisible(true);
    }
    public static class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            checkExist.setVisible(false);
            checkSuccessful.setVisible(false);
            jTextArea.setText("");
            Component[] components = jFrame.getRootPane().getContentPane().getComponents();
            for(Component c:components){
                if(c instanceof JTextField){
                    ((JTextField) c).setText("");
                }
            }
        }
    }
    public static class Add implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            checkExist.setVisible(false);
            checkSuccessful.setVisible(false);
            String[] s = GetData.getData(jTextFields);
            if(s.length < LENGTH){
                check.setVisible(true);
                System.out.println("error!!!");
                return;
            }
            for (String s1:s){
                if(s1==null||s1.trim().length()==0||jTextArea.getText().trim().length()==0){
                    check.setVisible(true);
                    System.out.println("error!!!");
                    break;
                }
                String s2="insert into `check`(check_time,check_record,pet_name,host_tele)" +
                        " values(?,?,?,?)  ;";
                SQL.getStatement(s2);
                int excuteUpdateSQL = SQL.excuteUpdateSQL(checkTime.getText(), jTextArea.getText(), s[0],s[1]);

                if(excuteUpdateSQL!=0){
                    checkSuccessful.setVisible(true);
                    System.out.println("successful!!!");
                    break;
                }
                else{
                    checkExist.setVisible(true);
                    System.out.println("error!!!");
                }
            }
        }
    }
    public static class Exit  implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            checkExist.setVisible(false);
            checkSuccessful.setVisible(false);
            jFrame.dispose();
            MenuGUI.menuGraphic();

        }
    }
}
