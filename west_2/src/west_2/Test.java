package west_2;
import java.util.*;

public class Test
{
	public static void main(String[] args)
	{
		TeaShop teashop=new TeaShop();
		System.out.println("西二奶茶店开张!!!");
		//进货
		for(int cnt=0;cnt<10;cnt++)					//购进25个珍珠，购进2个椰果
		{
			teashop.addIng(Bubble.newBubble(2019, 11, 26, 13, 39, 55));
			teashop.addIng(Bubble.newBubble(2019, 11, 29, 23, 40,12));
		}
		for(int cnt=0;cnt<5;cnt++)
		{
			teashop.addIng(Bubble.newBubble()); 
		}
		teashop.addIng(Coconut.newCoconut(2019, 11, 30, 13, 39, 55));
		teashop.addIng(Coconut.newCoconut());
		System.out.printf("进货成功!!!\n");
		try
		{
			teashop.sell("love", "Bubble");    			//出售一杯名为love的珍珠奶茶
			teashop.sell("hate", "Coconut");			//出售一杯名为hate的椰果奶茶
			teashop.sell("happiness","Coconut");		//出售一杯名为happiness的椰果奶茶
			teashop.sell("fear", "Coconut");			//出售一杯名为fear的椰果奶茶
		}
		catch(IngredientTypeException |SoldOutException SOE)
		{
			System.out.print(SOE.getMessage());
		}
		try
		{
			teashop.sell("peace", "Bubbel");    			//想出售一杯名为peace的珍珠奶茶,但是把配料名字说错了
		}
		catch(IngredientTypeException |SoldOutException SOE)
		{
			System.out.print(SOE.getMessage());
		}
		
		//自己自定义奶茶
		String milkteaName;
		String IngName;
		System.out.println("输入你要点的奶茶名:");	
		Scanner in=new Scanner(System.in);
		milkteaName=in.next();
		System.out.println("输入你要的配料:");
		IngName=in.next();
		in.close();
		try
		{
			teashop.sell(milkteaName, IngName);
		}
		catch(IngredientTypeException |SoldOutException SOE)
		{
			System.out.print(SOE.getMessage());
		}
		
		
		
		//测试toString 方法
		System.out.printf("\n开始测试toString方法:\n");
		Ingredient ing1=Bubble.newBubble();
		Ingredient ing2=Coconut.newCoconut(2011,12,3,11,40,3);
		System.out.println(ing1.toString());
		System.out.println(ing2.toString());
		
		MilkTea milktea1=new MilkTea("xixixi",ing1);
		MilkTea milktea2=new MilkTea("lalala",ing2);
		System.out.println(milktea1.toString());
		System.out.println(milktea2.toString());
	}
}
