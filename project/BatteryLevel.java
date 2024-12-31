import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class BatteryLevel implements Behavior {
	private final int FIVE_SECONDS = 5000;
	
	public boolean takeControl() {
		return Battery.getVoltage() <= 6.4;
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("Battery low!", 1, 2);
		LCD.drawString("Shutting Down.", 1, 3);

		//delay for messages to persist on LCD
		Delay.msDelay(FIVE_SECONDS);
		//end program
		System.exit(0);
	}
	
	public void suppress() {
		LCD.clear();
	}
}
