package exercise03.java04.GUI;

import exercise03.java04.process_data.GetData;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static exercise03.java04.client_server.Client.sendMessage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *查询功能：可以根据文本框里填入的家长姓名或者电话显示查询的宠物信息，包括就诊记录，
 * 如果没有查找到，应弹出对话框显示无此记录。
 */
public class CheckGUI{

    private final JFrame FRAME = new JFrame("查询功能");

    private  final int LENGTH=4;
    private  JTextField[]  jTextFields;


    public  void checkGraphics(){
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

        FRAME.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);

    }

    private  class Start implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String[] data = GetData.getData(jTextFields);
            if(data.length == 0){
                System.out.println("error!!!!");
                return;
            }

            String sqlStr ="select p.pet_name,pet_species,pet_birthday,pet_variety,pet_weigth\n" +
                    ",p.host_name,p.host_tele,check_record,check_time " +
                    "from pet p  left join `check` c on  p.pet_name=c.pet_name where ";
            sendMessage(0,sqlStr,data,null);

        }
    }

    private  class Exit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            new MenuGUI().menuGraphic();
        }
    }

    private  class Cancel implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            for(JTextField j:jTextFields){
                j.setText("");
            }
        }
    }



}
