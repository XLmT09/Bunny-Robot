import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class LightLevel implements Behavior {
	private BaseRegulatedMotor mLeft;
	private BaseRegulatedMotor mRight;
	private EV3ColorSensor cs;
	SampleProvider sp;
	float[] lightLevel = new float[1];
	
	public LightLevel(BaseRegulatedMotor left, BaseRegulatedMotor right, EV3ColorSensor cs) {
		this.mLeft = left;
		this.mRight = right;
		this.mLeft.synchronizeWith(new BaseRegulatedMotor[] {this.mRight});
		this.cs = cs;
		this.sp = cs.getAmbientMode();
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

//new programme to test
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class LightLevel implements Behavior {
	private BaseRegulatedMotor mLeft = new NXTRegulatedMotor(MotorPort.A);
	private BaseRegulatedMotor mRight = new NXTRegulatedMotor(MotorPort.C);	
	private EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
	SampleProvider sp = cs.getRGBMode(); 
	float[] colLevel = new float[4];
	
	public LightLevel() {
		this.mLeft.synchronizeWith(new BaseRegulatedMotor[] {this.mRight});
	}
	
	public boolean takeControl() {
		sp.fetchSample(colLevel, 0);
		//Check if dark using RGB
		return ((colLevel[1] >= 0.000f) && (colLevel[1] <= 0.063f) && (colLevel[2] >= 0.000f) && 
				(colLevel[2] <= 0.044f) && (colLevel[3] >= 0.000f) && (colLevel[3] <= 0.043f));
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
