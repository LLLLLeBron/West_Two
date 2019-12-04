package west_2;
import java.util.*;

public class Bubble extends Ingredient
{
//Bubble保质期对应的毫秒数
	public static final long saveTimeMillisecond=604800000;
	
//构造函数
	public Bubble(Calendar time)		
	{
		super("Bubble",time,7);
	}
	
//默认构造函数
	public Bubble()
	{
		super();
	}
	
//重写toString	
	@Override
	public String toString()			
	{
		return "[name]:{Bubble}"+" [time]:"+"{"+time.get(Calendar.YEAR)+"."+(time.get(Calendar.MONTH)+1)+"."
		+time.get(Calendar.DAY_OF_MONTH)+" "+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+":"+time.get(Calendar.SECOND)+"}  [saveTime]:"+"{7}";
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
		System.out.printf("Bubble:生产日期:%s.%s.%s %s:%s:%s   保质期:%d天\n",time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1
		,time.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND),saveTime);	
	}
	
//对象工厂
	public static Bubble newBubble(int year,int month,int day,int hour,int minute,int second)			//用设定时间生产
	{
		Calendar cal=Calendar.getInstance();
		cal.set(year,month-1,day,hour,minute,second);
		return new Bubble(cal);
	}
	
	public static Bubble newBubble()                                                       	//用系统时间生产
	{
		Calendar cal=Calendar.getInstance();
		return new Bubble(cal);
	}
}
