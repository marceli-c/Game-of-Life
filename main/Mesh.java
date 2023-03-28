package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
import javax.swing.JPanel;

public class Mesh extends JPanel{

	
	int scale;
	Mesh(int scale){
		this.setPreferredSize(new Dimension(1000,1000));
		this.setSize(1000,1000);
		this.scale=scale;
		this.setVisible(true);

	}
	
	@Override
	public void paint(Graphics g) {
		
		g.setColor(Color.black);
		
		for(int i=0;i<=this.getWidth()+150;i+=scale) {
			g.drawLine(i, 0, i, this.getHeight());
			g.drawLine(0, i, this.getHeight(), i);
		}

		
		
	}
	
	
}
