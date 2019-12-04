package west_2;
import java.util.*;

public class TeaShop implements Shop 
{
	private ArrayList<Ingredient> bubble;
	private ArrayList<Ingredient> coconut;

//Ĭ�Ϲ��캯��
	public TeaShop()
	{
		bubble=new ArrayList<Ingredient>();
		coconut=new ArrayList<Ingredient>();
	}
	
//���캯��
	public TeaShop(ArrayList<Ingredient> bubble,ArrayList<Ingredient> coconut)
	{
		this.bubble=bubble;
		this.coconut=coconut;
	}
	
//�������
	private Ingredient add(Bubble ing)
	{
		bubble.add(ing);
		return ing;
	}
	
//���Ҭ��
	private Ingredient add(Coconut ing)
	{
		coconut.add(ing);
		return ing;
	}
	
//ʵ��Shop�ӿ��е�addIng����
	public Ingredient addIng(Ingredient ing)
	{
		//�ж���������
		if(ing instanceof Bubble)
			add((Bubble)ing);				//�����������
		else if(ing instanceof Coconut)
			add((Coconut)ing);				//�������Ҭ��
		return ing;
	}
	
//ʵ��Shop�ӿ��е�sell����  
	public MilkTea sell(String milkteaName,String ingName) throws IngredientTypeException,SoldOutException
	{
		ArrayList<Ingredient> ing;
		MilkTea mt;
		long saveTime;
		//�ж���������
		if(ingName.equals("Bubble"))
		{
			ing=bubble;									//ѡ����Ҫȡ���������ڵ������б�
			saveTime=Bubble.saveTimeMillisecond;		//ѡ�����϶�Ӧ�ı�����
		}
		else if(ingName.equals("Coconut"))
		{
			ing=coconut;								//ѡ����Ҫȡ���������ڵ������б�
			saveTime=Coconut.saveTimeMillisecond;		//ѡ�����϶�Ӧ�ı�����
		}
		else
		{
			throw new IngredientTypeException("�������ʹ���!!!\n");
		}
		mt=fitOut(milkteaName,ing,saveTime);		//����װ�亯��װ���̲�
		System.out.printf("���۳ɹ�!!!\n");
		mt.Print();			//����̲����Ϣ
		return mt;
	}

//װ���̲�
	public MilkTea fitOut(String milkteaName,ArrayList<Ingredient> ing,long saveTime)   throws SoldOutException
	{
		//�����̲�������Ҫȡ���������ڵ������б����϶�Ӧ�ı�����
		Ingredient Ing;
		Calendar time=Calendar.getInstance();
		if(ing.isEmpty())
			throw new SoldOutException("����������!!!\n");
		while(ing.size()!=0)
		{
			Ing=ing.get(0);
			long t=time.getTimeInMillis()-Ing.getTime().getTimeInMillis();
			if(t>saveTime)			//���ڴ���
			{
				ing.remove(0);
			}
			else
			{
				break;
			}
		}
		Ing=ing.get(0);								//ȡ������
		ing.remove(0);								//�����ϴ��б����Ƴ�
		return new MilkTea(milkteaName,Ing);		//������õ��̲�
	}
}
	
	
	
	
	
 