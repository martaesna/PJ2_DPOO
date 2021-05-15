package businessLayer.entities.game;

import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private Timer timer = new Timer();
    private int seconds = 0;

    class Counter extends TimerTask {
        public void run() {
            seconds++;
        }
    }

    public void initCounter() {
        this.seconds = 0;
        timer = new Timer();
        timer.schedule(new Counter(), 0, 1000);
    }

    public void stopCounter() {
        timer.cancel();
    }

    public int getSeconds() {
        return this.seconds;
    }
}
