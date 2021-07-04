package businessLayer.entities.game;

import java.util.Timer;
import java.util.TimerTask;

/**
 *
 *
 */
public class Time {
    // Attributes
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

    /**
     * Mètode que posa el valor del temps passat per paràmetre
     * @param seconds valor dels segons que es vol posar
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
