package pl.adammi.saper;

import java.util.Scanner;

public class Game {
	private static final int sizeMaxX = 50;
	private static final int sizeMaxY = 50;
	private static final int minesMax = 1000;


	public static void main(String[] args) {
		int x; 
		int y;
		int result = 0;
		boolean ret = false;
		
		Integer[] settings = new Integer[3];
		Scanner scanner = new Scanner(System.in);
		
		SaperInterface iface = new SaperTextInterface();
		iface.initialize();
		do {
			settings = iface.getSettings();
			ret = checkSettings(settings);
			//TODO send message			
		}while(!ret);

/*		
		
		println("***************");
		println("*****SAPER*****");
		println("***************");
		println("Enter X size");
		x = scanner.nextInt();
		println("Enter Y size");
		y = scanner.nextInt();
	*/	
		
		
		
		
		
		Board board = new Board(settings[0],settings[1],settings[2]);
		board.createFields();
		iface.drawBoard(board);
		
		println("Enter X");	
		x = scanner.nextInt();
		println("Enter Y");
		y = scanner.nextInt();
	
		board.randomMines(x-1, y-1);
		board.fillFields();
		result = board.checkField(x-1, y-1);
		iface.drawBoard(board);
		
		while(result == 0) {
			println("Enter X");
			x = scanner.nextInt();
			println("Enter Y");
			y = scanner.nextInt();
			result = board.checkField(x-1, y-1);
			if(result == 0) {
				result = board.checkWin();
			}
			println("-----------");
			iface.drawBoard(board);
			println("-----------");
		}
		
		if(result == 1)
			println("You win!");
		if(result == -1)
			println("You lose!");
		
	
		println("-----------");
		iface.drawBoard(board);
		scanner.close();
	}
	

	static private void println(String string) {
		System.out.println(string);
	}
	static private boolean checkSettings(Integer[] settings) {
		if(settings[0]<1 || settings[0]>sizeMaxX)
			return false;
		if(settings[1]<1 || settings[1]>sizeMaxY)
			return false;
		if(settings[2]<1 || settings[2]>minesMax || sizeMaxX*sizeMaxY <= settings[2]-1)
			return false;
		return true;
	}

}
