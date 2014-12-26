/*
 * Fiona Rowan
 * Data Structures
 * 
 * 
 */


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.TreeSet;
import java.util.Vector;
import java.util.HashSet;

public class Kruskal
{
    public static void main(String[] args) throws NumberFormatException, IOException
    {	
    	String xyFile = args[0];

		//reads in file xy to get the city names, x and y coordinates

		String[] cityNames = new String[29];
		int[] x= new int[29];
		int[] y = new int[29];
		BufferedReader reader = new BufferedReader(new FileReader(xyFile));
		String line = null;
		int i = 0;
		while ((line = reader.readLine()) != null){
			String[] items = line.split("\\s+");
			cityNames[i]=items[0];
			x[i] = Integer.parseInt(items[1]); 
			y[i] = Integer.parseInt(items[2]); 
			i++;
		}
		reader.close();
		
        //sort edges prior to algorithm
        TreeSet<KEdge> edges = new TreeSet<KEdge>();
        
        //complete graph edges
		for(int m = 0; m < 28; m++){
			for(int n=m+1; n<29; n++){
				int p1=x[m];
				int p2=y[m]; 
				int q1 = x[n]; 
				int q2=y[n]; 
				double weight = dist(p1, p2, q1, q2);
				KEdge e = new KEdge(cityNames[n], x[n], y[n], cityNames[m], x[m], y[m], weight);
				edges.add(e);
			}
		}
		
		//kruskal edges
        MinEdges ked = new MinEdges();

        for (KEdge edge : edges) {
            ked.addEdge(edge);
        }

        //print to console the min edges and total weight
        System.out.println("Kruskal MST:");
        int total = 0;
        for (KEdge edge : ked.getEdges()) {
            System.out.println(edge);
            total += edge.weight;
        }
        System.out.println("Total weight is " + total);
        
        KruskalConsole kc= new KruskalConsole(ked, cityNames, x, y);
    }
    public static double dist(int p1, int p2, int q1, int q2){
		double distance= Math.sqrt((p1 -q1)*(p1 -q1)+(p2-q2)*(p2-q2));
		return distance; 
	}
}

class KEdge implements Comparable<KEdge>
{
	//variables
    String vertexA, vertexB;
    double weight;
    int xa, ya, xb, yb;

    //undirected edges for Kruskal
    public KEdge(String a, int x1, int y1, String b,int x2, int y2, double weight)
    {
        this.vertexA = a;
        this.vertexB = b;
        this.weight = weight;
        xa = x1;
        ya = y1;
        xb = x2;
        yb = y2;
    }
    @Override
    public String toString()
    {
        return vertexA + ", " + vertexB +": cost = " + weight;
    }
    public int compareTo(KEdge edge)
    {
    	return (this.weight < edge.weight) ? -1: 1;
    }
}

//find minimum edges using kruskal's algorithm
class MinEdges
{
	//collections of vertices and kedges
    Vector<HashSet<String>> vertexColl = new Vector<HashSet<String>>();
    //easily sortable edges
    TreeSet<KEdge> kruskalEdges = new TreeSet<KEdge>();

    
    //make edges and vertices accessible
    public TreeSet<KEdge> getEdges()
    {
        return kruskalEdges;
    }
    HashSet<String> getVertices(String vertex)
    {
        for (HashSet<String> vertices : vertexColl) {
            if (vertices.contains(vertex)) {
                return vertices;
            }
        }
        return null;
    }


    //insert edge into tree set
    public void addEdge(KEdge edge)
    {
        String vertexA = edge.vertexA;
        String vertexB = edge.vertexB;

        HashSet<String> verticesA = getVertices(vertexA);
        HashSet<String> verticesB = getVertices(vertexB);

        
        //if edge isn't in graph, we add edge to graph
        //if vertices aren't in graph, we add vertices to graph
        if (verticesA == null) {
            kruskalEdges.add(edge);
            if (verticesB == null) {
                HashSet<String> newVertices= new HashSet<String>();
                newVertices.add(vertexA);
                newVertices.add(vertexB);
                vertexColl.add(newVertices);
            }
            else {
                verticesB.add(vertexA);        	
            }
        }
        else {
            if (verticesB == null) {
                verticesA.add(vertexB);
                kruskalEdges.add(edge);
            }
            else if (verticesA != verticesB) {
                verticesA.addAll(verticesB);
                vertexColl.remove(verticesB);
                kruskalEdges.add(edge);
            }
        }
    }
}


