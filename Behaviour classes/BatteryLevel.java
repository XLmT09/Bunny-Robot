import lejos.hardware.Battery;
import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class BatteryLevel implements Behavior {
	private final int FIVE_SECONDS = 5000; // eliminate magic numbers
	
	public void printVersion() {
		LCD.drawString("V3.1 - Robotics Group 2", 0, 0);
		LCD.drawString("Battery level behaviour by Adam Tay and Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		return Battery.getVoltage() <= 6.0; // take control if battery voltage is less than or equal to 6.0
	}
	
	public void action() {
		LCD.clear();
		Sound.beep(); // plays beeping sound
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("Battery low!", 0, 0);
		LCD.drawString("Shutting down...", 0, 1);
		Delay.msDelay(FIVE_SECONDS);
		System.exit(0); // exit program with state 0
	}
	
	public void suppress() {
		LCD.clear();
	}
}