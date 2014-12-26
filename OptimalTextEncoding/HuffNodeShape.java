import java.awt.*;
import java.awt.geom.*;

public class HuffNodeShape 
{
	private int x;
    private int y;
    private int width;
    private HuffTree.HuffNode root;
    
    public HuffNodeShape(int x, int y, int width, HuffTree.HuffNode root)
    {
    	this.x = x;
        this.y = y;
        this.width = width;
        this.root = root;

    }
    public Dimension getSize() 
    {
  	  return new Dimension(width,width);
    }
    
    private int height(HuffTree.HuffNode root) 
    {
		  int leftHeight = 0;
		  int rightHeight = 0; 
		  
		  if (root == null) return 0;

		  else 
		  {
			  leftHeight = height(root.left);
			  rightHeight = height(root.right);
		  
		    if (leftHeight > rightHeight) return (1 + leftHeight);
		    return (1 + rightHeight);
		  }
		}  
    
    private void drawNode(int horiz, int vertic, int wide, HuffTree.HuffNode node, Graphics2D g2)
    {
        int heightMetric = height(node);
        
    	if (node.left != null)
    		drawNode((horiz)- (wide / 2)*heightMetric, vertic + 2*wide, wide, node.left, g2);
    	if (node.right != null)
    		drawNode((horiz )+ (wide / 2)*heightMetric, vertic + 2*wide, wide, node.right, g2);
    	Ellipse2D.Double nodeOval = new Ellipse2D.Double(horiz , vertic, wide, wide);
		 Point2D.Double startPoint
		    = new Point2D.Double(horiz + wide / 2, vertic + wide);
		 Point2D.Double leftChild
		    = new Point2D.Double((horiz + wide / 2) - (wide / 2)*heightMetric, vertic + 2* wide);
		 Point2D.Double rightChild
		    = new Point2D.Double((horiz + wide/2) + (wide/2)*heightMetric, vertic + 2* wide);
		 Line2D.Double leftBranch
		    = new Line2D.Double(startPoint, leftChild);
		 Line2D.Double rightBranch
		    = new Line2D.Double(startPoint, rightChild);
		 
		 g2.draw(nodeOval);
		 if (node.left != null) g2.draw(leftBranch);
		 if (node.right != null) g2.draw(rightBranch);
		 if(node.car != null && node.code!=null){
			 g2.drawString(node.car.toString(), horiz, vertic );
			 g2.drawString(node.code, horiz, vertic + wide);
		 }

    		
    }
  
    
    public void draw(Graphics2D g1)
    {
    	drawNode(x, y, width, root, g1);
    }
    
}

   