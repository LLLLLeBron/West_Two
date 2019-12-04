package west_2;
import java.util.*;

public class Bubble extends Ingredient
{
//Bubble�����ڶ�Ӧ�ĺ�����
	public static final long saveTimeMillisecond=604800000;
	
//���캯��
	public Bubble(Calendar time)		
	{
		super("Bubble",time,7);
	}
	
//Ĭ�Ϲ��캯��
	public Bubble()
	{
		super();
	}
	
//��дtoString	
	@Override
	public String toString()			
	{
		return "[name]:{Bubble}"+" [time]:"+"{"+time.get(Calendar.YEAR)+"."+(time.get(Calendar.MONTH)+1)+"."
		+time.get(Calendar.DAY_OF_MONTH)+" "+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+":"+time.get(Calendar.SECOND)+"}  [saveTime]:"+"{7}";
	}

//get����	
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
	
//�������
	@Override
	public void Print()
	{
		//���������Ϣ���������ڡ������ڣ�
		System.out.printf("Bubble:��������:%s.%s.%s %s:%s:%s   ������:%d��\n",time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1
		,time.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND),saveTime);	
	}
	
//���󹤳�
	public static Bubble newBubble(int year,int month,int day,int hour,int minute,int second)			//���趨ʱ������
	{
		Calendar cal=Calendar.getInstance();
		cal.set(year,month-1,day,hour,minute,second);
		return new Bubble(cal);
	}
	
	public static Bubble newBubble()                                                       	//��ϵͳʱ������
	{
		Calendar cal=Calendar.getInstance();
		return new Bubble(cal);
	}
}
