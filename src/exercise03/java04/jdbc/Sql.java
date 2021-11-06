package exercise03.java04.jdbc;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class Sql {
    private final String[] fieldNames= {"pet_name","pet_species","pet_birthday",
    "pet_variety","pet_weight","host_name","host_tele","check_time",
    "check_record"};

    private static Connection con=null;
    private static final String driverClass ;
    private static final String url ;
    private static final String username ;
    private static final String password ;
    private static Properties props;
    private static InputStream is;
    static {
        props=new Properties();
        is= null;
        try {
            is = new FileInputStream("src\\exercise03\\java04\\jdbc\\jdbc.properties");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        driverClass = props.getProperty("driverClass");
        url = props.getProperty("url");
        username = props.getProperty("username");
        password = props.getProperty("password");
        try {
            Class.forName(driverClass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println( "加载驱动成功!" );
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println( "连接数据库成功!" );
    }
    public static PreparedStatement getStatement(String sqlStr){
        try {
            System.out.println( "创建Statement成功!" );

            return con.prepareCall(sqlStr);
        } catch (SQLException throwables) {
            System.out.println("statement");
            return null;
        }

    }
    public static int excuteUpdateSQL(String sqlStr, String... a){
        PreparedStatement prepareCall=getStatement(sqlStr);
        int t = 0;
        try {
            for (int i=0;i<a.length;i++){
                prepareCall.setString(i+1,a[i]);
            }
            t= prepareCall.executeUpdate();
        } catch (SQLException throwables) {
            System.out.println(throwables.getErrorCode()+"  "+throwables.getSQLState());
        }
        finally {
            if (prepareCall!=null){
                try {
                    prepareCall.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
        return t;

    }
    public static ResultSet executeQuerySQL(String sqlStr, String... a){
        PreparedStatement prepareCall=getStatement(sqlStr);
        try {
            for (int i=0;i<a.length;i++){
                prepareCall.setString(i+1,a[i]);
            }
            ResultSet rs= prepareCall.executeQuery();
            return rs;
        } catch (SQLException throwables) {
            return null;
        }


    }
    public  boolean closeAll(){
        if (is!=null){
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if(con!=null){
                con.close();
            }
        } catch (SQLException throwables) {
            return false;
        }
        System.out.println("数据库关闭成功");
        return true;
    }

}
