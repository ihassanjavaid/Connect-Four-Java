package Back_End;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Filing {

    private static File gameFile = new File("gamefile.txt");
    private static PrintWriter printWriter;
    private static Leaderboard[] leaderboards;
    private static int NumberOfRecords = 0;

    static {

        if ( !gameFile.exists() ){

            try {
                /*
                Make a new file if a file already not exists
                Making an object of PrintWriter flushes the file
                */
                printWriter = new PrintWriter(gameFile);

                /*
                 Make a leaderboard-type array from the existing record (if found)
                 So that when a new printwriter is made, old entries are not dissipated
                 */
                leaderboards = fetchDatabase();
            }
            catch (Exception e) {
                // catch exception
            }
        }

    }

    public static void saveStats(Leaderboard currentLeaderboard){

        try {
            printWriter = new PrintWriter(gameFile);
        } catch (FileNotFoundException fnfe) {
            // catch exception
        }

        if ( NumberOfRecords > 0 ) {

            for (Leaderboard oldStats : leaderboards) {
                printWriter.println(oldStats.playerOneName);
                printWriter.println(oldStats.playerTwoName);
                printWriter.println(oldStats.winner);
                printWriter.println(oldStats.gameTime);
            }
        }

        printWriter.println("Player 1: "+currentLeaderboard.playerOneName);
        printWriter.println("Player 2: "+currentLeaderboard.playerTwoName);
        printWriter.println("Winner: "+currentLeaderboard.winner);
        printWriter.println(""+currentLeaderboard.gameTime);

        printWriter.close();

    }

    private static Leaderboard [] fetchDatabase() throws Exception {

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("gamefile.txt"));
        } catch (FileNotFoundException fnfe) {
            // catch exception
        }

        Leaderboard [] leaderboards = new Leaderboard[0];
        try {
            leaderboards = new Leaderboard[NumberOfRecords()];
        } catch (Exception e) {
            // catch exception
        }
        int i;

        i = 0;
        while (fileReader.hasNextLine()){
            leaderboards[i] = new Leaderboard("", "");
            leaderboards[i].playerOneName = fileReader.nextLine();
            leaderboards[i].playerTwoName = fileReader.nextLine();
            leaderboards[i].winner = fileReader.nextLine();
            leaderboards[i].gameTime = fileReader.nextLine();
            i++;
        }
        return leaderboards;
    }

    private static int NumberOfRecords() {

        Scanner fileReader = null;
        try {
            fileReader = new Scanner(new File("gamefile.txt"));
        } catch (FileNotFoundException fnfe) {
            // catch exception
        }
        int lineCount = 0;

        try {
            while (fileReader.hasNext()) {
                fileReader.nextLine();
                lineCount++;
            }
        }
        catch (NullPointerException npe){
            //Catch exception
        }

        NumberOfRecords = (lineCount/4);
        return (lineCount / 4);
    }

    public static String getLastRecord(){

        if ( NumberOfRecords > 0) {
            return leaderboards[leaderboards.length - 1].toStringForDialgoueBox();
        }

        else{
            String lastRecord = "";

            Scanner fileReader = null;
            try {
                fileReader = new Scanner(new File("gamefile.txt"));
            } catch (FileNotFoundException fnfe) {
                // catch exception
            }

            boolean hasSomeLines = false;

            try {
                for (int j = 0; j < 4; j++) {
                    if (fileReader.hasNextLine()) {

                        hasSomeLines = true;

                        lastRecord += fileReader.nextLine();
                        lastRecord += "\n";
                    }
                    else if ( !hasSomeLines ){
                        return "No record found!";
                    }
                }
            }
            catch (NullPointerException npe){
                // catch exception
            }
            return lastRecord;
        }
    }

    public static void flushRecords(){

        try {
            printWriter = new PrintWriter(gameFile);
        }
        catch (FileNotFoundException fnfe) {
            // catch exception
        }
    }

}
