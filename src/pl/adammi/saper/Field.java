package pl.adammi.saper;

public class Field {
	private int coordX;
	private int coordY;
//	private int neighboursCount=0;
	
	// -1 mine, positive mines count
	private int value;
	private boolean checked;
	private boolean flag;
	

	public Field(int x, int y, int value) {
		coordX = x;
		coordY = y;
		checked = false;
		this.value  = value;
		flag = false;
	}
	
	public void setField(int val) {
		value = val;
		checked  = false;
	}
	
	public int checkField() {
		checked = true;
		return value;
	}
	public void setFlag() {
		flag = !flag;
	}
	public void setCheck() {
		checked = true;
	}
	public boolean isFlag() {
		return flag;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public int getX() {
		return coordX;
	}
	
	public int getY() {
		return coordY;
	}
	public int getValue() {
		return value;
	}
	
}
