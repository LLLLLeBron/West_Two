/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

Forecast类:预报天气类
 */
package weather;

import org.json.JSONArray;
import org.json.JSONObject;

public class Forecast                       //预报天气信息
{
    private boolean flag;                           //状态量

    private String province;                    //查询省份
    private String city;                        //查询城市
    private String adCode;                         //地区编号
    private String time;                        //查询时间
    private String date[]= new String[4];                      //日期
    private String day[] = new String[4];                             //星期数
    private String dayWeather[]= new String[4];                 //白天天气
    private String nightWeather[]= new String[4];               //晚间天气
    private String dayTemperature[] = new String[4];                  //白天温度
    private String nightTemperature[] = new String[4];                //晚间温度
    private String dayWindDirection[]= new String[4];           //白天风向
    private String nightWindDirection[]= new String[4];         //晚间风向
    private String dayWindPower[]= new String[4];               //白天风力
    private String nightWindPower[]= new String[4];             //晚间风力

    public Forecast()                            //构造函数
    {
        flag=false;                     //还未解析，flag设为false
    }

    public void translate(JSONArray forecast)            //将获取的预报天气JSONArray解析
    {
        JSONObject foreCast = forecast.getJSONObject(0);
        province = foreCast.getString("province");
        city = foreCast.getString("city");
        adCode = foreCast.getString("adcode");
        time = foreCast.getString("reporttime");
        JSONArray Forecast = foreCast.getJSONArray("casts");
        for (int i = 0; i < Forecast.length(); i++)
        {
            JSONObject ForeCast = Forecast.getJSONObject(i);
            date[i] = ForeCast.getString("date");
            day[i] = ForeCast.getString("week");
            dayWeather[i] = ForeCast.getString("dayweather");
            nightWeather[i] = ForeCast.getString("nightweather");
            dayTemperature[i] = ForeCast.getString("daytemp");
            nightTemperature[i] = ForeCast.getString("nighttemp");
            dayWindDirection[i] = ForeCast.getString("daywind");
            nightWindDirection[i] = ForeCast.getString("nightwind");
            dayWindPower[i] = ForeCast.getString("daypower");
            nightWindPower[i] = ForeCast.getString("nightpower");
        }
        flag=true;                             //解析完成，flag改为true
    }

    public void print()                     //输出预报天气
    {
        System.out.println("查询的地区:"+province+"  "+city+" "+"区域编码:"+adCode+" "+"查询时间:"+time );
        System.out.println("未来四天的天气状况如下:");
        for (int i=0;i<4;i++)
        {
            System.out.print("日期:"+date[i]+"  "+"星期"+day[i]+"  ");
            System.out.print("白天天气:"+dayWeather[i]+"  "+"晚间天气:"+nightWeather[i]+"  ");
            System.out.print("白天温度:"+dayTemperature[i]+"℃"+"  "+"晚间温度:"+nightTemperature[i]+"℃"+"  ");
            System.out.print("白天风向:"+dayWindDirection[i]+"  "+"晚间风向:"+nightWindDirection[i]+"  ");
            System.out.println("白天风力:"+dayWindPower[i]+"级" +"  "+"晚间风力:"+nightWindPower[i]+"级");
        }

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

    public String[] getDate()
    {
        return date;
    }

    public String[] getDay()
    {
        return day;
    }

    public String[] getDayWeather()
    {
        return dayWeather;
    }

    public String[] getNightWeather()
    {
        return nightWeather;
    }

    public String[] getDayTemperature()
    {
        return dayTemperature;
    }

    public String[] getNightTemperature()
    {
        return nightTemperature;
    }

    public String[] getDayWindDirection()
    {
        return dayWindDirection;
    }

    public String[] getNightWindDirection()
    {
        return nightWindDirection;
    }

    public String[] getDayWindPower()
    {
        return dayWindPower;
    }

    public String[] getNightWindPower()
    {
        return nightWindPower;
    }
}
