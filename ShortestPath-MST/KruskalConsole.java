/*
 * Fiona Rowan
 * Data Structures
 * 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;

public class KruskalConsole extends JFrame {
	private MinEdges ke; 
	private String[] cities; 
	private int[] x; 
	private int[] y;
	
	public KruskalConsole(MinEdges k, String[] names, int[] h, int[] v){
		super("Kruskal Console");
		ke=k; 
		x = h; 
		y = v; 
		cities = names;
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		//dialog
		JDialog d = new JDialog(this, "Kruskal Console");
		
		//kruskal graphic
		KruskalGraphic kg = new KruskalGraphic(ke, cities, x, y);
		
		//scrollpane
		JScrollPane scrollPane = new JScrollPane(kg, 
				JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, 
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		//pack and set visible
		d.add(scrollPane, BorderLayout.CENTER);
		d.pack();
		d.setSize(kg.getWidth()+50, kg.getHeight()+50);
		d.setVisible(true);
	}

	
	//draw the map! 
	public class KruskalGraphic extends JComponent{
		MinEdges ked;
		private int width;
		private int height; 
		String[] cities;
		int[] x;
		int[] y; 
		KruskalGraphic(MinEdges k, String[] names, int[] h, int[] v){
			ked=k;
			x=h;
			y= v; 
			cities = names; 
			width=3000;
			height = 2000; 
			setPreferredSize(new Dimension(width, height));
			
		}
		
		
		public void paintComponent(Graphics g){
			Graphics2D g2 = (Graphics2D)g; 
			recPaint(g2);
		}
		
		//draw the nodes and the city names on the graph
		public void recPaint(Graphics2D g2){
			for(int i = 0; i<cities.length; i++){
				g2.drawString(cities[i], x[i], height - 100 - y[i]+40);
				g2.drawOval(x[i], height - 100 - y[i], 25, 25);
			}
			
			//draw the edges of MST
			for(KEdge edge: ke.getEdges()){
				g2.drawLine(edge.xa, height - 100 - edge.ya, edge.xb, height - 100 - edge.yb);
			}
		}
		public int getWidth(){
			return width;
		}
		
		public int getHeight(){
			return height; 
		}
		
	}
	
}

