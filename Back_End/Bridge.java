public interface Bridge {
    void PlayerChosenColumn(int ColumnButton);
    int CheckColumnAvailability (int ColumnButton);
    void MarkPosition (int row, int column);

    void SwapPlayers();

    void CheckWin ();

}
