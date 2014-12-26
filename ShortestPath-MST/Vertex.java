/*
 * Fiona Rowan
 * Data Structures
 * 
 * 
 */

import java.util.LinkedList;


public class Vertex implements Comparable<Vertex>{
	
	public LinkedList<Edge> adjacency = new LinkedList<>();
	public boolean known;
	public double dist; 
	public Vertex path; 
	public String city;
	public int x, y;


	public Vertex(String town, int h, int v) {
		city = town; 
		x=h;
		y=v;
	}
	
	public Vertex(){
		city="error";
	}


	@Override
	public int compareTo(Vertex b) {
		
		int comparison;
		if(this.dist> b.dist)
			comparison=1;
		if(this.dist<b.dist)
			comparison=-1;
		else
			comparison=0;
		return comparison;
			
	}
	
	public void printVertex(){
		System.out.println(city);
	}
	
	
}
