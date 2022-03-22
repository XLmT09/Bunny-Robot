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
	
	public boolean takeControl() {
		sp.fetchSample(samples, 1);
		//Check if orange using RGB
		if((samples[1]>0.003f) && (samples[1]<0.031f) && (samples[2]>0.013f) && (samples[2]<0.050f) && (samples[3]>0.003f) && (samples[3]<0.040f))
			isGreen = true;
		else if ((samples[1]>0.100f) && (samples[1]<0.160f) && (samples[2]>0.000f) && (samples[2]<0.050f) && (samples[3]>0.000f) && (samples[3]<0.030f))
			isDark = true;
		return isGreen || isDark;
	}
	
	public void action() {
		if(isGreen || isDark) {
			mLeft.setSpeed(360);
			mRight.setSpeed(360);
			mLeft.startSynchronization();
			mLeft.stop();
			mRight.stop();
			mLeft.endSynchronization();
			LCD.drawString("stopping", 0, 0);
		} 
		isGreen = false;
		isDark = false;
	}
	
	public void suppress() {
		LCD.clear();
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
	} 

}
