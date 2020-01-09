/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

Weather类:天气信息类
 */
package weather;

import org.json.JSONObject;

public class Weather                //天气信息
{
    private int status;                         //返回状态
    private int count;                          //结果总数目
    private String info;                        //返回状态信息
    private int infocode;                       //返回状态说明

    private Forecast forecast;                  //预报天气
    private Lives lives;                        //实时天气

    public Weather()
    {
        forecast=new Forecast();
        lives=new Lives();
    }

    public void translate (JSONObject json,String extensions)           //根据不同的查询方式解析JSONObject对象
    {
        try
        {
            status = json.getInt("status");
            count = json.getInt("count");
            info = json.getString("info");
            infocode = json.getInt("infocode");

            if(extensions.equals("all"))                        //根据不同的查询方式，选择对应的解析方式
            {
                forecast.translate(json.getJSONArray("forecasts"));
            }
            else if(extensions.equals("base"))
            {
                lives.translate(json.getJSONArray("lives"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

    }

    public void print()                             //输出天气信息
    {

        if(forecast.getFlag())                  //若预报天气已解析，则输出其内容
        {
            System.out.println("返回状态:"+status);
            System.out.println("结果总数目:"+count );
            System.out.println("返回状态信息:"+info );
            System.out.println("返回状态说明（10000代表正确）:"+infocode);
            System.out.println("");
            System.out.println("查询结果如下:"+"\n");
            forecast.print();                       //输出预报天气信息
            System.out.println("\n");
        }
        if(lives.getFlag())                     //若实时天气已解析，则输出其内容
        {
            System.out.println("返回状态:"+status);
            System.out.println("结果总数目:"+count );
            System.out.println("返回状态信息:"+info );
            System.out.println("返回状态说明（10000代表正确）:"+infocode);
            System.out.println("");
            System.out.println("查询结果如下:"+"\n");
            lives.print();                      //输出实时天气信息
            System.out.println("\n");
        }
    }

//get函数
    public int getStatus()
    {
        return status;
    }

    public int getCount()
    {
        return count;
    }

    public String getInfo()
    {
        return info;
    }

    public int getInfocode()
    {
        return infocode;
    }

    public Forecast getForecast()
    {
        return forecast;
    }

    public Lives getLives()
    {
        return lives;
    }
}
