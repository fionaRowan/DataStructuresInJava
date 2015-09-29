README

Fiona Rowan

*main methods live in:
ShortestPath.java
Kruskal.java

*other classes: 
Vertex.java
Edge.java
KruskalConsole.java
CityConsole.java




ShortestPath.java
main method lives here

The command arguments are cityxy.txt and citypairs.txt, in order. 
First, I read in cityxy.
I create an array of strings with the names of each city. (cityNames[])
I also store the city coordinates in int[] x and int[]y, storing them in indices corresponding to their cityNames.
Then, I create an array of vertices using cityNames and the corresponding coordinates, and store them in Vertex[] cities.
This is done by reading in the pairs file and creating two edges for each pair:
one from city A to city B, and the other from B to A. 
I add them to their corresponding vertices.
Then I create a new GUI by instantiating a CityConsole object "cc".




CityConsole.java
ShortestPath GUI

When the console object is created, a GUI comes up asking for user input.
(User inputs source and destination cities where indicated on the GUI).
The button "Show Map" creates a new action listener event.
This action listener reads the text in the user input fields. 
Then, it finds the relevant positions of the input in the city vertex array.
Then, it calls the dijkstra(source) method, finding the shortest path from the source to all other cities.
This method uses a PriorityQueue of vertices to relax edges by adding known vertices into "queue." 
Then the action listener creates the map of cities by creating a new CityGraphic object "cg," an inner class. 
CityGraphic extends JComponent, and drawString's the city names and ovals to indicate the city locations in the correct
positions.
Then, it calls findPath method to the the edges from the destination vertex to its "path"
vertex, and then recursively calls itself on each "path" vertex until it reaches the source city.




Vertex.java
Vertex object

This object implements comparable, so that vertices can be compared by their "dist" fields. 
This class has a LinkedList for keeping the Edge objects that exit from a Vertex.
It has a boolean "known", which is used for Dijkstra's algorithm. 
It has a Vertex "Path", which is used for printing the path resulting from Dijkstra's algorithm, and is the previous Vertex in the shortest path to the Vertex object.
It has a double "dist", which is the cost of going from the source to this vertex. 
It has a String "city," which is the name of the city represented by that Vertex. 
It also contains the x and y coordinates of the city that it represents, to be used in the GUI map. 




Edge.java
Edge object

This object represents edges between cities. 
It has a double "weight" which represents the distance between adjacent cities connected by that Edge. 
It has Vertex "destination," which is where the edge is pointing to. 



Kruskal.java
Main method for programming 2 is here. 
Inner classes: KEdge, MinEdge.

I read in the cityxy file.
I create arrays for the cityNames, and their x and y coordinates (indices correspond to cities in all three arrays). 
I create a TreeSet "edges" of KEdges.
I create edges between each city to make the graph complete, the weight calculated with the dist() method, 
which finds the Euclidian distance between each city.  
I create MinEdges (Kruskal's algorithm) ked, and insert all KEdges in "edges" into this object.
I print all edges in MinEdges to the console, including city pair and their cost, and the total MST weight. 
Then, I create a KruskalConsole object "kc", which creates a map of the MST using the 
ked Kruskal edges, cityNames array of strings, x array of x coords and y array of y coords.

KEdge inner class: 
KEdge is comparable. 
It holds endpoint vertices and the x and y coordinates of each vertex (undirected) 
It has a toString method for printing edges to the console. 
It has a compareTo method, in which edges are smaller if they have lesser cost.

MinEdges inner class:
Vertices are stored in a Vector<HashSet<String>> vertexColl, KEdges are stored in a TreeSet kruskalEdges.
This class has an addEdge method, which gets the endpoint cities of each edge, and if the edge isn't already 
added, we add the edge to the graph. If the endpoint vertices aren't already added, we add the vertices to the graph.




KruskalConsole.java
Kruskal GUI

In the constructor, we set field variables to the parameters, and then we instantiate the 
graph of cities, where they are connected with edges that create a minimum spanning tree. 
We do this by creating a dialog, and adding a scrollpane to that dialog that takes JComponent
"KruskalGraphic kg", an inner class. We pack and then set visible. 

KruskalGraphic inner class: 
Extends JComponent. 
method paintComponent creates Graphics2D object "g2" and calls recPaint on g2.
recPaint draws the vertices by going through the arrays of cities and x and y coordinates and 
drawing ovals and string names. 
Then it draws the edges included in the Kruskal MST, by accessing the "ke" MinEdges object.

