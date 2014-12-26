import java.util.LinkedList;
import java.util.List;

// SeparateChaining Hash table class
//
// CONSTRUCTION: an approximate initial size or default of 101
//
// ******************PUBLIC OPERATIONS*********************
// void insert( x )       --> Insert x
// void remove( x )       --> Remove x
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
	
    public SeparateChainingHashTable( )
    {
        this( DEFAULT_TABLE_SIZE );
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
            theLists[ i ] = new LinkedList<String>( );
    }

    /**
     * Insert into the hash table. If the item is
     * already present, then do nothing.
     * @param x the item to insert.
     */
    
    public void insert(String x){
    	this.insert(x.toLowerCase(), tableSize);
    }
    public void insert( String x, int size )
    {
        List<String> whichList = theLists[ hash( x, size ) ];
        if( !whichList.contains( x ) )
        {
            whichList.add( x );
            currentSize++;

           // Rehash; see Section 5.5
            if( ++currentSize > theLists.length )
                rehash( );
        }
    }

    /**
     * Find an item in the hash table.
     * @param x the item to search for.
     * @return true if x is not found.
     */
    public boolean contains(String x){
    	return this.contains(x, tableSize);
    }
    public boolean contains( String x, int size )
    {
        List<String> whichList = theLists[ hash( x, size) ];
        return whichList.contains( x );
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
     * @param key the String to hash.
     * @param tableSize the size of the hash table.
     * @return the hash value.
     */
    public static int hash( String key, int tableSize )
    {
        int hashVal = 0; 

        for( int i = 0; i < key.length( ); i++ )
            hashVal = 37 * hashVal + key.charAt( i );

        hashVal %= tableSize;
        if( hashVal < 0 )
            hashVal += tableSize;
        return hashVal;
    }

    @SuppressWarnings("unchecked")
	private void rehash( )
    {
        List<String> [ ]  oldLists = theLists;

            // Create new double-sized, empty table
        theLists = new List[ nextPrime( 2 * theLists.length ) ];
        for( int j = 0; j < theLists.length; j++ )
            theLists[ j ] = new LinkedList<>( );

            // Copy table over
        currentSize = 0;
        for( List<String> list : oldLists )
            for( String item : list )
                insert( (String) item, theLists.length );
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


    static int tableSize; 
    private static final int DEFAULT_TABLE_SIZE = 2000;

        /** The array of Lists. */
    private static List<String>[] theLists; 
    private int currentSize;
}

