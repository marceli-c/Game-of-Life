package main;

public class Board {

	boolean[][] board;
	int size;
	
	Board(int size){
		this.size=size;
		
		this.board=new boolean[size][size];
		
		showBoard();
		
	}
	
	Board(int x, int y){
		
		this.board=new boolean[x][y];
		
		//showBoard();
		
	}
	
	public boolean[][] getBoard() {
		return this.board;
	}
	
	private void showBoard() {
		for(boolean[] i:this.board) {
			for(boolean j:i) {
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}
	public void setState(int i,int j, boolean value) {
		this.board[i][j]=value;
	}
	public boolean getState(int i,int j) {
		return this.board[i][j];
	}
	public int getSize() {
		return size;
	}
	
	
	
}
