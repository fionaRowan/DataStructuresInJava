/*  
*   Fiona Rowan, Data structures
*   BinarySearchTree class 
*   modified from Weiss' class to implement lazy delete
*   modified methods: isEmpty, insert, remove, contains, printTree, findMin, findMax, makeEmpty, 
*   added methods: rightSubtreeExists, leftSubtreeExists
*   added instance variables: nodesCounter, deletedCounter.
*/

public class BinarySearchTree<AnyType extends Comparable<? super AnyType>>
{
    /**
     * Construct the tree.
     */
    public BinarySearchTree( )
    {
        root = null;
    }

    /**
     * Insert into the tree; duplicates are ignored.
     * @param x the item to insert.
     */
    public void insert( AnyType x )
    {
        root = insert( x, root );
    }

    /**
     * Remove from the tree. Nothing is done if x is not found.
     * @param x the item to remove.
     */
    public void remove( AnyType x )
    {
        root = remove( x, root );
    }

    /**
     * Find the smallest item in the tree.
     * @return smallest item or null if empty.
     */
    public AnyType findMin( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMin( root ).element;
    }

    /**
     * Find the largest item in the tree.
     * @return the largest item of null if empty.
     */
    public AnyType findMax( )
    {
        if( isEmpty( ) )
            throw new UnderflowException( );
        return findMax( root ).element;
    }

    /**
     * Find an item in the tree.
     * @param x the item to search for.
     * @return true if not found.
     */
    public boolean contains( AnyType x )
    {
        return contains( x, root );
    }

    /**
     * Make the tree logically empty.
     */
    public void makeEmpty( )
    {
        root = null;
        deletedCounter = 0; 
        nodesCounter = 0; 
    }

    /**
     * Test if the tree is logically empty.
     * @return true if number of binary nodes is equal to the number of deleted nodes.
     */
    public boolean isEmpty( )
    {
        return deletedCounter == nodesCounter; 
        
    }

    /**
     * Print the tree contents in sorted order.
     */
    public void printTree( )
    {
        if( isEmpty( ) )
            System.out.println( "Empty tree" );
        else
            printTree( root );
    }

    /**
     * Internal method to insert into a subtree.
     * @param x the item to insert.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> insert( AnyType x, BinaryNode<AnyType> t )
    {
        //increment number of nodes if new binary node is inserted
        if( t == null ){
            nodesCounter = nodesCounter +1; 
            return new BinaryNode<>( x, null, null ); 

        }
        
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = insert( x, t.left );
        else if( compareResult > 0 ){
            t.right = insert( x, t.right );

        }
        //decrement number of deleted nodes if existing binary node goes from isDeleted to !isDeleted
        else if(t.isDeleted){
            t.isDeleted = false;
            deletedCounter = deletedCounter - 1; 
        }
        else
            ;  // Duplicate; do nothing
        
        return t;

    }

    /**
     * Internal method to remove from a subtree.
     * @param x the item to remove.
     * @param t the node that roots the subtree.
     * @return the new root of the subtree.
     */
    private BinaryNode<AnyType> remove( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return t;   // Item not found; do nothing
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            t.left = remove( x, t.left );
        else if( compareResult > 0 )
            t.right = remove( x, t.right );
        else 
        {
            //if node is to be deleted, mark as deleted and then increment deletedCounter
            if (!t.isDeleted){
                t.isDeleted=true;
                deletedCounter = deletedCounter +1; 
            }
            else
                ;

        }

        return t;
    }

    /**
     * Internal method to find the smallest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the smallest item.
     */
    private BinaryNode<AnyType> findMin( BinaryNode<AnyType> t )
    {
        if( t == null )
            return null;
        //if there are undeleted binary nodes in the left subtree...
        else if(leftSubtreeExists(t))
            return findMin(t.left);
        else if(!t.isDeleted)
            return t; 

        //otherwise, check the right subtree
        else if (rightSubtreeExists(t))
            return findMin(t.right);
        else
            return null;
    }

    /**
     * Internal method to find the largest item in a subtree.
     * @param t the node that roots the subtree.
     * @return node containing the largest item.
     */
    private BinaryNode<AnyType> findMax( BinaryNode<AnyType> t )
    {
         if( t == null )
             return null;

         //if there are undeleted binary nodes in the right subtree... 
         else if(rightSubtreeExists(t))
            return findMax(t.right);
         else if(!t.isDeleted)
            return t; 

        //otherwise, check the left subtree
         else if (leftSubtreeExists(t))
            return findMax(t.left);
         else
            return null;
    }
    
    private boolean leftSubtreeExists(BinaryNode<AnyType> t){
        boolean exists; 
        if(t==null)
            exists=false; 
        
        if(t.left != null){
            if(!t.left.isDeleted)
                exists = true; 
            else if(leftSubtreeExists(t.left)|| rightSubtreeExists(t.left) )
                exists = true; 
            else 
                exists = false; 
        }
        else
            exists = false; 
        return exists;
    }
    
    private boolean rightSubtreeExists(BinaryNode<AnyType> t){
        boolean exists; 
        if(t==null)
            exists = false; 
        if(t.right != null){
            if (!t.right.isDeleted)
                exists = true; 
            else if(leftSubtreeExists(t.right) || rightSubtreeExists(t.right))
                exists = true; 
            else
                exists = false; 
        }
        else 
            exists = false; 
        return exists; 
    }

    /**
     * Internal method to find an item in a subtree.
     * @param x is item to search for.
     * @param t the node that roots the subtree.
     * @return node containing the matched item.
     */
    private boolean contains( AnyType x, BinaryNode<AnyType> t )
    {
        if( t == null )
            return false;
            
        int compareResult = x.compareTo( t.element );
            
        if( compareResult < 0 )
            return contains( x, t.left );
        else if( compareResult > 0 )
            return contains( x, t.right );

        //if binary node exists but is marked as deleted, tree does not contain. 
        else if(t.isDeleted)
            return false;
        else
            return true;   // Match
    }

    /**
     * Internal method to print a subtree in sorted order.
     * @param t the node that roots the subtree.
     */
    private void printTree( BinaryNode<AnyType> t )
    {
        if( t != null )
        {
            printTree( t.left );

            //only print out elements that are not deleted
            if(!t.isDeleted)
                System.out.println( t.element );
            printTree( t.right );
        }
    }

    /**
     * Internal method to compute height of a subtree.
     * @param t the node that roots the subtree.
     */
    @SuppressWarnings("unused")
    private int height( BinaryNode<AnyType> t )
    {
        if( t == null )
            return -1;
        else
            return 1 + Math.max( height( t.left ), height( t.right ) );    
    }
    
    // Basic node stored in unbalanced binary search trees
    private static class BinaryNode<AnyType>
    {
            // Constructors
        @SuppressWarnings("unused")
        BinaryNode( AnyType theElement )
        {
            this( theElement, null, null );
        }

        BinaryNode( AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt )
        {
            element  = theElement;
            left     = lt;
            right    = rt;
            isDeleted = false;
        }

        AnyType element;            // The data in the node
        BinaryNode<AnyType> left;   // Left child
        BinaryNode<AnyType> right;  // Right child
        boolean isDeleted; 
    }


      /** The tree root. */
    private BinaryNode<AnyType> root;

    //counters keep track of number of deleted and number of existing nodes, for isEmpty
    private int nodesCounter=0;
    private int deletedCounter = 0;

}