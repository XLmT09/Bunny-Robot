import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class CheckColor implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	SampleProvider sp;
	float[] samples = new float[4];
	private boolean isDark = false;
	private boolean isGreen = false;
	
	public CheckColor(BaseRegulatedMotor left, BaseRegulatedMotor right, SampleProvider sp) {
		this.mLeft = left;
		this.mRight = right;
		this.sp = sp;
	}
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Carrot detection and light level program by Hiji", 0, 1);
		LCD.drawString("Sound files and Walkabout behaviour implementation by Adam Tay", 0, 2);
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 1);
		// Check if green detected using RGB
		if ((samples[1]>0.003f) && (samples[1]<0.031f) && (samples[2]>0.013f) && (samples[2]<0.050f) && (samples[3]>0.003f) && (samples[3]<0.040f)) {
			isGreen = true;
		}
		// Use RGB to check if too dark
		else if ((samples[1]>0.100f) && (samples[1]<0.160f) && (samples[2]>0.000f) && (samples[2]<0.050f) && (samples[3]>0.000f) && (samples[3]<0.030f)) {
			isDark = true;
		}
		return isGreen || isDark; // if green detected or too dark return true
	}
	
	public void action() {
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight}); // synchronise left and right motors
		mLeft.startSynchronization(); // start synchronisation
		mLeft.setSpeed(180); // set speed of motors to 180
		mRight.setSpeed(180);
		mLeft.forward(); // motors go forward
		mRight.forward();
		if (isGreen) { // if green detected - carrot detected
			mLeft.setSpeed(360); // double speed
			mRight.setSpeed(360);
			mLeft.forward(); // motors go forward
			mRight.forward();
			Sound.playSample(new File("happy rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
			Button.LEDPattern(4); // green flashing LED light on EV3 brick
			LCD.drawString("Carrot detected", 0, 0);
			LCD.drawString("nearby!", 0, 1);
		} 
		else if(isDark) {
			mLeft.stop();
			mRight.stop();
			mLeft.endSynchronization();
			Sound.setVolume(50); // sets volume to half
			Sound.playSample(new File("scared rabbit sound.wav"));
			Button.LEDPattern(5); // red flashing LED light on EV3 brick
		}
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
		isGreen = false;
		isDark = false;
	}
	
	public void suppress() {
		LCD.clear(); // clear LCD screen
		mLeft.flt(); // set motors to float then stop
		mRight.flt();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization(); // end synchronisation
	} 

}
