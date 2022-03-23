import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

class SoundResponse implements Behavior {
	
	BaseRegulatedMotor mLeft, mRight;
	private float[] soundSample = new float[1];
	private float noise;
	NXTSoundSensor us;
	SampleProvider sp;
	
	public SoundResponse(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, NXTSoundSensor us, float noise) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.noise = noise + 0.8f; //this refers to a sound 0.8 above the average found
		this.us = us;
		this.sp = us.getDBAMode(); //Sample provider set to DBA mode
	}
	
	@Override
	public boolean takeControl() {
		sp.fetchSample(soundSample, 0);
		
		return soundSample[0] > noise + 0.8; //return if noise found is 0.8 more than average sound observed
	}

	@Override
	public void action() {
		//ensuring the bot stops
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		
		Button.LEDPattern(5); //Red lights to express surprise
		LCD.drawString("AH! What was that?!", 1, 2);
		Sound.buzz();
		Delay.msDelay(3000); //Delay for message to persist on LCD
		LCD.clear();
		Sound.beep();
	}

	@Override
	public void suppress() {
		LCD.clear();
		Button.LEDPattern(0);
	}
}
