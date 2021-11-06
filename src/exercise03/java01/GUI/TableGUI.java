package exercise03.java01.GUI;
import exercise03.java01.jdbc.Sql;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * 生成表格
 */
public class TableGUI extends JFrame
{

    private final String[] fieldNames= {"pet_name","pet_species","pet_birthday",
            "pet_variety","pet_weight","host_name","host_tele","check_time",
            "check_record"};
    private JScrollPane scpDemo;
    private final Sql SQL=new Sql();
    private JTableHeader jth;
    private JTable tabDemo;
    private JButton btnShow;
    private JButton bt1;
    private JButton bt2;
    private String sql;
    public void drawTableGUI(String sql) {
        this.sql=sql;
        this.setTitle("宠物信息表");		//JFrame的标题名称
        this.setSize(660,600);		//控制窗体大小
        this.setLayout(null);		//自定义布局
        this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
        this.scpDemo = new JScrollPane();
        this.bt1=new JButton("返回");
        this.bt2=new JButton("退出");
        this.btnShow = new JButton("显示数据");
        this.bt1.setBounds(100, 480, 100, 30);
        this.bt2.setBounds(380, 480, 100, 30);
        this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
        this.btnShow.setBounds(10,10,120,30);	//设置按钮
        this.btnShow.addActionListener(new ActionListener()	//给“显示数据”按钮添加事件响应。
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                btnShow_ActionPerformed(ae);
            }
        });
        /********按钮“返回”的响应*******/
        this.bt1.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                TableGUI.super.dispose();
                MenuGUI.menuGraphic();
            }
        });
        /******按钮 “退出”的响应*****/
        this.bt2.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }
        });
        /******* 将组件加入到窗体中******/
        add(this.scpDemo);
        add(this.btnShow);
        add(this.bt1);
        add(this.bt2);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    /***连接数据库并显示到表格中***/
    public void btnShow_ActionPerformed(ActionEvent ae)
    {
        try
        {

            SQL.getStatement(sql);
            ResultSet rs = SQL.executeQuerySQL();
            int count = 0;
            while(rs.next())
            {
                count++;
            }
            rs=SQL.executeQuerySQL();
            // 将查询获得的记录数据，转换成适合生成JTable的数据形式
            Object[][] info = new Object[count][9];
            String []title= {"宠物姓名","宠物种类"," 宠物生日","宠物品种","宠物体重","主人姓名","主人电话","检查记录","检查时间"};
            count = 0;
            while(rs.next())
            {
                for (int i=0;i<fieldNames.length;i++){
                info[count][i] = rs.getString(i+1);
                }
                count++;
            }
            // 创建JTable
            this.tabDemo = new JTable(info,title);
            // 显示表头
            this.jth = this.tabDemo.getTableHeader();
            // 将JTable加入到带滚动条的面板中
            this.scpDemo.getViewport().add(tabDemo);

        } catch(SQLException sqle)
        {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

}


