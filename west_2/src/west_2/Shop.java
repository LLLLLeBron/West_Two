package west_2;

public interface Shop 
{
	Ingredient addIng(Ingredient ing); 		   																		//Ìí¼ÓÕäÖé
	MilkTea sell(String milkteaName,String ingName) 	throws IngredientTypeException,SoldOutException;			//³öÊÛÄÌ²è
}
