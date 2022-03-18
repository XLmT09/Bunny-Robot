import lejos.hardware.Battery;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class BatteryLevel2 implements Behavior {
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Battery level behaviour by Adam Tay and Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		return Battery.getVoltage() <= 6.0;
	}
	
	public void action() {
		LCD.clear();
		LCD.drawString("Battery low!", 0, 0);
		LCD.drawString("Shutting Down.", 0, 1);
		Delay.msDelay(5000);
		System.exit(0);
	}
	
	public void suppress() {
		LCD.clear();
	}
}
