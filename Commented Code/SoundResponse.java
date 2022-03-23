import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

class SoundResponse implements Behavior {
	
	private float[] soundSample = new float[1];
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private float noise;
	NXTSoundSensor us;
	SampleProvider sp;
	
	public SoundResponse(BaseRegulatedMotor mLeft, BaseRegulatedMotor mRight, NXTSoundSensor us, float noise) {
		this.mLeft = mLeft;
		this.mRight = mRight;
		this.noise = noise;
		this.us = us;
		this.sp = us.getDBAMode(); //Sample provider set to DBA mode
	}
	
	@Override
	public boolean takeControl() {
		sp.fetchSample(soundSample, 0);
		
		return soundSample[0] > (noise + 0.8); //return if noise found is 0.8 more than average sound observed
	}

	@Override
	public void action() {
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		Button.LEDPattern(5); //Red lights to express surprise
		LCD.drawString("I heard something", 0, 3);
		Delay.msDelay(3000); //Delay for message to persist on LCD
	}

	@Override
	public void suppress() {
		LCD.clear();
		Button.LEDPattern(0);
	}
}