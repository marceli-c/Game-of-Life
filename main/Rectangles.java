package main;

import java.awt.Rectangle;

public class Rectangles extends Rectangle{

	int size;
	boolean state;
	
	Rectangles(int size){
		
		this.size=size;
		this.state=false;
		
	}
	Rectangles(int size, boolean state){
		
		this.size=size;
		this.state=state;
	}
	
	public boolean getState() {
		return this.state;
	}
	public void setState(boolean state) {
		this.state=state;
	}
	
}
	
	
	
	

