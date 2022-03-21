import java.util.Random;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class Trundle implements Behavior {
	private final int ONE_AND_A_HALF_SECS = 1500;
	private final int HALF_SECOND = 500;
	
	
	private BaseRegulatedMotor mL, mR, mHead;
	private Random rgen = new Random();
	private int num;
	public Trundle (BaseRegulatedMotor mL, BaseRegulatedMotor mR, BaseRegulatedMotor mHead) {
		this.mL = mL;
		this.mR = mR;
		this.mHead = mHead;
	}
	
	public void action() {
		//this will be used to generate a number between 500 and 2000
		//this will provide the number of milliseconds the EV3 bot should move for
		num = rgen.nextInt(ONE_AND_A_HALF_SECS+1) + HALF_SECOND;
		
		//bot moves forward until the edge of a table is detected
		mL.startSynchronization();
		mL.forward();
		mR.forward();
		mL.endSynchronization();
	}
	
	public void suppress() {
		mL.startSynchronization();
		mL.stop();
		mR.stop();
		mL.endSynchronization();
	}
	
	public boolean takeControl() {
		return true;
	}
}