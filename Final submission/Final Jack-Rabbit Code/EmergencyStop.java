import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EmergencyStop implements Behavior {	
	private final int ONE_SECOND = 1000;
	
	public boolean takeControl() {
		return Button.ENTER.isDown(); //returns if enter button is pressed
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("Shutting Down", 1, 2);
		Delay.msDelay(ONE_SECOND); //Delay for message to persist on LCD
		System.exit(0); //End program
	}
	
	public void suppress() {}
}
