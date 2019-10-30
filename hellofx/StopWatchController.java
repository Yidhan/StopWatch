package hellofx;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import java.util.Timer;
import java.util.TimerTask;

public class StopWatchController {
    long starTime = 0;
    public float OldTimePassed = 0;
    boolean runClock = false;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button resetButton;

    @FXML
    private TextField timer;

    @FXML
    void ResetButtonPressed(ActionEvent event) {
        runClock = false;
        OldTimePassed = 0;
        timer.setText("0s");
    }

    @FXML
    void StartButtonPressed(ActionEvent event) {

        Thread thread = new Thread(){
            public void run(){
                runClock = true;
                starTime = System.currentTimeMillis();
                float timePassed = 0;
                while(true == runClock)
                {
                    long currentTime = System.currentTimeMillis();
                    timePassed = (currentTime - starTime) + OldTimePassed;
                    int secPassed = (int) Math.floor(timePassed /1000);
                    String timeStr = String.valueOf(secPassed) + "s ";
                    timer.setText(timeStr);
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e)
                    {

                    }
                }
                OldTimePassed = timePassed;
            }
        };
        String a = timer.getText();
        if(timer.getText().equals("0s"))
        {
            OldTimePassed = 0;
        }
        thread.start();
    }

    @FXML
    void StopButtonPressed(ActionEvent event) {
        runClock = false;
    }

}
