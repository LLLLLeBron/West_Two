/*
221801331 张晨星 软件工程三班
Copyright [2020] by [littlestar].

Main类:最终实现将各省省会、直辖市的天气信息导入数据库
 */
package weather;

public class Main
{
    private static String SQLURL="jdbc:mysql://127.0.0.1:3306/littlestar?useSSL=false&useUnicode=true&characterEncoding=UTF8&serverTimezone=GMT";
    //连接数据库littlestar的URL
    private static String USER="LittleStar";
    //用户名
    private static String PASSWORD="321123klklkl";
    //密码
    private static String DRIVER="com.mysql.cj.jdbc.Driver";
    //驱动类


    private static String APIURL="https://restapi.amap.com/v3/weather/weatherInfo?parameters";
    //要访问API的URL
    private static String KEY="d2ddd2955c27dcee5a07876a491da716";
    //key值

    private static String CITYNUMBER[]={"110000","120000","130100","140100","150100",
            "210100","220100","230100","310000","320100","330100","340100","350100",
            "360100","370100","410100","420100","430100","440100","450100","460100",
            "500100","510100","520100","530100","540100","610100","620100","630100",
            "640100","650100","710000","810000","820000"};
    //各省会、直辖市的编码


    public static void main(String args[])
    {
//测试一些功能
        Weather weather=new Weather();

        //查询一下闽侯的天气情况并输出
        weather.translate(API.result(APIURL,KEY,"350121","base"),"base");
        weather.translate(API.result(APIURL,KEY,"350121","all"),"all");
        weather.print();


//将各省省会、直辖市天气状况导入数据库
        User user=new User(SQLURL,USER,PASSWORD,DRIVER);

        Operation operation=new Operation(CITYNUMBER,user);         //实例化一个Operation对象
        operation.createLivesTable();                               //在指定的数据库中创建实时天气表
        operation.createForecastTable();                            //在指定的数据库中创建预报天气表
        operation.createForcastCityTable();                         //在指定的数据库中创建预报天气-城市编码表
        operation.getAllWeather();                                  //获取全部省会、直辖市的天气状况
        operation.insertLivesWeather();                             //向实时天气表中插入数据
        operation.insertForecastCityTable();                        //向预报天气表中插入数据
        operation.insertForecastTable();                            //向预报天气-城市编码表中插入数据

    }
}
