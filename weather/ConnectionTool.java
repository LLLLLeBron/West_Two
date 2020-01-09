/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

ConnectionTool类:用于连接数据库的工具类
 */
package weather;

import java.sql.*;


public class ConnectionTool             //连接数据库的工具类
{
    public static Connection getConnection(String driver,String url,String user,String password)
    {
        Connection conn = null;
        try
        {
            //初始化驱动类
            Class.forName(driver);
            conn = DriverManager.getConnection(url,user,password);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public static void close(PreparedStatement pstmt)
    {
        if(pstmt != null){						//避免出现空指针异常
            try{
                pstmt.close();
            }catch(SQLException e){
                e.printStackTrace();
            }

        }
    }

    public static void close(Connection conn)
    {
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }

    public static void close(ResultSet rs)
    {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
        }
    }
}
