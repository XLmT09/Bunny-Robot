import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.subsumption.Behavior;

public class RabbitSoundsTableEdge implements Behavior {
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Rabbit sounds (table edge) sequence by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		return true; // placeholder boolean
	}
	
	public void action() {
		Sound.playSample(new File("rabbit obstacle table sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("Edge of table", 0, 0);
		LCD.drawString("detected!", 0, 1);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
	public void suppress() {
	}
}
