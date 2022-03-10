import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class RabbitSounds implements Behavior {
	private MovePilot pilot;
	private SampleProvider colorSample;
	private float[] lightLevel = new float[1];
	private float[] darkLevel = new float[1];
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Rabbit sounds (combined) sequence by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		return true; // placeholder boolean
	}
	
	public void action() {
		pilot.stop();
		Sound.playSample(new File("scared rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		if (lightLevel[0] >= 0.75f) { // too bright
			LCD.drawString("Too bright!", 0, 0);
		}
		else if (darkLevel[0] < 0.5f) { // too dark
			LCD.drawString("Too dark!", 0, 0);
		}
		else { // too loud - clap filter to be implemented
			LCD.drawString("Too loud!", 0, 0);
		}
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
	public void suppress() {
		pilot.setLinearSpeed(720);
		pilot.forward();
	}
}
