package pl.adammi.saper;

public interface SaperInterface {
	public void initialize(); 
	Integer[] getXY();
	Integer[] getSettings();
	void drawBoard(Board board);
	void updateBoard(Board board);
	void sendMessage(String message);
	void sendMessage(int message);
	public void close();
}
