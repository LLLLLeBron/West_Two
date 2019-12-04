package west_2;

public class IngredientTypeException extends Exception  //配料类型错误
{
	private static final long serialVersionUID = 1L;
	private String message;				//错误信息
	
//构造函数
	public IngredientTypeException(String message)
	{
		super(message);
		this.message=message;
	}
	
//默认构造函数
	public IngredientTypeException()
	{
		super("");
		this.message=null;
	}
	
//输出异常函数
	public void ExceptionOutput()
	{
		System.out.print(message);
	}
	
//get函数
	public String getMessage()
	{
		return message;
	}
}
