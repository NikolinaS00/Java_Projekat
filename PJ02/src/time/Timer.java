package time;

import java.util.logging.Logger;
import gui.Window;
import simulation.Game;
import simulation.Main;

public class Timer extends Thread {

	public static long startTime;
	public long endTime;

	public Timer() {
	}
	
	public void run() {
		Logger.getLogger(Timer.class.getName()).addHandler(Main.handler);
		while (Game.gameStarted) {
			if (Main.signForWait == false) {
				this.endTime = System.currentTimeMillis();
				Long time = (this.endTime - startTime) / 1000;
				if (time <= 60) {
					Window.lblvrijemeTrajanjaSet.setText(time.toString() + "s");
					Window.lblvrijemeTrajanjaSet.repaint();
				} else {
					Window.lblvrijemeTrajanjaSet
							.setText(((Long) (time / 60)).toString() + "m" + ((Long) (time % 60)).toString() + "s");
					Window.lblvrijemeTrajanjaSet.repaint();
				}
				try {
					sleep(1000);
				} catch (InterruptedException e) {
					Logger.getLogger(Timer.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
			} else {
				try {
					synchronized (this) {
						wait();
					}
				} catch (InterruptedException e) { 
					Logger.getLogger(Timer.class.getName()).log(java.util.logging.Level.SEVERE, e.fillInStackTrace().toString());
				}
			}
		}
	}
}
