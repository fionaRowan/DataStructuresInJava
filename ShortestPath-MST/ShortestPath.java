/*
 * Fiona Rowan
 * Data Structures
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.PriorityQueue;


public class ShortestPath {
	
	//array of city vertices
	static Vertex[] cities = new Vertex[29];

	public static void main(String[] args) throws Exception{
		try{

			String xyFile = args[0];
			String pairsFile=args[1]; 
			
			
			//read in names of the city
			String[] cityNames = new String[29];
			
			//collect coordinates with same index
			int[] x = new int[29];
			int[] y = new int[29];
			BufferedReader reader = new BufferedReader(new FileReader(xyFile));
			String line = null;
			int i = 0;
			while ((line = reader.readLine()) != null){
				String[] items = line.split("\\s+");
				cityNames[i]=items[0];
				x[i] = Integer.parseInt(items[1]);
				y[i]=Integer.parseInt(items[2]);
				i++;
			}
			reader.close();


			//create array of vertices
			for(int k = 0; k<29; k++){
				cities[k] = new Vertex(cityNames[k], x[k], y[k]);
			}

			//reads in file of city pairs and adds edges
			BufferedReader reader2 = new BufferedReader(new FileReader(pairsFile));
			String line2 = null;
			
			//edges going to and from each city pair
			Edge aToB;
			Edge bToA; 
			int positionA=-1; 
			int positionB= -1;
			while ((line2 = reader2.readLine()) != null){
				//string array of each line
				String[] itemz = line2.split("\\s+");
				double weight = Double.parseDouble(itemz[2]);
				//find the corresponding names for the city in order to find the city index
				for(int j = 0; j<cityNames.length; j++){
					if(itemz[1].equals(cityNames[j])){
						positionB = j;
					}
					if(itemz[0].equals(cityNames[j])){
						positionA=j;
					}
				}
				
				//add new edges pointing to the vertex at the corresponding index
				aToB=new Edge(weight, cities[positionB]);
				bToA=new Edge(weight, cities[positionA]);
				
				//add edges to each of the vertices
				cities[positionA].adjacency.add(aToB);
				cities[positionB].adjacency.add(bToA);

			}
			reader2.close();		

			//create new GUI
			CityConsole cc = new CityConsole(cityNames, x, y, cities);


		}catch(ArrayIndexOutOfBoundsException e){
			System.out.println("Index out of bounds! Please enter four arguments: the xy file, the city pairs file, the source city name and the destination city name.");
			System.out.println(e.getMessage());
		}catch(Exception e){
			System.out.println("Something ain't right!");
		}
	}
}
