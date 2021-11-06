package exercise03.java04.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static exercise03.java04.client_server.Client.sendMessage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * 主菜单
 */
public class MenuGUI {

    private String sqlStr;
    private final JFrame FRAME = new JFrame("主菜单");
    private  int MENUGUI=-1;
    public void menuGraphic(){
        MENUGUI=-1;
        FRAME.setSize(325,480);
        String[] names= {"添加宠物信息功能","宠物就诊功能","查询功能","查看宠物信息","退出"};
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);
        JButton[] jButtons = new JButton[5];
        for(int i=0;i<5;i++){
            jButtons[i]=new JButton(names[i]);
            jButtons[i].setBounds(75,(i+1)*65,150,20);
            jPanel.add(jButtons[i]);
        }
        jButtons[0].addActionListener(new Info());
        jButtons[1].addActionListener(new Inquire());
        jButtons[2].addActionListener(new Check());
        jButtons[3].addActionListener(new CheckInfo());
        jButtons[4].addActionListener(new Back());
        FRAME.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);
    }
    private  class Back implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    private  class Inquire implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MENUGUI=-1;
            FRAME.dispose();
            new InquireGUI().inquireGraphics();
        }
    }
    public  class Check implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            MENUGUI=-1;
            FRAME.dispose();
            new CheckGUI().checkGraphics();
        }
    }
    public  class CheckInfo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            menuStart();
        }
    }
    private  void menuStart(){
        sqlStr="select p.pet_name,pet_species,pet_birthday,pet_variety,pet_weigth\n" +
                ",p.host_name,p.host_tele,check_record,check_time " +
                "from pet p   left join `check` c on  p.pet_name=c.pet_name ;";
        MENUGUI=4;
        String[] data=new String[1];
        data[0]="1";
        System.out.println(data[0]);
        sendMessage(MENUGUI,sqlStr,data,null);

    }
    public  class Info implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            MENUGUI=-1;
            FRAME.dispose();
            new InfoGUI().infoGraphics();
        }
    }

}
