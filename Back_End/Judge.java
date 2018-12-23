import java.util.ArrayList;
import java.util.List;

public class Judge extends GameBoard{
   protected List winList;
   public String winDirection;
   public boolean victoryFlag = false;
   public GameBoard Gameboard;


    public Judge(GameBoard GB){
        winList = new ArrayList();
        winDirection = " ";
        this.Gameboard = GB;
    }



    public boolean HorizontalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){

        int column = PlayerColumn;

        winList.add(column); // Store the player's latest move's column number
        int victoryCounter = 0;

        // Checking West from the point of player's latest move

        while ( column >= 0 && !victoryFlag ){

            column--;

            if ( column >=0 ){

                if ( Gameboard.getString(PlayerRow,column).equals(PlayerMarker)){
                    winList.add(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(PlayerRow);   // Store the row in which the player has won
                        victoryFlag = true;;

                    }
                }

                else{
                    break;
                }
            }
        }

        // Checking East from the point of player's latest move

        column = PlayerColumn;

        while ( column < 7 && !victoryFlag ){

            column++;

            if ( column < 7 ){

                if ( Gameboard.getString(PlayerRow,column).equals(PlayerMarker)){
                    winList.add(column); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(PlayerRow);   // Store the row in which the player has won
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


    public boolean VerticalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){
        boolean verticalFlag = false;
        int column = PlayerColumn;
        int row = PlayerRow;

        winList.add(row);  //Store the player's latest move's row number
        int victoryCounter = 0;

        // Checking South from the point of player's latest move

        while ( row < 6 && !victoryFlag ){

            row++;

            if ( row < 6 ){

                if ( Gameboard.getString(row,column).equals(PlayerMarker)){
                    winList.add(row); // If there is a same piece on the left of the current piece, store its coordinates
                    victoryCounter++;

                    if ( victoryCounter == 3 ){
                        winList.add(PlayerColumn);   // If there is a same piece below the current piece, store its coordinates
                        victoryFlag = true;
                        verticalFlag = true;
                        System.out.println("Vert");
                        break;
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

        return verticalFlag;
    }

    public boolean DiagonalJudge(String PlayerMarker, int PlayerRow, int PlayerColumn){
        victoryFlag = false;
        int subRow = PlayerRow;
        int subColumn = PlayerColumn;
        int victoryCounter = 0;

        // Checking first Diagonal
        // North West

        // Store the player's latest move's coordinates

        winList.add(subRow);
        winList.add(subColumn);

        while ( subRow >= 0 && subColumn >= 0 && !victoryFlag ){

            subRow--;
            subColumn--;

            if ( subRow >= 0 && subColumn >= 0 ){

                if ( Gameboard.getString(subRow,subColumn).equals(PlayerMarker)){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                        System.out.println("NW");

                    }
                }

                else{
                    break;
                }
            }
        }

        // South west

        subRow = PlayerRow;
        subColumn = PlayerColumn;
        victoryCounter = 0;

        while ( subRow < 6 && subColumn < 7 && subColumn >3&& !victoryFlag ){

            subRow++;
            subColumn--;

            if ( subRow < 6 && subColumn < 7 ){

                if ( Gameboard.getString(subRow,subColumn).equals(PlayerMarker)){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        victoryFlag = true;
                        System.out.println(("SW"));
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
        subRow = PlayerRow;
        subColumn = PlayerColumn;
        winList.add(subRow);
        winList.add(subColumn);

        while ( subRow >= 0 && subColumn < 7 && !victoryFlag ){

            subRow--;
            subColumn++;

            if ( subRow >= 0 && subColumn < 7 ){

                if ( Gameboard.getString(subRow,subColumn).equals(PlayerMarker)){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        winDirection = "Right Diagonal";
                        victoryFlag = true;
                        System.out.println("NE");
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
        victoryCounter = 0;

        while ( subRow < 6 && subColumn >= 0 && !victoryFlag ){

            subRow++;
            subColumn++;

            if ( subRow <= 5 && subColumn >= 0 ){

                if (Gameboard.getString(subRow,subColumn).equals(PlayerMarker)){
                    winList.add(subRow);
                    winList.add(subColumn);
                    victoryCounter++;

                    if ( victoryCounter == 3){
                        winDirection = "Right Diagonal";
                        victoryFlag = true;
                        System.out.println("SE");
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

}




