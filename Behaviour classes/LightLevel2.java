import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class LightLevel2 implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private EV3ColorSensor cs;
	SampleProvider sp;
	float[] lightLevel = new float[1];
	
	public LightLevel2(BaseRegulatedMotor left, BaseRegulatedMotor right, EV3ColorSensor cs) {
		this.mLeft = left;
		this.mRight = right;
		this.mLeft.synchronizeWith(new BaseRegulatedMotor[] {this.mRight});
		this.cs = cs;
		this.sp = cs.getAmbientMode();
	}
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Light level behaviour 2 by Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		cs.fetchSample(lightLevel, 0);
		return lightLevel[0] == 0.00f;
	}
	
	public void action() {
		mLeft.startSynchronization();
		mLeft.stop();
		mRight.stop();
		mLeft.endSynchronization();
	}
	
	public void suppress() {
	}
}