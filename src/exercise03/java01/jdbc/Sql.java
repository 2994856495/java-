package exercise03.java01.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * @author ASUS
 */
public class Sql {
    private static Connection con=null;
    private PreparedStatement prepareCall =null;
    private ResultSet rs=null;
    private static final String DRIVER_CLASS;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;
    private static final Properties PROPS;
    private static InputStream is;
    static {
        PROPS =new Properties();
        is= null;
        try {
            is = new FileInputStream("src\\exercise03\\java01\\jdbc\\jdbc.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            PROPS.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        DRIVER_CLASS = PROPS.getProperty("driverClass");
        URL = PROPS.getProperty("url");
        USERNAME = PROPS.getProperty("username");
        PASSWORD = PROPS.getProperty("password");
        try {
            Class.forName(DRIVER_CLASS);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println( "加载驱动成功!" );
        try {
            con = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println( "连接数据库成功!" );
    }

    public void getStatement(String sqlStr){
        try {
            prepareCall = con.prepareCall(sqlStr);
        } catch (SQLException throwables) {
            return;
        }
        System.out.println( "创建Statement成功!" );
    }
    public int excuteUpdateSQL(String... a){
        int t = 0;

        try {
            for (int i=0;i<a.length;i++){
                prepareCall.setString(i+1,a[i]);
            }
            t= prepareCall.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode()+"  "+throwables.getSQLState());
        }
        return t;

    }
    public ResultSet executeQuerySQL(String ...a){
        try {
            for (int i=0;i<a.length;i++){
                prepareCall.setString(i+1,a[i]);
            }
            rs= prepareCall.executeQuery();
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode()+"  "+throwables.getSQLState());
        }
        return rs;

    }
    public void closeAll(){
        if (is!=null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            if(rs!=null){
                rs.close();
            }
        } catch (SQLException throwables) {
            return;
        }
        try {
            if(prepareCall !=null){
                prepareCall.close();
            }
        } catch (SQLException throwables) {
            return;
        }
        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException throwables) {
            return;
        }
        System.out.println("数据库关闭成功");
    }

}
