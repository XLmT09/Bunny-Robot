import java.util.Random;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CheckFloor implements Behavior {
	private final int NINETY_DEGREES = 360;
	private final int TWO_SECOND = 2000;
	private final int ONE_SECOND = 1000;
	
	private BaseRegulatedMotor mL, mR;
	private EV3UltrasonicSensor us;
	SampleProvider sp;
	private float[] sample = new float[1];
	private String sampleString;
	private Random rgen = new Random();
	private int num;
	
	public CheckFloor (BaseRegulatedMotor mL, BaseRegulatedMotor mR,  EV3UltrasonicSensor us) {
		this.mL = mL;
		this.mR = mR;
		
		this.us = us;
		this.sp = us.getDistanceMode(); //Sample provider set to distance mode
	}
	
	/**
	 * @return true if the range specified isn't true, sample string doesn't return infinity AND mHead isn't in the process of moving
	 */
	@Override
	public boolean takeControl() {
		us.fetchSample(sample, 0);
		sampleString = Float.toString(sample[0]);

		//returns if something is very far away OR if it detects nothing
		return ((sample[0] > 0.3) || (sampleString.equals("Infinity")));
	}
	
	@Override
	public void action() {
		num = (2 * rgen.nextInt(2) - 1) * NINETY_DEGREES; //allows the bot to turn ninety degrees in a random direction
		
		//lower speed to back up
		mL.setSpeed(100);
		mR.setSpeed(100);

		//back up
		mL.startSynchronization();
		mL.backward();
		mR.backward();
		mL.endSynchronization();
		
		Delay.msDelay(TWO_SECOND); //this will ensure it only backs up for 2 seconds
		
		//increase speed to turn around
		mL.setSpeed(200);
		mR.setSpeed(200);

		//turn 90 degrees
		mL.startSynchronization();
		mL.rotate(num);
		mR.rotate(-1 * num);
		mL.endSynchronization();
		
		//if the behaviour runs again, this will prevent it from running too consistently
		Delay.msDelay(ONE_SECOND);
	}

	@Override
	public void suppress() {
		mL.startSynchronization();
		mL.stop();
		mR.stop();
		mL.endSynchronization();
	}
}