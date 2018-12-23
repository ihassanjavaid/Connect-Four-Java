package Back_End;

public class Judge {
    private GameBoard gameBoard;

    Judgement judgement;

    public Judge (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    protected Judgement makeJudgement (String playerMarker, int playerRow, int playerColumn) {
        /*
        The judge will make a judgement which will tell four things, namely:
        1. Has a player won?
        2. In what direction did the player win?
        3. Which player won?
        4. The list of piece coordinates
         */
        judgement.setPlayerMarker(playerMarker);

        judgement.setVictoryFlag(horizontalJudge(playerMarker, playerRow, playerColumn));

        if (!judgement.getVictoryFlag())
            judgement.setVictoryFlag(verticalJudge(playerMarker, playerRow, playerColumn));
        if (!judgement.getVictoryFlag())
            judgement.setVictoryFlag(diagonalJudge(playerMarker, playerRow, playerColumn));

        return judgement;
    }

    private boolean horizontalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){
        judgement.initialiseWinList(5);
        boolean victoryFlag = false;
        int column = PlayerColumn;

        //winList.add(column);
        judgement.addToWinList(column);// Store the player's latest move's column number

        int victoryCounter = 0;

        // Checking West from the point of player's latest move

        while ( column >= 0 && !victoryFlag ){

            column--;

            if ( column >=0 ){

                if (gameBoard.getPlayerMarker(PlayerRow,column).equals(PlayerMarker)){
                    judgement.addToWinList(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        judgement.addToWinList(PlayerRow);   // Store the row in which the player has won
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            judgement.flushWinList();
            judgement.initialiseWinList(5);
        }

        // Checking East from the point of player's latest move

        column = PlayerColumn;
        judgement.addToWinList(column);

        while ( column < 7 && !victoryFlag ){

            column++;

            if ( column < 7 ){

                if (gameBoard.getPlayerMarker(PlayerRow,column).equals(PlayerMarker)){
                    judgement.addToWinList(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        judgement.addToWinList(PlayerRow);   // Store the row in which the player has won
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            judgement.flushWinList();
        }
        else
            judgement.setWinDirection("Horizontal");

        return victoryFlag;
    }

    private boolean verticalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){
        judgement.initialiseWinList(5);
        boolean victoryFlag = false;
        int column = PlayerColumn;
        int row = PlayerRow;

        judgement.addToWinList(row);  //Store the player's latest move's row number
        int victoryCounter = 0;

        // Checking South from the point of player's latest move

        while ( row < 6 && !victoryFlag ){

            row++;

            if ( row < 6 ){

                if (gameBoard.getPlayerMarker(PlayerRow,column).equals(PlayerMarker)){
                    judgement.addToWinList(row); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        judgement.addToWinList(PlayerColumn);   // If there is a same piece below the current piece, store its coordinates
                        victoryFlag = true;
                        judgement.setWinDirection("Vertical");
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            judgement.flushWinList();  // in python: winList = []  #If no one won, reset the win list
        }

        return victoryFlag;
    }

    private boolean diagonalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){
        judgement.initialiseWinList(6);
        boolean victoryFlag = false;

        int subRow = 0;
        int subColumn = 0;
        int victoryCounter = 0;

        // Checking first Diagonal
        // North West

        subRow = PlayerRow;
        subColumn = PlayerColumn;

        // Store the player's latest move's coordinates

        judgement.addToWinList(subColumn);
        judgement.addToWinList(subColumn);

        while ( subRow >= 0 && subColumn >= 0 && !victoryFlag ){

            subRow--;
            subColumn--;

            if ( subRow >= 0 && subColumn >= 0 ){

                if (gameBoard.getPlayerMarker(subRow, subColumn).equals(PlayerMarker)){
                    judgement.addToWinList(subRow);
                    judgement.addToWinList(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                    }
                }
                else{
                    break;
                }
            }
        }

        // South East

        subRow = PlayerRow;
        subColumn = PlayerColumn;

        while ( subRow < 6 && subColumn < 7 && !victoryFlag ){

            subRow++;
            subColumn++;

            if ( subRow < 6 && subColumn < 7 ){

                if (gameBoard.getPlayerMarker(subRow, subColumn).equals(PlayerMarker)){
                    judgement.addToWinList(subRow);
                    judgement.addToWinList(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            judgement.flushWinList(); // reset the list, if no one won
        }
        else{
            judgement.setWinDirection("Left Diagonal");
        }

        // Checking the other diagonal
        // North East
        judgement.initialiseWinList(6);
        victoryCounter = 0;
        subRow = PlayerRow;
        subColumn = PlayerColumn;
        judgement.addToWinList(subRow);
        judgement.addToWinList(subColumn);

        while ( subRow >= 0 && subColumn < 7 && !victoryFlag ){

            subRow--;
            subColumn++;

            if ( subRow >= 0 && subColumn < 7 ){

                if (gameBoard.getPlayerMarker(subRow, subColumn).equals(PlayerMarker)){
                    judgement.addToWinList(subRow);
                    judgement.addToWinList(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        // South East

        subRow = PlayerRow;
        subColumn = PlayerColumn;

        while ( subRow < 6 && subColumn >= 0 && !victoryFlag ){

            subRow++;
            subColumn--;

            if ( subRow <= 5 && subColumn >= 0 ){

                if (gameBoard.getPlayerMarker(subRow, subColumn).equals(PlayerMarker)){
                    judgement.addToWinList(subRow);
                    judgement.addToWinList(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            judgement.flushWinList();
        }
        else{
            judgement.setWinDirection("Right Diagonal");
        }

        return victoryFlag;
    }




}
