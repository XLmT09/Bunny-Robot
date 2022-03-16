import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class BatteryLevel implements Behavior {
	private float currentVoltage;
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Battery level behavior by Adam Tay", 0, 1);	
	}
	
	public boolean takeControl() {
		currentVoltage = Battery.getVoltage(); // gets current voltage of battery
		return currentVoltage <= 6.8;
	}
	
	public void action() {
		Sound.beep(); // plays beeping sound
		LCD.drawString("Battery low!", 0, 0);
	}
	
	public void suppress() {
		LCD.clear();
	}
}
