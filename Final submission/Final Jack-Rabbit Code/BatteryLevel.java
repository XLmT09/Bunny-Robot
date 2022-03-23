import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class BatteryLevel implements Behavior {
	
	public boolean takeControl() {
		return Battery.getVoltage() <= 6.4;
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("Battery low!", 0, 0);
		LCD.drawString("Shutting Down.", 0, 1);

		//delay for messages to persist on LCD
		Delay.msDelay(5000);
		//end program
		System.exit(0);
	}
	
	public void suppress() {
		LCD.clear();
	}
}