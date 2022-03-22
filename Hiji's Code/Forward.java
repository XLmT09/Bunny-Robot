import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	
	Forward(BaseRegulatedMotor left, BaseRegulatedMotor right) {
		this.mLeft = left;
		this.mRight = right;
	}
	
	public boolean takeControl() {
		return true; 
	}

	public void action() {
		LCD.clear();
		mLeft.setSpeed(180);
		mRight.setSpeed(180);
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();
	}
	
	public void suppress() {
	}
}
