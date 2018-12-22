import java.util.ArrayList;
import java.util.List;

public class Game {

    public static List winList = new ArrayList();
    public static String winDirection;

    public static String[][] gameBoard = new String[6][7];

    public static void main(String[] args) {

    }

    public static void initialiseGameBoard(){ // Function to initialize the GameBoard

        for ( int row = 0 ; row < 6 ; row++ ){
            for ( int column = 0 ; column < 7 ; column++ ){
                gameBoard[row][column] = "-";
            }
        }
    }

    public static void setPlayerColumn(int buttonNumber){ //Function to store the player selected column

        int playerColumn; // global in python
        boolean moveFlag; // global in python

        if (gameBoard[0][buttonNumber].equals("-")) { // Check if .equals() needs to be implemented
            playerColumn = buttonNumber;
            moveFlag = true;
            System.out.println("Player selected column: " + (playerColumn + 1));
        }

        else{
            // Show dialgoue box that column is already full
            System.out.println("Column is full");
        }
    }

    public static int nextEmptyPosition(int playerColumn, String playerMarker){

        int row = 5; //Start checking from the bottom of the board

        while ( !gameBoard[row][playerColumn].equals("-") &&  row >= 0 ){
            row--;
        }

        if ( row >= 0 ){
            gameBoard[row][playerColumn] = playerMarker;
        }

        return row;
    }

    public static ClassForPlayerMarkerAndTurnCounter swapPlayers(String playerMarker, int turnCounter){

        if ( playerMarker.equals("o") ){  // try .equals()
            playerMarker = "x";
            System.out.println("Player two");
            System.out.println("Player Marker: " +playerMarker);
        }
        else{
            playerMarker = "o";
            System.out.println("Player one");
            System.out.println("Player Marker: " +playerMarker);
        }

        turnCounter++;

        return new ClassForPlayerMarkerAndTurnCounter(playerMarker, turnCounter);
    }

    public static boolean horizontalJudge(String playerMarker, int playerRow, int playerColumn){

        //List winList = new ArrayList(); // declared a global variable

        boolean victoryFlag = false;
        int column = playerColumn;

        winList.add(column); // Store the player's latest move's column number
        int victoryCounter = 0;

        // Checking West from the point of player's latest move

        while ( column >= 0 && !victoryFlag ){

            column--;

            if ( column >=0 ){

                if ( gameBoard[playerRow][column].equals(playerMarker) ){
                    winList.add(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(playerRow);   // Store the row in which the player has won
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        // Checking East from the point of player's latest move

        column = playerColumn;

        while ( column < 7 && !victoryFlag ){

            column++;

            if ( column < 7 ){

                if ( gameBoard[playerRow][column].equals(playerMarker) ){
                    winList.add(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(playerRow);   // Store the row in which the player has won
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            winList.clear(); // in python: winList = []  #If no one won, reset the win list
        }

        return victoryFlag;
    }

    public static boolean verticalJudge(String playerMarker, int playerRow, int playerColumn){

        // global winList

        boolean victoryFlag = false;
        boolean verticalFlag = false;

        int row = playerRow;

        winList.add(row);  //Store the player's latest move's row number
        int victoryCounter = 0;

        // Checking South from the point of player's latest move

        row = playerRow;

        while ( row < 6 && !victoryFlag ){

            row++;

            if ( row < 6 ){

                if ( gameBoard[row][playerColumn].equals(playerMarker) ){
                    winList.add(row); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(playerColumn);   // If there is a same piece below the current piece, store its coordinates
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            winList.clear();  // in python: winList = []  #If no one won, reset the win list
        }

        return verticalFlag;
    }

    public static boolean diagonalJudge(String playerMarker, int row, int column){

        // global winDirection
        // global winList

        boolean victoryFlag = false;

        int subRow = 0;
        int subColumn = 0;
        int victoryCounter = 0;

        // Checking first Diagonal
        // North West

        subRow = row;
        subColumn = column;

        // Store the player's latest move's coordinates

        winList.add(subColumn);
        winList.add(subColumn);

        while ( subRow >= 0 && subColumn >= 0 && !victoryFlag ){

            subRow--;
            subColumn--;

            if ( subRow >= 0 && subColumn >= 0 ){

                if ( gameBoard[subRow][subColumn].equals(playerMarker) ){
                    winList.add(subRow);
                    winList.add(subColumn);
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

        subRow = row;
        subColumn = column;

        while ( subRow < 6 && subColumn < 7 && !victoryFlag ){

            subRow++;
            subColumn++;

            if ( subRow < 6 && subColumn < 7 ){

                if ( gameBoard[subRow][subColumn].equals(playerMarker) ){
                    winList.add(subRow);
                    winList.add(subColumn);
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
            winList.clear(); // reset the list, if no one won
        }
        else{
            winDirection = "Left Diagonal";
        }

         // Checking the other diagonal
        // North East

        victoryCounter = 0;
        subRow = row;
        subColumn = column;
        winList.add(subRow);
        winList.add(subColumn);

        while ( subRow >= 0 && subColumn < 7 && !victoryFlag ){

            subRow--;
            subColumn++;

            if ( subRow >= 0 && subColumn < 7 ){

                if ( gameBoard[subRow][subColumn].equals(playerMarker) ){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        winDirection = "Right Diagonal";
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        // South East

        subRow = row;
        subColumn = column;

        while ( subRow < 6 && subColumn >= 0 && !victoryFlag ){

            subRow++;
            subColumn--;

            if ( subRow <= 5 && subColumn >= 0 ){

                if ( gameBoard[subRow][subColumn].equals(playerMarker) ){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        winDirection = "Right Diagonal";
                        victoryFlag = true;
                    }
                }

                else{
                    break;
                }
            }
        }

        if ( !victoryFlag ){
            winList.clear();
        }

        return victoryFlag;
    }

    public static void toggleMusic( /*takes two arguments*/ ){

        // functionality to be provided

    }

}
