package exercise03.java01.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 主菜单
 */
public class MenuGUI {

    private static final JFrame FRAME = new JFrame("主菜单");
    public static void menuGraphic(){
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
        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);
    }
    public static class Back implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
    public static class Inquire implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            InquireGUI.inquireGraphics();
        }
    }
    public static class Check implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            CheckGUI.checkGraphics();
        }
    }
    public static class CheckInfo implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            String sql="select p.pet_name,pet_species,pet_birthday,pet_variety,pet_weigth\n" +
                    ",p.host_name,p.host_tele,check_record,check_time " +
                    "from pet p left join `check` c on  p.host_name=c.pet_name ;";
            new TableGUI().drawTableGUI(sql);
        }
    }
    public static class Info implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
            InfoGUI.infoGraphics();
        }
    }
}
