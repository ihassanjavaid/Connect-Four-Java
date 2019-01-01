package Back_End;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSupport {

    private static JLabel systemTime;
    private static JLabel gameTime;
    private static int timeSec;
    private static int timeMin;
    private static Timer timer;

    static {

        // Initialize time to zero
        timeSec = 0;
        timeMin = 0;

        // Initialize Labels
        systemTime = new JLabel("" +new SimpleDateFormat("hh:mm").format(new Date()));
        gameTime = new JLabel("00:00");

        // Configure the labels
        systemTime.setFont(new Font("Freestyle Script", Font.BOLD, 28));
        systemTime.setForeground(Color.YELLOW);
        gameTime.setFont(new Font("Freestyle Script", Font.BOLD, 28));
        gameTime.setForeground(Color.YELLOW);


        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                systemTime.setText(new SimpleDateFormat("hh:mm").format(new Date()));
                gameTime.setText(showTimeElapsed());

            }
        });
        timer.start();
    }
    
    private static void getTimeNow() {

        timeSec++;

        if ( timeSec == 60 ){
            timeMin++;
            timeSec = 0;
        }

    }

    private static String showTimeElapsed(){

        String timeSecString;
        String timeMinString;

        getTimeNow();

        if ( timeMin >= 59 )
            return "1 hour +";

        else {

            if (timeSec <= 9)
                timeSecString = "0" + timeSec;
            else
                timeSecString = "" + timeSec;

            if (timeMin <= 9)
                timeMinString = "0" + timeMin;
            else
                timeMinString = "" + timeMin;
        }

        return timeMinString +":" +timeSecString;
    }

    public static Timer getTimer() {
        return timer;
    }

    public static JLabel getSystemTime() {
        return systemTime;
    }

    public static JLabel getGameTime() {
        return gameTime;
    }
}
