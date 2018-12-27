package Front_End;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeSupport implements ActionListener {

    JLabel systemTime;
    JLabel gameTime;

    private int timeSec;
    private int timeMin;

    private Timer timer;

    public TimeSupport() {

        timeSec = 0;
        timeMin = 0;

        systemTime = new JLabel("" +new SimpleDateFormat("hh:mm").format(new Date()));
        gameTime = new JLabel("00:00");

        systemTime.setFont(new Font("Freestyle Script", Font.BOLD, 28));
        systemTime.setForeground(Color.YELLOW);

        gameTime.setFont(new Font("Freestyle Script", Font.BOLD, 28));
        gameTime.setForeground(Color.YELLOW);

        timer = new Timer(1000, this);
        timer.start();

    }

    public void actionPerformed(ActionEvent ae) {

        systemTime.setText(new SimpleDateFormat("hh:mm").format(new Date()));
        gameTime.setText(showTimeElapsed());

    }

    private void getTimeNow() {

        timeSec++;

        if ( timeSec == 60 ){
            timeMin++;
            timeSec = 0;
        }

    }

    private String showTimeElapsed(){

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

}