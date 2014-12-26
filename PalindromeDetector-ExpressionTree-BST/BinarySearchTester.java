/*
*   Fiona Rowan, Data structures
*   BinarySearchTester class
*   implements all lazy-delete modified methods in BinarySearchTree class
*   
*/

public class BinarySearchTester {

    // Test program
    public static void main( String [ ] args )
    {
        try{
            BinarySearchTree<Integer> t = new BinarySearchTree<>( ); 

            System.out.println( "Building a tree.... " );

            //build tree
            t.insert(100);
            t.insert(90);
            t.insert(110);
            t.insert(80);
            t.insert(95);
            t.insert(105);
            t.insert(120);
            t.insert(70);
            t.insert(85);
            t.insert(92);
            t.insert(96);
            t.insert(102);
            t.insert(106);
            t.insert(115);
            t.insert(130);

            System.out.print("contents of the new tree are: \n");
            t.printTree(); 
            System.out.print("does this tree contain 70? (should be true): "+ t.contains(70) +"\n");
            System.out.println("Is this tree empty? (should be false): " + t.isEmpty());

            System.out.println("the min is " +t.findMin());
            System.out.println("the max is " +t.findMax());
            //remove things
            t.remove(90);
            t.remove(70);
            t.remove(80);
            t.remove(130);


            System.out.print("after deleting 90, 70, 130, and 80, the contents of the tree are: \n");
            t.printTree();

            System.out.println("the new min is " +t.findMin());
            System.out.println("the new max is " +t.findMax());

            System.out.println("does the tree contain 70? (should be false because 70 was deleted): "+ t.contains(70));
            System.out.println("now we lazy-delete all elements.");
            t.remove(100);
            t.remove(90);
            t.remove(110);
            t.remove(80);
            t.remove(95);
            t.remove(105);
            t.remove(120);
            t.remove(70);
            t.remove(85);
            t.remove(92);
            t.remove(96);
            t.remove(102);
            t.remove(106);
            t.remove(115);
            t.remove(130);
            System.out.println("is the tree empty? (should be true): "+ t.isEmpty());
            

        }catch(Exception e){
            System.out.println("Something ain't right!");
            System.out.println(e.getMessage());
        }
    }
}