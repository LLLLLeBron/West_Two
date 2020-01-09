/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

User类:数据库账户类，保存URL、user、password、driver
 */
package weather;

import java.sql.*;

public class User                   //数据库账户类
{
    private String url;
    //连接数据库的URL
    private String user;
    //用户名
    private String password;
    //密码
    private String driver;
    //驱动类

    public User(String url,String user,String password,String driver)       //构造函数
    {
        this.url=url;
        this.user=user;
        this.password=password;
        this.driver=driver;
    }

//get函数

    public String getUrl()
    {
        return url;
    }

    public String getUser()
    {
        return user;
    }

    public String getPassword()
    {
        return password;
    }

    public String getDriver()
    {
        return driver;
    }
}
