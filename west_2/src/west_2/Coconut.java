package west_2;
import java.util.*;

public class Coconut extends Ingredient
{
//Coconut�����ڶ�Ӧ�ĺ�����
	public static final long saveTimeMillisecond=432000000;
	
//���캯��
	public Coconut(Calendar time)			
	{
		super("Coconut",time,5);
	}
	
//Ĭ�Ϲ��캯��
	public Coconut()
	{
		super();
	}
	
//��дtoString	
	@Override
	public String toString()				
	{
		return "[name]:{Coconut}"+" [time]:"+"{"+time.get(Calendar.YEAR)+"."+(time.get(Calendar.MONTH)+1)+"."
		+time.get(Calendar.DAY_OF_MONTH)+" "+time.get(Calendar.HOUR_OF_DAY)+":"+time.get(Calendar.MINUTE)+":"+time.get(Calendar.SECOND)+"}  [saveTime]:"+"{5}";
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
		System.out.printf("Coconut:��������:%s.%s.%s %s:%s:%s   ������:%d��\n",time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1
		,time.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND),saveTime); 
	}

//���󹤳�
	public static Coconut newCoconut(int year,int month,int day,int hour,int minute,int second)			//���趨ʱ������
	{
		Calendar cal=Calendar.getInstance();
		cal.set(year, month-1, day, hour, minute,second);
		System.out.println(cal.get(Calendar.MONTH));
		return new Coconut(cal);
	}
	
	public static Coconut newCoconut()                                                       	//��ϵͳʱ������
	{
		Calendar cal=Calendar.getInstance();
		return new Coconut(cal);
	}
}
