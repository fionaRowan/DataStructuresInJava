/*
*	Fiona Rowan, Data structures
*	MyStack class
*	implements stack ADT as an array
*	methods: clear, push, isEmpty, top, pop
*/

public class MyStack<AnyType>{
	AnyType[] stack;
	int counter = 0; 

	@SuppressWarnings("unchecked")
	public MyStack(int x){
		stack = (AnyType[]) new Object[x]; 
	}
	
	public void clear(){
		counter = 0; 
	}
	
	public AnyType push(AnyType x){
		stack[counter] = x;
		counter++; 
		return x; 
	}
	
	public boolean isEmpty(){
		boolean empty; 
		if (this.counter == 0)
			empty = true; 
		else 
			empty = false; 
		return empty;
	}
	
	public AnyType top(){
		return stack[counter-1];
	}

	public AnyType pop(){
		AnyType x = this.top(); 
		counter--; 
		return x; 
	}


}
