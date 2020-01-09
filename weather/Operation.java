/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

Operation类:对数据库进行操作，并将天气状况导入数据库

PS：对于预报天气表，我用了两种方式，不知道哪种更好一些。
一种是按province ,city ,adcode ,date day_of_week,day_weather ,night_weather,day_temperature,night_temperature
,day_wind_direction,night_wind_direction,day_wind_power,night_wind_power,report_time 依次存入，每一天的预报天气是一行数据。
但这样每个城市都有四天的天气情况，所以这四行的province ,city ,adcode都是相同数据。

第二种是用两个表储存信息，一个表存province,city,adcode。另一个表存adcode和天气情况，两个表都有相同的元素adcode，可以通过两个表对照，
来查看预报天气信息。但是需要用到两个表。
 */
package weather;

import java.sql.*;

public class Operation                          //将天气状况导入数据库
{
//数据域
    private static String APIURL="https://restapi.amap.com/v3/weather/weatherInfo?parameters";
    //要访问API的URL
    private static String KEY="d2ddd2955c27dcee5a07876a491da716";
    //key值

    private String cityNumber[];                //城市编码数组

    private int numOfCity;                      //城市数量

    private User user;                          //数据库账号

    private Weather[] weathers;                 //需要导入数据库的天气信息数组


//函数
    public Operation()                          //默认构造函数
    {

    }

    public Operation(String cityNumber[],User user)                  //构造函数
    {
        this.cityNumber=cityNumber;
        numOfCity=cityNumber.length;
        this.user=user;
        weathers=new Weather[numOfCity];
        for(int i=0;i<numOfCity;i++)
        {
            weathers[i]=new Weather();
        }
    }

    public void getAllWeather()                 //获取城市编码表中所有城市的天气情况
    {
        for(int i=0;i<numOfCity;i++)
        {
            weathers[i].translate(API.result(APIURL,KEY,cityNumber[i],"all"),"all");
            weathers[i].translate(API.result(APIURL,KEY,cityNumber[i],"base"),"base");
        }
    }

//创建表
    public void createLivesTable()              //创建实时天气表
    {
        String sql="create table lives_weather(province varchar(40),city varchar(40),adcode varchar(40)," +
                "weather varchar(40),temperature varchar(40),wind_direction varchar(40),wind_power varchar(40)," +
                "humidity varchar(40),report_time varchar(40))";
        //创建实时天气表的sql语句

        Connection conn = null;
        PreparedStatement pstmt = null;		//创建statement
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());
            //和数据库取得连接
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();			//执行，创建实时天气表
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmt);
            ConnectionTool.close(conn);
        }
    }

    public void createForecastTable()              //创建预报天气表
    {
        String sql="create table forecast_weather(province varchar(40),city varchar(40),adcode varchar(40)," +
                "date varchar(40),day_of_week varchar(40),day_weather varchar(40),night_weather varchar(40)," +
                "day_temperature varchar(40),night_temperature varchar(40),day_wind_direction varchar(40)," +
                "night_wind_direction varchar(40),day_wind_power varchar(40),night_wind_power varchar(40)," +
                "report_time varchar(40))";
        //创建预报天气表的sql语句

        Connection conn = null;
        PreparedStatement pstmt = null;		//创建statement
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());
            //和数据库取得连接
            pstmt = (PreparedStatement) conn.prepareStatement(sql);
            pstmt.executeUpdate();			//执行，创建预报天气表
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmt);
            ConnectionTool.close(conn);
        }
    }

    public void createForcastCityTable()              //创建预报天气-城市对照表
    {
        String sqlOne="create table forecast(adcode varchar(40),date varchar(40),day_of_week varchar(40)," +
                "day_weather varchar(40),night_weather varchar(40),day_temperature varchar(40)," +
                "night_temperature varchar(40),day_wind_direction varchar(40),night_wind_direction varchar(40)," +
                "day_wind_power varchar(40),night_wind_power varchar(40),report_time varchar(40))";
        //创建预报天气表的sql语句

        String sqlTwo="create table city(adcode varchar(40),province varchar(40),city varchar(40))";
        //创建城市编码表的sql语句

        Connection conn = null;
        PreparedStatement pstmtOne = null;		//创建statement1
        PreparedStatement pstmtTwo = null;		//创建statement1
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());
            //和数据库取得连接
            pstmtOne = (PreparedStatement) conn.prepareStatement(sqlOne);
            pstmtOne.executeUpdate();			//执行，创建预报天气表

            pstmtTwo = (PreparedStatement) conn.prepareStatement(sqlTwo);
            pstmtTwo.executeUpdate();			//执行，创建城市编码表
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmtOne);
            ConnectionTool.close(pstmtTwo);
            ConnectionTool.close(conn);
        }
    }

