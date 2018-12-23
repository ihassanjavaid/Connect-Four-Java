package Front_End;

import Back_End.Judgement;

public interface Playable {
  void makeMove(int columnNumber);
  void setCredentials(String playerMarker, int row, Judgement judgement);
  void deactivateButton(int ButtonNumber);
}
