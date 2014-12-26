/*
 * Fiona Rowan
 * Data Structures
 * 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.util.PriorityQueue;

import javax.swing.*;

public class CityConsole extends JFrame implements ActionListener{
	
	//variables
	private static Vertex[] city; 
	private static String[] cities; 
	private int[] x; 
	private int[] y;
	private JTextArea sourceField, destField;
	static double inf = Double.POSITIVE_INFINITY;

	public CityConsole(String[] names, int[] h, int[] v, Vertex[] cities){
		super("City Console");
		x = h; 
		y = v; 
		this.cities = names;
		city = cities;
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		getContentPane().setLayout(
				new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
		
		
		//user input and button
		sourceField = new JTextArea("Enter Source City", 12, 3);
		destField = new JTextArea("Enter Destination City", 12, 3);
		JButton mapButton = new JButton("Show Map");
		mapButton.addActionListener(this);

		//add widgets to frame
		add(sourceField);
		add(destField);
		add(mapButton);
		pack();
		setSize(500, 300);
		setVisible(true);


	}


	//actual map
	public class CityGraphic extends JComponent{
		private int width;
		private int height; 
		private int destPosition;
		String[] cities;
		int[] x;
		int[] y; 
		CityGraphic(String[] names, int[] h, int[] v, int destPos){
			x=h;
			y= v; 
			destPosition = destPos;
			cities = names; 
			width=3000;
			height = 2000; 
			setPreferredSize(new Dimension(width, height));
		}


		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D)g; 
			recPaint(g2);
		}
		
		//draw ovals with city names in the right coordinate positions
		public void recPaint(Graphics2D g2){
			for(int i = 0; i<cities.length; i++){
				g2.drawString(cities[i], x[i], height - 100 - y[i]+40);
				g2.drawOval(x[i], height - 100 - y[i], 25, 25);
			}
			
			//draw the edges for the shortest path to destination
			findPath(g2, city[destPosition], height);

		}



	}





	@Override
	public void actionPerformed(ActionEvent e) {
		try{
			if(e.getActionCommand().equals("Show Map")){
				String source = sourceField.getText(); 
				String dest = destField.getText(); 

				//we will find the relevant positions in vertex array 
				int sourcePosition = -1;
				int destPosition = -1;
				for(int k=0; k<cities.length; k++){
					if(city[k].city.equals(source))
						sourcePosition = k;
					if(city[k].city.equals(dest))
						destPosition = k; 
				}
				
				//find shortest path from source to all other cities
				dijkstra(city[sourcePosition]);

				//create and pack the map
				JDialog d = new JDialog(this, "Dijkstra Console");
				CityGraphic cg = new CityGraphic(cities, x, y, destPosition);
				JScrollPane scrollPane = new JScrollPane(cg, 
						JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
						JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				d.add(scrollPane, BorderLayout.CENTER);
				d.pack();
				d.setSize(cg.getWidth()+50, cg.getHeight()+50);
				d.setVisible(true);
			}
		}catch(Exception ex){
			System.out.println("Something ain't right!");
		}





	}

	//dijkstra algorithm
	public static void dijkstra(Vertex s) throws Exception{

		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>();
		for(int i=0; i<29; i++){
			city[i].dist=inf; 
			city[i].known=false; 

		}

		s.dist=0;

		queue.add(s);

		while(!queue.isEmpty()){
			Vertex a = queue.poll(); 

			//see all edges that leave a

			for(Edge e : a.adjacency){
				Vertex b = e.destination;
				double weight = e.weight;
				double pathThruA = a.dist + weight; 
				if(pathThruA < b.dist){
					queue.remove(b);
					b.dist=pathThruA; 
					b.path = a; 
					queue.add(b);
				}
			}
		}
	}

	//method for drawing the shortest path edges from destination vertex to source vertex
	public static void findPath(Graphics2D g2, Vertex v, int height){
		if(v.path!= null){
			g2.drawLine(v.x, height - 100 - v.y, v.path.x, height - 100 - v.path.y);
			findPath(g2, v.path, height);	
		}
	}

}

