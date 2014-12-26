/*
*	Fiona Rowan, Data structures
*	UnderflowException class
*	to be thrown if an ADT is empty but should contain a node.
*
*/

public class UnderflowException extends RuntimeException
{

	private static final long serialVersionUID = 1L;

	public UnderflowException(){
		System.out.println("The ADT was empty!");
	}
}