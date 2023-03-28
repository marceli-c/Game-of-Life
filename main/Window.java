package main;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame implements ActionListener{

	Timer timer;
	int windowSize;
	Board board;
	final int boardSize=50;
	Rectangles[][] recArr,recArrCache;
	int scale;
	Mesh mesh;
	JPanel panel = new JPanel();
	RecDraw recDraw;
	
	
	Window(int windowSize){
		recArrCache=new Rectangles[boardSize][boardSize];
		recArr=new Rectangles[boardSize][boardSize];
		scale=windowSize/boardSize;

		

		initializeRectangles(50);
		
		
		
		
		this.windowSize=windowSize;
		windowInitialize(windowSize);
		
		recArr[7][5].setState(true);			//GLIDER
		recArr[7][6].setState(true);
		recArr[7][7].setState(true);
		recArr[6][7].setState(true);
		recArr[5][6].setState(true);
		

		
		//System.out.println(checkProximity(6,6));
		timerInitialize(76);
		//showRecArr();
		
		mesh=new Mesh(scale);
		this.add(mesh);
		
		recDraw=new RecDraw(boardSize,board,recArr);
		this.add(recDraw);
		
		
		
		//ystem.out.println("scale "+scale);
	}
	
	private void windowInitialize(int size) {
		
		
		this.setVisible(true);
		this.setResizable(false);
		this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
		this.setSize(size,size);
		
		
		
	}
	
	private void timerInitialize(int delay) {
		timer=new Timer(delay,this);
    	timer.start();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//checkBoardState();
		
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				checkPattern(i,j);
			}
		}
		
		implementCache();
		recDraw.repaint();
		
	}
	

	private int checkProximity(int x,int y) {
		
		int neighbours=0;

		//System.out.println("x:, y: "+x+" "+y);
		//System.out.println("state: "+recArr[x-1][y].getState());

			if((x > 0 && y > 0) 					&& 	recArr[x-1][y-1].getState()==true) 			neighbours++;			//LEWA STRONA
			if((x > 0 )								&& 	recArr[x-1][y].getState()==true) 			neighbours++;
			if((x > 0 && y < boardSize-1 ) 			&& 	recArr[x-1][y+1].getState()==true) 			neighbours++;
			
			if((y > 0) 								&& 	recArr[x][y-1].getState()==true) 			neighbours++;			// NAD
			if((y < boardSize-1) 					&& 	recArr[x][y+1].getState()==true) 			neighbours++;			// POD
			
			if((x < boardSize-1 && y > 0) 			&&	recArr[x+1][y-1].getState()==true) 			neighbours++;			//PRAWA STRONA
			if((x < boardSize-1) 					&&	recArr[x+1][y].getState()==true) 			neighbours++;
			if((x < boardSize-1 && y < boardSize-1) &&	recArr[x+1][y+1].getState()==true) 			neighbours++;
			
		//System.out.println(neighbours);
		return neighbours;
		
		
	}
	
	private void checkPattern(int x, int y) {											//TODO RECTANGLES ZMIANA NA OBIEKT NA POCŻATKU WIADOMO OCBV
		int neighbours=checkProximity(x,y);
		boolean state=recArr[x][y].getState();
		
		//System.out.println("before x: "+x+" y: "+y+" state: "+recArr[x][y].getState()+" neighbours: "+neighbours);
		
		if (x >= boardSize || x<=-1 || y<= -1 || y>= boardSize)			recArrCache[x][y].setState(false);
														
		if		(neighbours<2 && state==true) 							recArrCache[x][y].setState(false);
		
		else if	(neighbours>2 && neighbours<4 && state==true) 			recArrCache[x][y].setState(true);
		
		else if	(neighbours>3 && state==true) 							recArrCache[x][y].setState(false);
		
		else if	(neighbours==3 && state==false) 						recArrCache[x][y].setState(true);		
		
		else 															recArrCache[x][y].setState(state);
		
		//System.out.println(" state: after "+recArrCache[x][y].getState());
		
	}

	
	

	private void initializeRectangles(int size) {
		int h=0;
		for(int i=0;i<boardSize;i++) {
			int k=0;
			for(int j=0;j<boardSize;j++) {
				k+=scale;
				recArr[i][j]=new Rectangles(size);
				recArr[i][j].setBounds(k, h, scale, scale);
				
				//System.out.println("setting bounds i="+i+" j="+j+" k="+k+" h="+h+" scale="+scale);
				
				recArrCache[i][j]=new Rectangles(size);														//CREATING CACHE
				
			}
			h+=scale;
		}
	}
	public void showRecArr() {
		for(int i=0;i<recArr.length;i++) {
			for(int j=0;j<recArr.length;j++) {
				System.out.print(recArr[i][j].getState()+" ");
			}
			System.out.println();
		}
	
	}	
	
	public void implementCache() {
		for(int i=0;i<boardSize;i++) {
			for(int j=0;j<boardSize;j++) {
				recArr[i][j].setState(recArrCache[i][j].getState());
			}
		}
	}
}
