package pl.adammi.saper;

public interface SaperInterface {
	Integer[] getXY();
	Integer[] getSettings();
	void drawBoard(Board board);
	void sendMessage(int message);
}
