import java.util.Random;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class TableTrundle implements Behavior {
	private final int NINETY_DEGREES = 225;
	private final int BACK_UP = -200;
	private final int HALF_SECOND = 500;
	private final int ONE_SECOND = 1000;
	
	private BaseRegulatedMotor mL, mR;
	private EV3UltrasonicSensor us;
	SampleProvider sp;
	private float[] sample = new float[1];
	private String sampleString;
	private Random rgen = new Random();
	private int num;
	
	public TableTrundle (BaseRegulatedMotor mL, BaseRegulatedMotor mR,  EV3UltrasonicSensor us) {
		this.mL = mL;
		this.mR = mR;
		
		this.us = us;
		this.sp = us.getDistanceMode();
	}
	
	/**
	 * @return true if the range specified isn't true, sample string doesn't return infinity AND mHead isn't in the process of moving
	 */
	@Override
	public boolean takeControl() {
		us.fetchSample(sample, 0);
		sampleString = Float.toString(sample[0]);
		return ((sample[0] > 0.5) || (sampleString.equals("Infinity")));
		// return ( !(sample[0] < 0.35f && sample[0] > 0.15f) || !(sampleString.equals("Infinity")) );
	}
	
	@Override
	public void action() {
		num = (2 * rgen.nextInt(2) - 1) * NINETY_DEGREES;
		
		mL.startSynchronization();
		mL.rotate(BACK_UP);
		mR.rotate(BACK_UP);
		mL.endSynchronization();
		
		Delay.msDelay(HALF_SECOND);
		
		mL.startSynchronization();
		mL.rotate(num);
		mR.rotate(-1 * num);
		mL.endSynchronization();
		
		Delay.msDelay(ONE_SECOND);
	}

	@Override
	public void suppress() {}
}