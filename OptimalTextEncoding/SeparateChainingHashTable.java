import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;



// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// boolean contains( x )  --> Return true if x is present
// void makeEmpty( )      --> Remove all items

/**
 * Separate chaining table implementation of hash tables.
 * Note that all "matching" is based on the equals method.
 * @author Mark Allen Weiss
 */
public class SeparateChainingHashTable<AnyType>
{
	/**
	 * Construct the hash table.
	 */

	public SeparateChainingHashTable(boolean isWeight )
	{
		this( DEFAULT_TABLE_SIZE );
		weight = isWeight;
	}

	/**
	 * Construct the hash table.
	 * @param size approximate table size.
	 */
	@SuppressWarnings("unchecked")
	public SeparateChainingHashTable( int size )
	{	
		tableSize = size; 
		theLists = new LinkedList[tableSize ];
		for( int i = 0; i < theLists.length; i++ )
			theLists[ i ] = new LinkedList<CharAndWeight>( );
	}

	/**
	 * Insert into the hash table. If the item is
	 * already present, then do nothing.
	 * @param x the item to insert.
	 */

	public void insert(CharAndWeight x){
		this.insert(x, tableSize);
	}
	public void insert( CharAndWeight x, int size )
	{
		List<CharAndWeight> whichList = theLists[ hash( x, size ) ];
		if( !contains( x, tableSize ) )
		{
			whichList.add( x );
			currentSize++;

			// Rehash; see Section 5.5
			if( currentSize+1 > theLists.length )
				rehash( );
		}
		else {
			if(weight)
				incrementWeight(x, size);
			else
				;
		}
	}

	/**
	 * Find an item in the hash table.
	 * @param x the item to search for.
	 * @return true if x is not found.
	 */
	public boolean contains(CharAndWeight x){
		return this.contains(x, tableSize);
	}
	public boolean contains( CharAndWeight x, int size )
	{
		boolean doesContain =false;
		List<CharAndWeight> whichList = theLists[ hash( x, size) ];
		ListIterator<CharAndWeight> j = whichList.listIterator();
		while (j.hasNext()){
			if(j.next().equals(x)){
				doesContain=true;
			}
		}
		return doesContain;
	}

	public void incrementWeight(CharAndWeight x, int size){
		List<CharAndWeight> whichList = theLists[ hash( x, size ) ];
		ListIterator<CharAndWeight> j = whichList.listIterator();
		while(j.hasNext()){
			CharAndWeight toChange = j.next();
			toChange.weight++;
			if(toChange.equals(x)){
				j.set(toChange);
			}
		}   
	}

	/**
	 * Make the hash table logically empty.
	 */
	public void makeEmpty( )
	{
		for( int i = 0; i < theLists.length; i++ )
			theLists[ i ].clear( );
		currentSize = 0;    
	}

	/**
	 * A hash routine for String objects.
	 * @param  the String to hash.
	 * @param tableSize the size of the hash table.
	 * @return the hash value.
	 */
	public static int hash( CharAndWeight x, int tableSize )
	{
		int charInt = x.character;
		int hashVal = 37* charInt; 
		hashVal %= tableSize;
		if( hashVal < 0 )
			hashVal += tableSize;
		return hashVal;
	}

	@SuppressWarnings("unchecked")
	private void rehash( )
	{
		List<CharAndWeight> [ ]  oldLists = theLists;

		// Create new double-sized, empty table
		theLists = new List[ nextPrime( 2 * theLists.length ) ];
		for( int j = 0; j < theLists.length; j++ )
			theLists[ j ] = new LinkedList<>( );

		// Copy table over
		currentSize = 0;
		for( List<CharAndWeight> list : oldLists )
			for( CharAndWeight item : list )
				insert( (CharAndWeight) item, theLists.length );
	}
	/**
	 * Internal method to find a prime number at least as large as n.
	 * @param n the starting number (must be positive).
	 * @return a prime number larger than or equal to n.
	 */
	private static int nextPrime( int n )
	{
		if( n % 2 == 0 )
			n++;

		for( ; !isPrime( n ); n += 2 )
			;

		return n;
	}

	/**
	 * Internal method to test if a number is prime.
	 * Not an efficient algorithm.
	 * @param n the number to test.
	 * @return the result of the test.
	 */
	private static boolean isPrime( int n )
	{
		if( n == 2 || n == 3 )
			return true;

		if( n == 1 || n % 2 == 0 )
			return false;

		for( int i = 3; i * i <= n; i += 2 )
			if( n % i == 0 )
				return false;

		return true;
	}

	public void printTable(){
		System.out.println("Table results:");
		for(int i = 0; i<theLists.length; i++){
			List<CharAndWeight> whichList = theLists[i];
			ListIterator<CharAndWeight> j = whichList.listIterator();
			if(weight){
				while(j.hasNext()){

					double weight = whichList.get(j.nextIndex()).weight;
					Character car = whichList.get(j.nextIndex()).character;
					System.out.println("Char: "+ car + "  Weight: " + weight); 
					j.next();

				}
			}
				else{
					while(j.hasNext()){
						String codez = whichList.get(j.nextIndex()).code;
						Character car = whichList.get(j.nextIndex()).character;
						System.out.println("Char: "+ car + "  Code: " + codez); 
						j.next();

					}
				}
			}




			System.out.println();
			System.out.println("number of unique characters: " +currentSize);
		}
	
		public String findCode(Character c){
			String huffCode ="oops"; 
			CharAndWeight x = new CharAndWeight(c);
			List<CharAndWeight> whichList = theLists[ hash(x, tableSize) ];
			ListIterator<CharAndWeight> j = whichList.listIterator();
			while (j.hasNext()){
				CharAndWeight b = j.next();
				if(b.equals(x)){
					huffCode = b.code; 
				}
				else{
					;
				}
			}
			
			
	
			return huffCode; 
		}

		public int size(){
			return currentSize;
		}

		public int listLength(){
			return theLists.length;
		}

		public List<CharAndWeight>[] list(){
			return theLists;
		}


		static int tableSize; 
		private static final int DEFAULT_TABLE_SIZE = 200;

		/** The array of Lists. */
		private static List<CharAndWeight>[] theLists; 
		private int currentSize;
		private boolean weight = true;

	}


