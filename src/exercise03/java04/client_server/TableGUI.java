package exercise03.java04.client_server;
import exercise03.java04.jdbc.Sql;

import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import static javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS;

/**
 * 生成表格
 */
public class TableGUI extends JFrame
{
    private final String[] fieldNames= {"pet_name","pet_species","pet_birthday",
            "pet_variety","pet_weight","host_name","host_tele","check_time",
            "check_record"};
    private JScrollPane scpDemo;
    private final String[] title= {"宠物姓名","宠物种类"," 宠物生日",
            "宠物品种","宠物体重","主人姓名","主人电话","检查记录","检查时间"};
    private JTableHeader jth;
    private JTable tabDemo;
    private JButton btnShow;
    private JButton bt2;
    private String sqlStr;

    public  void drawTableGUI(String sql,int MENUGUI) {
        this.sqlStr =sql;
        this.setTitle("宠物信息表");		//JFrame的标题名称
        this.setSize(660,600);		//控制窗体大小
        this.setLayout(null);		//自定义布局
        this.setLocation(400,100);	//点击运行以后，窗体在屏幕的位置
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.scpDemo = new JScrollPane();
        this.bt2=new JButton("退出");
        this.btnShow = new JButton("显示数据");
        this.bt2.setBounds(450, 480, 100, 30);
        this.scpDemo.setBounds(10,50,580,400);	//设置滚动框大小
        this.btnShow.setBounds(10,10,120,30);	//设置按钮
        this.btnShow.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                btnShow_ActionPerformed(ae);
            }
        });
        /******按钮 “退出”的响应*****/
        this.bt2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                dispose();
            }
        });

        /******* 将组件加入到窗体中******/
        add(this.scpDemo);
        add(this.btnShow);
        add(this.bt2);
        this.setVisible(true);

    }
    /***连接数据库并显示到表格中***/
    public void btnShow_ActionPerformed(ActionEvent ae) {
        try {
            ResultSet rs = Sql.executeQuerySQL(sqlStr);
            int count = 0;
            while(rs.next())
            {
                count++;
            }
            rs= Sql.executeQuerySQL(sqlStr);
            Object[][] info = new Object[count][9];
            count = 0;
            while(rs.next())
            {
                for (int i=0;i<fieldNames.length;i++){
                info[count][i] = rs.getString(i+1);
                }
                count++;
            }
            this.tabDemo = new JTable(info,title){
                @Override
                public boolean getScrollableTracksViewportWidth() {
                    return getPreferredSize().width < getParent().getWidth();
                }
            };

            // 显示表头
            this.jth = this.tabDemo.getTableHeader();
            tabDemo.setAutoResizeMode(AUTO_RESIZE_ALL_COLUMNS);
            // 将JTable加入到带滚动条的面板中AUTO_RESIZE_ALL_COLUMNS
            this.scpDemo.getViewport().add(tabDemo);


        } catch(SQLException sqle) {
            sqle.printStackTrace();
            JOptionPane.showMessageDialog(null,"数据操作错误","错误",JOptionPane.ERROR_MESSAGE);
        }
    }

}


