import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	
	private final int NORMAL_SPEED = 180;
	
	Forward(BaseRegulatedMotor left, BaseRegulatedMotor right) {
		this.mLeft = left;
		this.mRight = right;
	}
	
	public boolean takeControl() {
		return true; //always returns base case
	}

	public void action() {
		LCD.drawString("Chugging along!", 1, 2);
		
		mLeft.setSpeed(NORMAL_SPEED);
		mRight.setSpeed(NORMAL_SPEED);
		
		mLeft.startSynchronization();
		mLeft.forward();
		mRight.forward();
		mLeft.endSynchronization();
	}
	
	public void suppress() {
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
		
		LCD.clear();
	}
}
