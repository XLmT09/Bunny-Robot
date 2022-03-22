import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EmergencyStop implements Behavior {	
	public boolean takeControl() {
		return Button.ENTER.isDown();
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("Shutting Down", 0, 1);
		Delay.msDelay(1000);
		System.exit(0);
	}
	
	public void suppress() {}
}
