package pl.adammi.saper;

import java.util.Scanner;
import java.util.regex.*;

public class SaperTextInterface implements SaperInterface {
	Scanner scanner;
	Pattern pattern;
	Matcher matcher;
	@Override
	public void initialize() {
		scanner = new Scanner(System.in);
	}
	
	@Override
	public Integer[] getXY() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Integer[] getSettings() {
		Integer[] settings = new Integer[3];

		pattern = Pattern.compile("(\\d+)x(\\d+)");
		String string = new String();
		do {
			println("Enter size: (example 9x9)");
			string = scanner.nextLine();
		}while(!pattern.matcher(string).matches());
		matcher = pattern.matcher(string);
		matcher.matches();
		settings[0] = Integer.parseInt(matcher.group(1));
		settings[1] = Integer.parseInt(matcher.group(2));
		
		pattern = Pattern.compile("(\\d+)");

		do {
			println("Enter mines: (example 10)");
			string = scanner.nextLine();
			
		}while(!pattern.matcher(string).matches());
		matcher = pattern.matcher(string);
		matcher.matches();
		settings[2] = Integer.parseInt(matcher.group(1));
		
		println("Current settings: Board: " + settings[0] +  "x" + settings[1] + " Mines: " + settings[2]);
		
		
		return settings;
	}

	@Override
	public void drawBoard(Board board) {
		int temp;
		println("---------------------------------------------------");
		for(int y = 0; y < board.getSizeX(); y++) {
			for(int x = 0; x < board.getSizeY(); x++) {
				if(board.getFieldChecked(x, y)) {
					temp = board.getFieldValue(x,y);
					if(temp != -1)
						System.out.print(temp + "\t");
					else
						System.out.print("!\t");
				}
				else if(board.getFieldFlag(x, y)) {
					System.out.print("f\t");
				}
				else {
					System.out.print("x\t");
				}
			}
			System.out.print("\n");
		}
		println("---------------------------------------------------");
	}

	@Override
	public void sendMessage(int message) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void close() {
		scanner.close();
	}

	private void println(String string) {
		System.out.println(string);
	}
}
