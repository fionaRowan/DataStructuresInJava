/*
*	Fiona Rowan, Data structures
*	NonEmptyStackException class 
*	to be thrown if the stack should be empty but isn't.
*
*/

public class NonEmptyStackException extends RuntimeException
{


	private static final long serialVersionUID = 1L;

	public NonEmptyStackException(){
		System.out.println("The Stack still has a lil something.");
	}
}