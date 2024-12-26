import java.util.Random;

import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class TableTrundle implements Behavior {
	private final int NINETY_DEGREES = 360;
	private final int TWO_SECOND = 2000;
	private final int ONE_SECOND = 1000;
	private final int BACK_UP_SPEED = 100;
	private final int TURNING_SPEED = 200;
	
	private BaseRegulatedMotor mL, mR;
	private EV3UltrasonicSensor us;
	private EV3TouchSensor ts;
	SampleProvider ussp, tssp;
	private float[] sample = new float[2];
	private String sampleString;
	private Random rgen = new Random();
	private int num;
	
	public TableTrundle (BaseRegulatedMotor mL, BaseRegulatedMotor mR,  EV3UltrasonicSensor us, EV3TouchSensor ts) {
		this.mL = mL;
		this.mR = mR;
		
		this.us = us;
		this.ussp = us.getDistanceMode();
		
		this.ts = ts;
		this.tssp = ts.getTouchMode();
	}
	
	/**
	 * @return true if the range specified isn't true, sample string doesn't return infinity AND mHead isn't in the process of moving
	 */
	@Override
	public boolean takeControl() {
		us.fetchSample(sample, 0);
		sampleString = Float.toString(sample[0]);
		
		ts.fetchSample(sample, 1);
		
		//returns if something is very far away, if it detects nothing, if the touch sensor is activated OR if the driving motors stall
		return ((sample[0] > 0.3f) || (sampleString.equals("Infinity")) || (sample[1] > 0f) || (mL.isStalled() && mR.isStalled()));
	}
	
	@Override
	public void action() {
		num = (2 * rgen.nextInt(2) - 1) * NINETY_DEGREES; //allows the bot to turn ninety degrees in a random direction
		
		Sound.twoBeeps();
		LCD.drawString("Woah - that's not good!", 1, 2);
		LCD.drawString("Let me just... back up...", 1, 3);
		
		//lower speed to back up
		mL.setSpeed(BACK_UP_SPEED);
		mR.setSpeed(BACK_UP_SPEED);

		//back up
		mL.startSynchronization();
		mL.backward();
		mR.backward();
		mL.endSynchronization();
		
		Delay.msDelay(TWO_SECOND); //this will ensure it only backs up for 2 seconds
		
		//increase speed to turn around
		mL.setSpeed(TURNING_SPEED);
		mR.setSpeed(TURNING_SPEED);

		//turn 90 degrees
		mL.startSynchronization();
		mL.rotate(num);
		mR.rotate(-1 * num);
		mL.endSynchronization();
		
		LCD.clear();
		
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
