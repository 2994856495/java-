package exercise03.java01.GUI;

import exercise03.java01.jdbc.Sql;
import exercise03.java01.process_data.GetData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 1）添加宠物信息功能：创建文本框输入宠物名字、种类（猫狗或者其他）、生日、品种、体重、家长姓名电话，
 * 当点击Add按钮时，将信息添加到数据库里，并且具备更新表格，显示新添加的记录。
 * @author ASUS
 */
public class InfoGUI {

    private static final JFrame FRAME = new JFrame("添加宠物信息");
    private static final int COUNT=7;
    private static JLabel check;
    private static final Sql SQL=new Sql();
    public static JTextField[] jTextFields;
    private static JPanel jPanel ;
    private static JLabel insert;
    private static JLabel checkInsert;
    public static void  infoGraphics(){
        check = new JLabel("所有均为必填项且体重号码为数字(不能填充空格)");
        check.setBounds(100,20,200,20);
        check.setFont(new Font("微软雅黑", Font.BOLD, 9));
        check.setForeground(Color.red);
        check.setVisible(false);

        insert = new JLabel("插入成功");
        insert.setBounds(100,20,180,20);
        insert.setFont(new Font("微软雅黑", Font.BOLD, 9));
        insert.setForeground(Color.red);
        insert.setVisible(false);

        checkInsert = new JLabel("已经创建成功,请勿重复添加");
        checkInsert.setBounds(100,20,180,20);
        checkInsert.setFont(new Font("微软雅黑", Font.BOLD, 9));
        checkInsert.setForeground(Color.red);
        checkInsert.setVisible(false);

        FRAME.setSize(325,400);
        String[] names= {"宠物名字","宠物种类","宠物生日","宠物品种","宠物体重",
                "主人姓名","主人电话"};
        jPanel=new JPanel();
        InfoGUI.jPanel.setLayout(null);
        JButton[] jButtons = new JButton[COUNT];
        jTextFields= new JTextField[COUNT];
        for(int i=0;i<COUNT;i++){
            jButtons[i]=new JButton(names[i]);
            jTextFields[i]=new JTextField();
            jButtons[i].setBounds(50,(i+1)*40,100,20);
            jTextFields[i].setColumns(10);
            jTextFields[i].setBounds(175,(i+1)*40,100,20);
            InfoGUI.jPanel.add(jButtons[i]);
            InfoGUI.jPanel.add(jTextFields[i]);
        }
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
        InfoGUI.jPanel.add(add);
        jPanel.add(check);
        jPanel.add(checkInsert);
        InfoGUI.jPanel.add(cancel);
        InfoGUI.jPanel.add(exit);
        FRAME.setContentPane(InfoGUI.jPanel);
        FRAME.setVisible(true);
    }
    public static class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            for(JTextField j:jTextFields){
                j.setText("");
            }
        }
    }

    public static class Add implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {

            String[] s = GetData.getData(jTextFields);
            if(s.length < COUNT){
                check.setVisible(true);
                insert.setVisible(false);
                checkInsert.setVisible(false);
                System.out.println("error!!!");
                return;
            }
            check.setVisible(false);
            insert.setVisible(false);
            checkInsert.setVisible(false);
            for (String s1:s){
                if(s1==null||s1.trim().length()==0){
                    check.setVisible(true);
                    insert.setVisible(false);
                    checkInsert.setVisible(false);
                    break;
                }
                String temp="insert into pet(pet_name,pet_species,pet_birthday,pet_variety" +
                        ",pet_weigth,host_name,host_tele" +
                        ") values(?,?,?,?,?,?,?)";
                SQL.getStatement(temp);
                int t=SQL.excuteUpdateSQL(s);
                //SQL.closeAll();
                if(t!=0){
                    check.setVisible(false);
                    insert.setVisible(true);
                    checkInsert.setVisible(false);
                    System.out.println("插入成功");
                    break;
                }
                else {
                    check.setVisible(false);
                    insert.setVisible(false);
                    checkInsert.setVisible(true);
                    System.out.println("插入失败");
                }
            }
        }
    }
    public static class Exit  implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            check.setVisible(false);
            FRAME.dispose();
            MenuGUI.menuGraphic();
        }
    }
}
