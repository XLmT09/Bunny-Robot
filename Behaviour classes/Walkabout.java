import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Walkabout implements Behavior {
	private MovePilot pilot;
	private boolean foo = true; // metasyntactic variable
	
	Walkabout (MovePilot pilot) {
		this.pilot = pilot;
	}
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Walkabout behaviour by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		return true; // placeholder boolean
	}
	
	public void action() {
		this.pilot.forward(); // make robot go forwards
		if (foo == true) { // if carrot detected TODO: implement carrot colour detection system
			this.pilot.setLinearSpeed(360); // robot increases speed if carrot detected
			Sound.playSample(new File("happy rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
			Button.LEDPattern(4); // green flashing LED light on EV3 brick
			LCD.drawString("Carrot detected", 0, 0);
			LCD.drawString("nearby!", 0, 1);
			Button.ENTER.waitForPressAndRelease();
			LCD.clear();
		}
	}
	
	public void suppress() {
		this.pilot.stop(); // stops robot
	}
}
