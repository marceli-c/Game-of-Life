package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JPanel;


public class RecDraw extends JPanel{

	Rectangles recArr[][];
	Board board;
	int boardSize;
	
	public RecDraw(int boardSize,Board board,Rectangles[][] recArr){
		this.board=board;
		this.recArr=recArr;
		this.setPreferredSize(new Dimension(1000,1000));
		this.setSize(1000,1000);
		this.boardSize=boardSize;
		this.setVisible(true);
		this.setBackground(new Color(0,0,0,0));
		this.setOpaque(false);

	}
	
	
	
	@Override
	public void paint(Graphics g) {
		
		Graphics2D g2d=(Graphics2D) g;
		paintRecs(g2d);
		
		
		
		
	}
	
	public void paintRecs(Graphics2D g2d) {
		
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {

				if(recArr[i][j].getState()==true) {
					g2d.setColor(Color.black);
					g2d.fill(recArr[i][j]);
				}
				else {
					g2d.setColor(Color.white);
					g2d.fill(recArr[i][j]);
				}
			}
		}
	}
	
	
	
}
