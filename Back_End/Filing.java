package Back_End;

import java.io.*;
import java.util.Scanner;

public class Filing {
    private ObjectOutputStream opStream;
    private FileOutputStream file;
    private Leaderboard[] leaderboards;

    public Filing() throws Exception {

        leaderboards = fetchLeaderboardDatabase();
        file = new FileOutputStream("gamefile.txt");
        opStream = new ObjectOutputStream(file);

        try {
            for (int i = 0; i < leaderboards.length; i++) {
                opStream.writeObject(leaderboards[i].toString());
                opStream.flush();
            }
        }
        catch (NullPointerException ne){
            ne.printStackTrace();
        }
    }

    public void saveStats(Leaderboard leaderboard_in) throws Exception {

        opStream.writeObject(leaderboard_in.toString());
        opStream.close();
    }

    private Leaderboard[] fetchLeaderboardDatabase() throws  Exception {

        Scanner reader = new Scanner(new File("gamefile.txt"));
        Leaderboard[] savedGames = new Leaderboard[NumberOfRecords()];
        int i;


        i = 0;
        while (reader.hasNextLine()){
            savedGames[i] = new Leaderboard("", "");
            savedGames[i].playerOneName = reader.nextLine();
            savedGames[i].playerTwoName = reader.nextLine();
            savedGames[i].winner = reader.nextLine();
            savedGames[i].gameTime = reader.nextLine();
            i++;
        }
        return savedGames;

    }

    private int NumberOfRecords() throws Exception{

        Scanner FileRead = new Scanner(new File("gamefile.txt"));
        int lineCount = 0;

        while(FileRead.hasNext()){ //while not the end of file
            FileRead.nextLine(); //Go to next line
            lineCount++; //Count the line
        }
            /*
              Each game has 4 attributes, so number of games is obtained
              by dividing the number of lines by 4
            */
        return (lineCount/4);
    }
}
