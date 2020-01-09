/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

API类:用于连接天气查询的API服务，并将获取内容返回到一个JSONObject对象
 */

package weather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


import org.json.JSONArray;
import org.json.JSONObject;

public class API        //使用天气查询API服务的工具类
{
    public static JSONObject result(String url,String key,String cityNumber, String extensions)
    //天气查询的返回结果，保存在JSONObject里
    {
        //params用于存储要请求的参数
        Map<String,Object> params = new HashMap<String,Object>();
        //key值
        params.put("key",key);
        //查询的城市编号
        params.put("city",cityNumber);
        //查询的气象类型
        params.put("extensions",extensions);
        //返回格式
        params.put("output", "JSON");
        //调用httpRequest方法，这个方法主要用于请求地址，并加上请求参数
        String string = httpRequest(url,params);
        //处理返回的JSON数据并返回
        JSONObject js=new JSONObject(string);
        return js;
    }

    private static String httpRequest(String requestUrl,Map<String,Object> params)  //请求地址，并加上请求参数
    {
        //buffer用于接受返回的字符
        StringBuffer buffer = new StringBuffer();
        try
        {
            //建立URL，把请求地址给补全，其中urlencode（）方法用于把params里的参数给取出来
            URL url = new URL(requestUrl+"?"+urlencode(params));
            //打开http连接
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();
            httpUrlConn.setDoInput(true);
            httpUrlConn.setRequestMethod("GET");
            httpUrlConn.connect();

            //获得输入
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            //将bufferReader的值给放到buffer里
            String str = null;
            while ((str = bufferedReader.readLine()) != null)
            {
                buffer.append(str);
            }
            //关闭bufferReader和输入流
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
            inputStream = null;
            //断开连接
            httpUrlConn.disconnect();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //返回字符串
        return buffer.toString();
    }


    public static String urlencode(Map<String,Object> data)         //取出data里的数据
    {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,Object> i : data.entrySet())
        {
            try
            {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue()+"","UTF-8")).append("&");
            }
            catch (UnsupportedEncodingException e)
            {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
