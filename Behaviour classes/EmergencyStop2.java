import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EmergencyStop2 implements Behavior {
	private final int FIVE_SECONDS = 5000; // 5 second delay - eliminate magic numbers
	
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Emergency STOP behaviour by Adam Tay and Hiji", 0, 1);
	}
	public boolean takeControl() {
		return Button.ENTER.isDown() || Button.ESCAPE.isDown();
	}
	
	public void action() {
		// might need to stop motors
		LCD.clear(); // clear LCD screen
		LCD.drawString("Ending program", 0, 0); // notify user of program ending
		Delay.msDelay(FIVE_SECONDS);
		System.exit(0); // exit program in state 0
	}
	
	public void suppress() {} // suppress method is empty
}
