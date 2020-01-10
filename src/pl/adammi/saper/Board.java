package pl.adammi.saper;

import java.util.Random;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;


public class Board {
	private int sizeX;
	private int sizeY;
	private int minesCount;
	private int fieldsCount;
	private Field[][] fields;
	List<Integer[]> minesArray = new ArrayList<>();
	
	public int checkField(int x, int y, int value) {
		return 1;
	}
	
	public Board(int x, int y, int mines) {
		System.out.println("Board initialization");
		//TODO sprawdzic czy nieujemne, dodac max range
		sizeX = x;
		sizeY = y;
		minesCount = mines;
		
		fieldsCount = sizeX*sizeY;
		if(minesCount > fieldsCount-1)
			System.out.println("Too many mines - initialization failure!");
		else {
			fields = new Field [sizeX] [sizeY];
			System.out.println("Board " + sizeX + "x" + sizeY + " filled with " + minesCount + " mines intialised!");
		}
			
		
	}
	public void randomMines(int startFieldX, int startFieldY) {
		int temp;
		Integer[] mineXY = new Integer[2];
		Random rand = new Random();
		

		for(int i = 0; i < minesCount; i++)
		{
			temp = rand.nextInt(fieldsCount);
			mineXY = minePosToXY(temp);
			//System.out.println("fillMines: generated mine position " + mineXY[0] + "." + mineXY[1]);
			if(mineXY[0] == startFieldX && mineXY[1] == startFieldY){
				//System.out.println("fillMines: generated mine position is start position -- skip");
				i--;
			}
			else if(checkMineExists(mineXY)) {
				//System.out.println("fillMines: generated mine position is already used -- skip");
				i--;
			}
			else {
				//System.out.println("fillMines: generated mine position is OK -- use");
				minesArray.add(mineXY);
				System.out.println("fillMines: generated mine position " + mineXY[0] + "." + mineXY[1]);
			}		
		}
		
	}
	public void createFields() {
		Integer[] temp = new Integer[2];
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				temp[0] = x;
				temp[1] = y;
				fields[x][y] = new Field();
				/*if(checkMineExists(temp))
					fields[x][y] = new Field(x,y,-1);
				else {
					fields[x][y] = new Field(x,y,countMines(x,y));
				}
				*/
				
			}
		}
	}
	
	public void fillFields() {
		Integer[] temp = new Integer[2];
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				temp[0] = x;
				temp[1] = y;
				
				if(checkMineExists(temp))
					fields[x][y].setField(-1);
				else {
					fields[x][y].setField(countMines(x,y));
				}
				
				
			}
		}
	}
	
	public void printBoardTest() {
		int temp;
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				temp = fields[x][y].getValue();
				if(temp != -1)
					System.out.print(temp + "\t");
				else
					System.out.print("x\t");
			}
			System.out.print("\n");
		}
	}
	public void printBoard() {
		int temp;
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				if(fields[x][y].isChecked()) {
					temp = fields[x][y].getValue();
					if(temp != -1)
						System.out.print(temp + "\t");
					else
						System.out.print("!\t");
				}
				else if(fields[x][y].isFlag()) {
					System.out.print("f\t");
				}
				else {
					System.out.print("x\t");
				}
			}
			System.out.print("\n");
		}
	}
		
	public int checkField(int x, int y) {
		int val;
		val = fields[x][y].getValue();
		fields[x][y].setCheck();
		
		//TODO check win
		if(val == 0)checkZeros();
		if(val>=0) return 0;
		else return -1;
	}
	
	public int checkWin() {
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				if(!fields[x][y].isChecked() && fields[x][y].getValue()>=0)
					return 0;
			}
		}
		for(int y = 0; y < sizeY; y++) {
			for(int x = 0; x < sizeX; x++) {
				fields[x][y].setCheck();
			}
		}
		return 1;
	}
	
	private void checkZeros() {
		boolean repeat;
		do {
			repeat = false;
			for(int y = 0; y < sizeY; y++) {
				for(int x = 0; x < sizeX; x++) {
					if(fields[x][y].isChecked() && fields[x][y].getValue()==0) {
						for(int i = y-1; i<= y+1;i++) {
							if(i < 0 || i+1>sizeY) continue;
							for(int j = x-1; j<= x+1; j++) {
								if(j < 0 || j+1 > sizeX)continue;
								if(fields[j][i].isChecked())continue;
								fields[j][i].setCheck();
								if(fields[j][i].getValue() == 0)
									repeat = true;
							}
						}
					}
						
				}
			}
		}while(repeat);	
	}

	private int countMines(int positionX, int positionY) {
		int temp=0;
		Integer [] coord = new Integer [2];
		for(int y = positionY - 1; y <= positionY + 1; y++) {
			if(y < 0 || y+1 > sizeY) continue;
			for(int x = positionX -1; x <= positionX+1; x++) {
				if(x < 0 || x+1 > sizeX) continue;
				coord[0] = x;
				coord[1] = y;
				if(checkMineExists(coord))
					temp++;
			}
		}
		return temp;
	}
	
	private Integer[] minePosToXY(int position) {
		//TODO sprawdzenie pozycji
		Integer[] ret = new Integer[2];
		ret[0] = position%sizeX;
		ret[1] = position/sizeX;
		return ret;		
	}
	
	private boolean checkMineExists(Integer[] mine) {
		if((mine[1] < 0 || mine[1] + 1 > sizeY) || (mine[0] < 0 || mine[0] + 1 > sizeX)) {
			System.out.println("checkMineExists - invalid coord: " + mine[0] + "." + mine[1]);
			throw new IllegalArgumentException();
		}
			
		for(Integer[] temp: minesArray) {
			if(Arrays.equals(temp, mine))
				return true;
		}
		return false;
	}
	
}
