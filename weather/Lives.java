/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

Lives类:实时天气类
 */
package weather;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

public class Lives                  //实时天气信息
{
    private boolean flag;                           //状态量
    private String province;                    //查询省份
    private String city;                        //查询城市
    private String adCode;                         //地区编号
    private String time;                        //查询时间
    private String weather;                     //天气
    private String temperature;                    //温度
    private String windDirection;               //风向
    private String windPower;                   //风力
    private String humidity;                       //空气湿度

    public Lives()                          //构造函数
    {
        flag=false;                 //还未解析，flag设为false
    }

    public void translate(JSONArray lives)        //将获取的实时天气JSONOArray解析
    {
        JSONObject liveS = lives.getJSONObject(0);
        province = liveS.getString("province");
        city = liveS.getString("city");
        adCode = liveS.getString("adcode");
        time = liveS.getString("reporttime");
        weather = liveS.getString("weather");
        temperature = liveS.getString("temperature");
        windDirection = liveS.getString("winddirection");
        windPower = liveS.getString("windpower");
        humidity = liveS.getString("humidity");
        flag=true;                             //解析完成，flag改为true
    }

    public void print()                         //输出实时天气
    {
        System.out.println("查询的地区:"+province+"  "+city+" "+"区域编码:"+adCode+" "+"查询时间:"+time);
        System.out.println("天气:"+weather+"  "+"温度:"+temperature+"℃"+"  "+"风向:"+windDirection +"  "
                +"风力:"+windPower+"级" +"  "+"空气湿度:"+humidity);
    }


//get函数
    public boolean getFlag()
    {
        return flag;
    }

    public String getProvince()
    {
        return province;
    }

    public String getCity()
    {
        return city;
    }

    public String getAdCode()
    {
        return adCode;
    }

    public String getTime()
    {
        return time;
    }

    public String getWeather()
    {
        return weather;
    }

    public String getTemperature()
    {
        return temperature;
    }

    public String getWindDirection()
    {
        return windDirection;
    }

    public String getWindPower()
    {
        return windPower;
    }

    public String getHumidity()
    {
        return humidity;
    }
}
