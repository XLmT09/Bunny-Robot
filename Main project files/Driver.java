import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public void printVersion() {
		LCD.drawString("V6 - Robotics Group 2", 0, 0);
		LCD.drawString("Driver class by Adam Tay", 0, 1);
		LCD.drawString("Calibration method by Bharat Karki", 0, 2);
	}
	
	public static void SplashScreen() { // Splash screen method
		Sound.twoBeeps();
		Button.LEDPattern(7);
		LCD.drawString("Jack-Rabbit - ver.", 0, 0);
		LCD.drawString("1 beta (Mar 2022)", 0, 1);
		LCD.drawString("", 0, 2);
		LCD.drawString("By Robotics Group", 0, 3);
		LCD.drawString("2 (Adam Tay,", 0, 4);
		LCD.drawString("Sam Brown,", 0, 5);
		LCD.drawString("Bharat Karki and", 0, 6);
		LCD.drawString("Hiji A.)", 0, 7);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
	public static float calibrateMax(SampleProvider soundSampleProvider) {
		float[] maxSoundSample = new float[1];
		float maxSoundLevel = 0.f;
		
		while (Button.ENTER.isUp()) {
			soundSampleProvider.fetchSample(maxSoundSample, 0);
			if (maxSoundSample[0] > maxSoundLevel) {
				maxSoundLevel = maxSoundSample[0];
			}
		}
		return maxSoundLevel;
	}

	public static void main(String[] args) {
		SplashScreen(); // Calls splash screen method
		Behavior BatteryLevel = new BatteryLevel(); // Declare behaviours
		Behavior CheckColor = new CheckColor(null, null, null); // TODO: Sort out arguments for CheckColor
		Behavior EmergencyStop = new EmergencyStop();
		Behavior RabbitSounds = new RabbitSounds();
		Behavior RabbitSoundsTableTrundle = new RabbitSoundsTableTrundle();
		Behavior Walkabout = new Walkabout();
		Arbitrator ab = new Arbitrator(new Behavior[] {Walkabout, RabbitSounds, RabbitSoundsTableTrundle, CheckColor, EmergencyStop, BatteryLevel}); // Create arbitrator
		ab.go();
	}
}
