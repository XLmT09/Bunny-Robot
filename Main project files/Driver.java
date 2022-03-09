import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Driver class by Adam Tay", 0, 1);
	}
	
	public static void SplashScreen() { // Splash screen method
		Sound.twoBeeps();
		Button.LEDPattern(7);
		LCD.drawString("Bash - version 1", 0, 0);
		LCD.drawString("alpha (Feb 2022)", 0, 1);
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
		Behavior RabbitSoundsClap = new RabbitSoundsClap();
		Behavior RabbitSoundsDark = new RabbitSoundsDark();
		Behavior RabbitSoundsLight = new RabbitSoundsLight();
		Arbitrator ab = new Arbitrator(new Behavior[] {RabbitSoundsClap, RabbitSoundsDark, RabbitSoundsLight, EmergencyStop, BatteryLevel}); // Create arbitrator
	}
}
