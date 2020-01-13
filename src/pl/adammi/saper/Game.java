package pl.adammi.saper;

public class Game {
	private static final int sizeMaxX = 50;
	private static final int sizeMaxY = 50;
	private static final int minesMax = 1000;


	public static void main(String[] args) {
		int result = 0;
		boolean ret = false;
		
		Integer[] settings = new Integer[3];
		Integer[] command = new Integer[3];
		
		SaperInterface iface = new SaperTextInterface();
		iface.initialize();
		
		do {
			ret = true;
			settings = iface.getSettings();
			if(!checkSettings(settings)) {
				ret = false;
				iface.sendMessage("Incorrect size or mines number. Size up to " + sizeMaxX + "x" + sizeMaxY + " Mines number up to " + minesMax);
				continue;
			}	
		}while(!ret);
		
		Board board = new Board(settings[0],settings[1],settings[2]);
		board.createFields();
		iface.drawBoard(board);

		do {
			command = iface.getXY();
			if(!checkCommand(board, command)){
				iface.sendMessage("Incorrect coordinates");
				command[2]=0;
				continue;
			}
			if(command[2]==1) {
				board.flagField(command[0]-1, command[1]-1);
				iface.updateBoard(board);
			}
				
		}while(command[2] != 2);
		

		board.randomMines(command[0]-1, command[1]-1);
		board.fillFields();
		result = board.checkField(command[0]-1, command[1]-1);
		iface.updateBoard(board);
		
		while(result == 0) {
		
			do {
				command = iface.getXY();
				if(!checkCommand(board, command)){
					iface.sendMessage("Incorrect coordinates");
					command[2]=0;
					continue;
				}
			}while(command[2] == 0);
			
			if(command[2]==2) {
				result = board.checkField(command[0]-1, command[1]-1);
				if(result == 0) {
					result = board.checkWin();
				}
			}
			else if(command[2]==1)
				board.flagField(command[0]-1, command[1]-1);

			iface.updateBoard(board);
		}
		
		if(result == 1)
			iface.sendMessage("You win!");
		if(result == -1)
			iface.sendMessage("You lose!");

		board.checkBoard();
		iface.updateBoard(board);
		iface.close();
	}
	
	static private boolean checkSettings(Integer[] settings) {
		if(settings[0]<1 || settings[0]>sizeMaxX)
			return false;
		if(settings[1]<1 || settings[1]>sizeMaxY)
			return false;
		if(settings[2]<1 || settings[2]>minesMax || (settings[0]*settings[1] <= settings[2]-1))
			return false;
		return true;
	}
	
	static private boolean checkCommand(Board board, Integer[] command) {
		if(command[0]<1 || command[0]>board.getSizeX())
			return false;
		if(command[1]<1 || command[1]>board.getSizeY())
			return false;
		return true;
	}

}
