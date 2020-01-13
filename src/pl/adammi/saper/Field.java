package pl.adammi.saper;

public class Field {
	// -1 mine, positive mines count
	private int value;
	private boolean checked;
	private boolean flag;
	
	public Field() {
		checked = false;
		value = 0;
		flag  = false;
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
	public int getValue() {
		return value;
	}
	
}
