package west_2;
import java.util.*;

public class MilkTea 
{
	private String name;			//奶茶的名字
	private Ingredient ing;			//配料
	
	
//构造函数	
	public MilkTea(String name,Ingredient ing)		
	{
		this.name=name;
		this.ing=ing;
	}
	
//默认构造函数
	public MilkTea()
	{
		this.name=null;
		this.ing=null;
	}
	
//get函数
	public String getName()
	{
		return name;
	}
	
	public Ingredient getIng()
	{
		return ing;
	}
	
	
//set函数
	public String setName(String name)
	{
		this.name=name;
		return name;
	}
	
	public Ingredient setBurden(Ingredient ing)
	{
		this.ing=ing;
		return ing;
	}
	
	
//重写toString
	@Override
	public String toString()
	{
		return "[name]:"+"{"+name+"}"+" [ing]:"+"{"+ing.toString()+"}";
	}

//输出函数
	public void Print()
	{
		Calendar time=Calendar.getInstance();
		System.out.printf("奶茶名:%s 配料:%s 生产日期:%s.%s.%s %s:%s:%s\n",name,ing.getName(),time.get(Calendar.YEAR),time.get(Calendar.MONTH)+1
		,time.get(Calendar.DAY_OF_MONTH),time.get(Calendar.HOUR_OF_DAY),time.get(Calendar.MINUTE),time.get(Calendar.SECOND));
	}
}
