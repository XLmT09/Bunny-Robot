import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CheckColor implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private BaseRegulatedMotor head; //Linear motor for head movements
	private EV3ColorSensor cs;
	SampleProvider sp;
	float[] samples = new float[4];
	private boolean isDark = false;
	private boolean isGreenOrange = false;
	
	final static int HEAD_ROTATION= -120;
	final static int HEAD_ROTATION_CALIB = 180;
	final static int ONE_SECOND = 1000;
	
	public CheckColor(BaseRegulatedMotor left, BaseRegulatedMotor right, EV3ColorSensor cs, BaseRegulatedMotor head) {
		this.mLeft = left;
		this.mRight = right;
		this.head = head;
		this.cs = cs;
		
		//sample provider set to RGB mode
		this.sp = cs.getRGBMode();
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 1);
		//Check for orange using RGB values
		if (((samples[1]>0.003f) && (samples[1]<0.031f) && (samples[2]>0.013f) && (samples[2]<0.050f) && (samples[3]>0.003f) && (samples[3]<0.040f)) ||
				//Check for green using RGB values
				((samples[1]>0.030f) && (samples[1]<0.095f) && (samples[2]>0.005f) && (samples[2]<0.023f) && (samples[3]>0.008f) && (samples[3]<0.019f)))
			isGreenOrange = true;
		//Check for dark environment using RGB values
		else if ((samples[1]>0.100f) && (samples[1]<0.160f) && (samples[2]>0.000f) && (samples[2]<0.050f) && (samples[3]>0.000f) && (samples[3]<0.030f))
			isDark = true;
		return isGreenOrange || isDark;
	}
	
	public void action() {
		//ensuring the bot stops
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		
		//startled rabbit movements
		if(isDark) {
			LCD.drawString("I'm blind!", 1, 2);
			head.rotate(HEAD_ROTATION_CALIB);
			mLeft.rotate(180, false);
			mRight.rotate(360, false);
			head.rotate(HEAD_ROTATION); //rabbit head movements
			Sound.buzz();
		} 
		else if (isGreenOrange) {
			Sound.beepSequence();
			Sound.beepSequenceUp();
			Delay.msDelay(ONE_SECOND);
		}

		//Revert values
		isGreenOrange = false;
		isDark = false;
	}
	
	public void suppress() {} 

}
