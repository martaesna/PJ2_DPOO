package businessLayer.entities.game;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Time {
    private Timer timer = new Timer();
    private int seconds = 0;

    private class Counter extends TimerTask {
        public synchronized void run() {
            seconds++;
        }
    }

    /**
     * Mètode que inicialitza el comptador
     */
    public void initCounter() {
        this.seconds = 0;
        timer = new Timer();
        timer.schedule(new Counter(), 0, 1000);
    }

    /**
     * Mètode que posa el comptador a 0
     */
    public void resetCounter() {
        seconds = 0;
    }

    /**
     * Mètode que retorna el valor del temps actual
     * @return valor del temps actual
     */
    public int getSeconds() {
        return this.seconds;
    }

}
