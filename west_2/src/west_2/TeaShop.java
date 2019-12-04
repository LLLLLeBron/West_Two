package west_2;
import java.util.*;

public class TeaShop implements Shop 
{
	private ArrayList<Ingredient> bubble;
	private ArrayList<Ingredient> coconut;

//默认构造函数
	public TeaShop()
	{
		bubble=new ArrayList<Ingredient>();
		coconut=new ArrayList<Ingredient>();
	}
	
//构造函数
	public TeaShop(ArrayList<Ingredient> bubble,ArrayList<Ingredient> coconut)
	{
		this.bubble=bubble;
		this.coconut=coconut;
	}
	
//添加珍珠
	private Ingredient add(Bubble ing)
	{
		bubble.add(ing);
		return ing;
	}
	
//添加椰果
	private Ingredient add(Coconut ing)
	{
		coconut.add(ing);
		return ing;
	}
	
//实现Shop接口中的addIng方法
	public Ingredient addIng(Ingredient ing)
	{
		//判断配料类型
		if(ing instanceof Bubble)
			add((Bubble)ing);				//调用添加珍珠
		else if(ing instanceof Coconut)
			add((Coconut)ing);				//调用添加椰果
		return ing;
	}
	
//实现Shop接口中的sell方法  
	public MilkTea sell(String milkteaName,String ingName) throws IngredientTypeException,SoldOutException
	{
		ArrayList<Ingredient> ing;
		MilkTea mt;
		long saveTime;
		//判断配料类型
		if(ingName.equals("Bubble"))
		{
			ing=bubble;									//选择需要取的配料所在的配料列表
			saveTime=Bubble.saveTimeMillisecond;		//选择配料对应的保质期
		}
		else if(ingName.equals("Coconut"))
		{
			ing=coconut;								//选择需要取的配料所在的配料列表
			saveTime=Coconut.saveTimeMillisecond;		//选择配料对应的保质期
		}
		else
		{
			throw new IngredientTypeException("配料类型错误!!!\n");
		}
		mt=fitOut(milkteaName,ing,saveTime);		//调用装配函数装配奶茶
		System.out.printf("出售成功!!!\n");
		mt.Print();			//输出奶茶的信息
		return mt;
	}

//装配奶茶
	public MilkTea fitOut(String milkteaName,ArrayList<Ingredient> ing,long saveTime)   throws SoldOutException
	{
		//传入奶茶名，需要取的配料所在的配料列表，配料对应的保质期
		Ingredient Ing;
		Calendar time=Calendar.getInstance();
		if(ing.isEmpty())
			throw new SoldOutException("配料已售完!!!\n");
		while(ing.size()!=0)
		{
			Ing=ing.get(0);
			long t=time.getTimeInMillis()-Ing.getTime().getTimeInMillis();
			if(t>saveTime)			//过期处理
			{
				ing.remove(0);
			}
			else
			{
				break;
			}
		}
		Ing=ing.get(0);								//取出配料
		ing.remove(0);								//将配料从列表中移除
		return new MilkTea(milkteaName,Ing);		//返回配好的奶茶
	}
}
	
	
	
	
	
 