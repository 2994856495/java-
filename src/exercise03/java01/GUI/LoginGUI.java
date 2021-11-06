package exercise03.java01.GUI;

import exercise03.java01.jdbc.Sql;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static exercise03.java01.process_data.Regx.checkPassword;

/**
 * 登录信息
 * admin abcd1234
 * @author ASUS
 */
public class LoginGUI {
    static {
        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
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
    public static Sql SQL=new Sql() ;
    private static JTextField usrText;
    private static JTextField passwdText;
    private static final JFrame FRAME = new JFrame("登录界面");
    private static JLabel wrongPwd;
    private static JLabel checkCode;
    private static JLabel checkLogin;
    public static void drawLogin(){
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

        wrongPwd = new JLabel("密码/用户名错误");
        wrongPwd.setBounds(220,220,80,20);
        wrongPwd.setFont(new Font("微软雅黑", Font.ITALIC, 10));
        wrongPwd.setForeground(Color.red);
        wrongPwd.setVisible(false);

        checkLogin = new JLabel("注册成功");
        checkLogin.setBounds(120,250,180,20);
        checkLogin.setFont(new Font("微软雅黑", Font.BOLD, 15));
        checkLogin.setForeground(Color.red);
        checkLogin.setVisible(false);


        jPanel.add(checkLogin);
        jPanel.add(checkCode);
        jPanel.add(wrongPwd);
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
    public static class Login implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username=usrText.getText();
            String passwd=passwdText.getText();
            wrongPwd.setVisible(false);
            String sqlStr="INSERT INTO `user` VALUES(?,?)";
            if (username.length() != 0 && checkPassword(passwd)){
                SQL.getStatement(sqlStr);
                if(SQL.excuteUpdateSQL(username, passwd)==0){
                    wrongPwd.setVisible(false);
                    checkLogin.setText("注册失败");
                    checkLogin.setVisible(true);
                    usrText.setText("");
                    passwdText.setText("");
                }
                else{
                    checkCode.setVisible(false);
                    wrongPwd.setVisible(false);
                    checkLogin.setText("注册成功");
                    checkLogin.setVisible(true);
                }
            }
            else{
                checkLogin.setVisible(false);
                wrongPwd.setVisible(false);
                checkCode.setVisible(true);
            }

        }
    }
    public static class Exit implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            FRAME.dispose();
        }
    }
    public static class Enter implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String username=usrText.getText();
            String passwd=passwdText.getText();
            String sqlStr="select * FROM `user` WHERE usr=? AND passwd=?";
            SQL.getStatement(sqlStr);
            ResultSet resultSet = SQL.executeQuerySQL(username, passwd);
            try {
                if (resultSet.next()) {
                    checkCode.setVisible(false);
                    checkLogin.setVisible(false);
                    wrongPwd.setVisible(false);
                    FRAME.dispose();
                    MenuGUI.menuGraphic();
                }
                else{
                    checkCode.setVisible(false);
                    checkLogin.setVisible(false);
                    wrongPwd.setVisible(true);
                    System.out.println("error!!!");
                }

            } catch (SQLException throwables) {
                System.out.println("error!!!");
            }

        }
    }
}
