import javax.swing.*;

import java.awt.*;

public class HuffNodeComponent extends JComponent {

    private HuffNodeShape m;
    private int x = 0;
    private int y = 0;
    
    public HuffNodeComponent(HuffNodeShape m) {
	   this.m = m;
	   setPreferredSize(new Dimension(150,100));
    }

    public void paintComponent(Graphics g) {

	Graphics2D g2 = (Graphics2D)g;
	m.draw(g2);

    }

}
