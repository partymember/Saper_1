package pl.adammi.saper;

import java.util.Scanner;

public class Game {


	public static void main(String[] args) {
		int x; 
		int y;
		int result = 0;
		Scanner scanner = new Scanner(System.in);
		println("***************");
		println("*****SAPER*****");
		println("***************");
		println("Enter X size");
		x = scanner.nextInt();
		println("Enter Y size");
		y = scanner.nextInt();
		
		Board board = new Board(x,y,5);
		board.createFields();
		board.printBoard();
		println("Enter X");
		
		x = scanner.nextInt();
		println("Enter Y");
		y = scanner.nextInt();
		
		
		
		board.randomMines(x-1, y-1);
		board.fillFields();
		result = board.checkField(x-1, y-1);
		board.printBoard();
		
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
			board.printBoard();
			println("-----------");
		}
		
		if(result == 1)
			println("You win!");
		if(result == -1)
			println("You lose!");
		
	
		println("-----------");
		board.printBoard(); 
		scanner.close();
	}
	

	static private void println(String string) {
		System.out.println(string);
	}

}
