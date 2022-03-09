import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class RabbitSoundsLight implements Behavior {
	private MovePilot pilot;
	private SampleProvider colorSample;
	private float[] lightLevel = new float[1];
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Rabbit sounds (light) sequence by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		colorSample.fetchSample(lightLevel, 0);
		return lightLevel[0] >= 0.75f;
	}
	
	public void action() {
		pilot.stop();
		Sound.playSample(new File("scared rabbit sound.wav"), Sound.VOL_MAX);
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("Too bright!", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
	public void suppress() {
		pilot.setLinearSpeed(720);
		pilot.forward();
	}
}
