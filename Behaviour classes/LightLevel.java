import java.io.File;

import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
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
	
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Light level behaviour by Hiji", 0, 1);
		LCD.drawString("Sound implementation by Adam Tay", 0, 2);
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
		Sound.setVolume(50); // sets volume to half
		Sound.playSample(new File("scared rabbit sound.wav")); // plays wav file at half volume
		Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("Too dark!", 0, 0);
	}
	
	public void suppress() {
		LCD.clear();
		mLeft.startSynchronization();
		mLeft.setSpeed(180);
		mRight.setSpeed(180);
		mLeft.forward();
		mRight.forward();
	}
}
