package west_2;
import java.util.*;

public abstract class Ingredient 
{
	protected String name;			//配料名
	protected Calendar time;     	//生产日期
	protected int saveTime;			//保质期（天）
	
//构造函数	
	public Ingredient(String name,Calendar time,int saveTime)	
	{
		this.name=name;
		this.time=time;
		this.saveTime=saveTime;
	}

//默认构造函数
	public Ingredient()
	{
		this.name=null;
		this.time=null;
		this.saveTime=0;
	}
	
//抽象方法	
	@Override
	public abstract String toString();
	//get函数
	public abstract String getName();
	public abstract Calendar getTime();
	public abstract int getSaveTime();
	
	public abstract void Print();		//输出配料信息（生产日期、保质期）	
}
