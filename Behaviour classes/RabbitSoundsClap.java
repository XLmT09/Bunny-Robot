import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class RabbitSoundsClap implements Behavior {
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Rabbit sounds (clap) sequence by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		return true; // placeholder boolean
	}
	
	public void action() {
		Sound.playSample(new File("scared rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("Too loud!", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
	public void suppress() {
	}
}
