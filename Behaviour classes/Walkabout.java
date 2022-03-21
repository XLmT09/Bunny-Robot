import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class Walkabout implements Behavior {
	private boolean foo = true; // metasyntactic variable
	private BaseRegulatedMotor mLeft = new NXTRegulatedMotor(MotorPort.A);
	private BaseRegulatedMotor mRight = new NXTRegulatedMotor(MotorPort.B);
	
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Walkabout behaviour by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		return true; // return true - always take control
	}
	
	public void action() {
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight}); // synchronise left and right motors
		mLeft.startSynchronization(); // start synchronisation
		mLeft.setSpeed(180); // set speed of motors to 180
		mRight.setSpeed(180);
		mLeft.forward(); // motors go forward
		mRight.forward();
		if (foo == true) { // if carrot detected TODO: implement carrot colour detection system
			mLeft.setSpeed(360); // double speed
			mRight.setSpeed(360);
			mLeft.forward(); // motors go forward
			mRight.forward();
			Sound.playSample(new File("happy rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
			Button.LEDPattern(4); // green flashing LED light on EV3 brick
			LCD.drawString("Carrot detected", 0, 0);
			LCD.drawString("nearby!", 0, 1);
			Button.ENTER.waitForPressAndRelease();
			LCD.clear();
		}
	}
	
	public void suppress() {
		mLeft.flt(); // set motors to float then stop
		mRight.flt();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization(); // end synchronisation
	}
}
