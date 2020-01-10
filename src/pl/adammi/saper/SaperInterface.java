package pl.adammi.saper;

public interface SaperInterface {
	public void initialize(); 
	Integer[] getXY();
	Integer[] getSettings();
	void drawBoard(Board board);
	void sendMessage(int message);
	public void close();
}
