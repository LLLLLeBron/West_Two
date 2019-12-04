package west_2;
import java.util.*;

public class Coconut extends Ingredient
{
//Coconut保质期对应的毫秒数
	public static final long saveTimeMillisecond=432000000;
	
//构造函数
	public Coconut(Calendar time)			
	{
		super("Coconut",time,5);
	}
	
//默认构造函数
	public Coconut()
	{
		super();
	}
	
//重写toString	
	@Override
	public String toString()				
	{
		return "[name]:{Coconut}"+" [time]:"+"{"+time.get(Calendar.YEAR)+"."+(time.get(Calendar.MONTH)+1)+"."
		+time.get(Calendar.DAY_OF_MONTH)+" "+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+":"+time.get(Calendar.SECOND)+"}  [saveTime]:"+"{5}";
	}

//get函数
	@Override
	public String getName()
	{
		return name;
	}
	
	@Override
	public Calendar getTime()
	{
		return time;
	}
	
	@Override
	public int getSaveTime()
	{
		return saveTime;
	}
	
//输出函数
	@Override
	public void Print()
	{	
		//输出配料信息（生产日期、保质期）
		System.out.printf("Coconut:生产日期:%s.%s.%s %s:%s:%s   保质期:%d天\n",time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1
		,time.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND),saveTime); 
	}

//对象工厂
	public static Coconut newCoconut(int year,int month,int day,int hour,int minute,int second)			//用设定时间生产
	{
		Calendar cal=Calendar.getInstance();
		cal.set(year, month-1, day, hour, minute,second);
		System.out.println(cal.get(Calendar.MONTH));
		return new Coconut(cal);
	}
	
	public static Coconut newCoconut()                                                       	//用系统时间生产
	{
		Calendar cal=Calendar.getInstance();
		return new Coconut(cal);
	}
}
