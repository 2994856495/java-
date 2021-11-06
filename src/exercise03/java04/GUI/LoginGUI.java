package exercise03.java04.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static exercise03.java01.process_data.Regx.checkPassword;
import static exercise03.java04.client_server.Client.sendMessage;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * 登录信息
 * admin abcd1234
 */
public class LoginGUI {
    static {
        for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
        {
            if ("Nimbus".equals(info.getName()))
            {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException
                        | UnsupportedLookAndFeelException e) {
                    System.out.println("error!!!!");
                }
                break;
            }
        }
    }
    private  int LOGINGUI_ENTER =-1;
    private  int LOGINGUI_LOGIN =-1;
    private String sqlStr;
    private String[] data;
    private  JTextField usrText;
    private  JTextField passwdText;
    private static final JFrame FRAME = new JFrame("登录界面");
    private  JLabel checkCode;
    public  void drawLogin(){
        LOGINGUI_ENTER =-1;
        LOGINGUI_LOGIN =-1;
        FRAME.setSize(325,500);
        JPanel jPanel = new JPanel();
        jPanel.setLayout(null);

        JButton enter = new JButton("登录");
        enter.setBounds(30,300,80,20);
        enter.addActionListener(new Enter());
        JButton login = new JButton("注册");
        login.setBounds(120,300,80,20);
        login.addActionListener(new Login());
        JButton exit = new JButton("退出");
        exit.addActionListener(new Exit());
        exit.setBounds(210,300,80,20);

        JLabel usr = new JLabel("账号");
        usr.setFont(new Font("微软雅黑", Font.BOLD, 20));
        usr.setBounds(50,100,100,50);
        JLabel passwd = new JLabel("密码");
        passwd.setFont(new Font("微软雅黑", Font.BOLD, 20));
        passwd.setBounds(50,200,100,50);

        usrText = new JTextField();
        passwdText = new JTextField();
        usrText.setColumns(10);
        passwdText.setColumns(10);
        usrText.setBounds(120,120,80,20);
        passwdText.setBounds(120,220,80,20);

        checkCode = new JLabel("密码同时含数字和字母，长度大于8位");
        checkCode.setBounds(70,250,180,20);
        checkCode.setFont(new Font("微软雅黑", Font.BOLD, 8));
        checkCode.setForeground(Color.red);
        checkCode.setVisible(false);


        FRAME.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        jPanel.add(checkCode);
        jPanel.add(enter);
        jPanel.add(exit);
        jPanel.add(passwd);
        jPanel.add(passwdText);
        jPanel.add(usr);
        jPanel.add(usrText);
        jPanel.add(login);
        jPanel.add(passwdText);
        FRAME.setContentPane(jPanel);
        FRAME.setVisible(true);

    }
    public  class Login implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username=usrText.getText();
            String passwd=passwdText.getText();
            data= new String[]{username, passwd};

            sqlStr="INSERT INTO `user` VALUES(?,?)";
            checkCode.setVisible(false);
            if (username.length() != 0 && checkPassword(passwd)){
                LOGINGUI_ENTER =-1;
                LOGINGUI_LOGIN =6;
                sendMessage(LOGINGUI_LOGIN,sqlStr,data,null);

            }
            else{
                checkCode.setVisible(true);
            }

        }
    }
    public  class Exit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            LOGINGUI_ENTER =-1;
            LOGINGUI_LOGIN =-1;
            FRAME.dispose();
        }
    }
    public  class Enter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            enterStart();
        }
    }
    private  void enterStart(){
        String username=usrText.getText();
        String passwd=passwdText.getText();
        data= new String[]{username, passwd};
        sqlStr="select * FROM `user` WHERE usr=? AND passwd=?";
        LOGINGUI_ENTER =3;
        LOGINGUI_LOGIN =-1;
        FRAME.dispose();
        sendMessage(LOGINGUI_ENTER,sqlStr,data,null);

    }

}
