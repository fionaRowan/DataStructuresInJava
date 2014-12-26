public class UnderflowException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public UnderflowException(){
		System.out.println("The ADT was empty!");
	}
}