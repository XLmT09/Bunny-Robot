import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Driver class by Adam Tay", 0, 1);
	}
	
	public static void SplashScreen() { // Splash screen method
		Sound.twoBeeps();
		Button.LEDPattern(7);
		LCD.drawString("Jack-Rabbit - ver.", 0, 0);
		LCD.drawString("1 alpha (Mar 2022)", 0, 1);
		LCD.drawString("", 0, 2);
		LCD.drawString("By Robotics Group", 0, 3);
		LCD.drawString("2 (Adam Tay,", 0, 4);
		LCD.drawString("Sam Brown,", 0, 5);
		LCD.drawString("Bharat Karki and", 0, 6);
		LCD.drawString("Hiji A.)", 0, 7);
		Button.ENTER.waitForPressAndRelease();
	}

	public static void main(String[] args) {
		SplashScreen(); // Calls splash screen method
		Behavior BatteryLevel = new BatteryLevel(); // Declare behaviours
		Behavior EmergencyStop = new EmergencyStop();
		Behavior RabbitSounds = new RabbitSounds();
		Behavior Walkabout = new Walkabout(null); // TODO: Implement MovePilot object into Walkabout behaviour as argument
		Arbitrator ab = new Arbitrator(new Behavior[] {Walkabout, RabbitSounds, EmergencyStop, BatteryLevel}); // Create arbitrator
	}
}