//插入信息
    public void insertLivesWeather()                //向实时天气表插入内容
    {
        Connection conn = null;				//和数据库取得连接
        PreparedStatement pstmt = null;		//创建statement
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());

            for (int i=0;i<weathers.length;i++)
            {
                String sql = "insert into lives_weather(province,city,adcode,weather,temperature,wind_direction," +
                        "wind_power,humidity,report_time) values(?,?,?,?,?,?,?,?,?)";
                //向实时天气表插入内容的sql语句

                pstmt = (PreparedStatement) conn.prepareStatement(sql);

                pstmt.setString(1, weathers[i].getLives().getProvince());
                pstmt.setString(2, weathers[i].getLives().getCity());
                pstmt.setString(3, weathers[i].getLives().getAdCode());
                pstmt.setString(4, weathers[i].getLives().getWeather());
                pstmt.setString(5, weathers[i].getLives().getTemperature());
                pstmt.setString(6, weathers[i].getLives().getWindDirection());
                pstmt.setString(7, weathers[i].getLives().getWindPower());
                pstmt.setString(8, weathers[i].getLives().getHumidity());
                pstmt.setString(9, weathers[i].getLives().getTime());

                pstmt.executeUpdate();            //执行，向实时天气表插入内容

            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmt);
            ConnectionTool.close(conn);
        }
    }

    public void insertForecastCityTable()           //向预报天气-城市编码表插入数据
    {
        Connection conn = null;
        PreparedStatement pstmtOne = null;		//创建statement1
        PreparedStatement pstmtTwo = null;      //创建statement2
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());
            //和数据库取得连接

            for (int i=0;i<weathers.length;i++)
            {
                for (int j=0;j<4;j++)
                {
                    String sqlOne="insert into forecast(adcode,date,day_of_week,day_weather,night_weather," +
                            "day_temperature,night_temperature,day_wind_direction,night_wind_direction," +
                            "day_wind_power,night_wind_power,report_time) values(?,?,?,?,?,?,?,?,?,?,?,?)";
                    //向预报天气表插入数据的sql语句

                    pstmtOne = (PreparedStatement) conn.prepareStatement(sqlOne);

                    pstmtOne.setString(1, weathers[i].getForecast().getAdCode());
                    pstmtOne.setString(2, weathers[i].getForecast().getDate()[j]);
                    pstmtOne.setString(3, weathers[i].getForecast().getDay()[j]);
                    pstmtOne.setString(4, weathers[i].getForecast().getDayWeather()[j]);
                    pstmtOne.setString(5, weathers[i].getForecast().getNightWeather()[j]);
                    pstmtOne.setString(6, weathers[i].getForecast().getDayTemperature()[j]);
                    pstmtOne.setString(7, weathers[i].getForecast().getNightTemperature()[j]);
                    pstmtOne.setString(8, weathers[i].getForecast().getDayWindDirection()[j]);
                    pstmtOne.setString(9, weathers[i].getForecast().getNightWindDirection()[j]);
                    pstmtOne.setString(10, weathers[i].getForecast().getDayWindPower()[j]);
                    pstmtOne.setString(11, weathers[i].getForecast().getNightWindPower()[j]);
                    pstmtOne.setString(12, weathers[i].getForecast().getTime());

                    pstmtOne.executeUpdate();            //执行，向预报天气表插入数据
                }
                String sqlTwo="insert into city(adcode,province,city) values(?,?,?)";
                //向城市编码表插入数据的sql语句

                pstmtTwo = (PreparedStatement) conn.prepareStatement(sqlTwo);
                pstmtTwo.setString(1, weathers[i].getForecast().getAdCode());
                pstmtTwo.setString(2, weathers[i].getForecast().getProvince());
                pstmtTwo.setString(3, weathers[i].getForecast().getCity());

                pstmtTwo.executeUpdate();            //执行，向城市编码表插入数据
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmtOne);
            ConnectionTool.close(pstmtTwo);
            ConnectionTool.close(conn);
        }
    }

    public void insertForecastTable()           //向预报天气表插入数据
    {
        Connection conn = null;
        PreparedStatement pstmt = null;		//创建statement
        try
        {
            conn = ConnectionTool.getConnection(user.getDriver(),user.getUrl(),user.getUser(),user.getPassword());
            //和数据库取得连接

            for (int i=0;i<weathers.length;i++)
            {
                for (int j=0;j<4;j++)
                {

                    String sql = "insert into forecast_weather(province,city,adcode,date,day_of_week,day_weather," +
                            "night_weather,day_temperature,night_temperature,day_wind_direction,night_wind_direction," +
                            "day_wind_power,night_wind_power,report_time) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    //向预报天气表插入数据的sql语句

                    pstmt = (PreparedStatement) conn.prepareStatement(sql);


                    pstmt.setString(1, weathers[i].getForecast().getProvince());
                    pstmt.setString(2, weathers[i].getForecast().getCity());
                    pstmt.setString(3, weathers[i].getForecast().getAdCode());
                    pstmt.setString(4, weathers[i].getForecast().getDate()[j]);
                    pstmt.setString(5, weathers[i].getForecast().getDay()[j]);
                    pstmt.setString(6, weathers[i].getForecast().getDayWeather()[j]);
                    pstmt.setString(7, weathers[i].getForecast().getNightWeather()[j]);
                    pstmt.setString(8, weathers[i].getForecast().getDayTemperature()[j]);
                    pstmt.setString(9, weathers[i].getForecast().getNightTemperature()[j]);
                    pstmt.setString(10, weathers[i].getForecast().getDayWindDirection()[j]);
                    pstmt.setString(11, weathers[i].getForecast().getNightWindDirection()[j]);
                    pstmt.setString(12, weathers[i].getForecast().getDayWindPower()[j]);
                    pstmt.setString(13, weathers[i].getForecast().getNightWindPower()[j]);
                    pstmt.setString(14, weathers[i].getForecast().getTime());

                    pstmt.executeUpdate();            //执行，向预报天气表插入数据
                }
            }
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
        finally
        {
            ConnectionTool.close(pstmt);
            ConnectionTool.close(conn);
        }
    }

//get函数
    public Weather[] getWeathers()
    {
        return weathers;
    }

    public String[] getCityNumber()
    {
        return cityNumber;
    }

    public int getNumOfCity()
    {
        return numOfCity;
    }

    public User getUser()
    {
        return user;
    }

}
