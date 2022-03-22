import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	public static void main(String[] args) {
		BaseRegulatedMotor mLeft = new NXTRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new NXTRegulatedMotor(MotorPort.C);
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		NXTSoundSensor us = new NXTSoundSensor(SensorPort.S2);
		EV3UltrasonicSensor ds = new EV3UltrasonicSensor(SensorPort.S4);
		SampleProvider sp = cs.getRGBMode();
		
		SplashScreen();
		float backgroundNoise = Calibrate.calibrateBackground(us);
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
		
		Forward forward = new Forward(mLeft, mRight);
		BatteryLevel btLevel = new BatteryLevel();
		EmergencyStop stop = new EmergencyStop();
		CheckColor checkCol = new CheckColor(mLeft, mRight, sp);
		SoundResponse sound = new SoundResponse(mLeft, mRight, us, backgroundNoise);
		CheckFloor floor = new CheckFloor(mLeft, mRight, ds);
		Arbitrator ab = new Arbitrator(new Behavior[] {forward, checkCol, sound, floor, stop, btLevel});

		ab.go();
	}
	
	public static void SplashScreen() {
		Button.LEDPattern(7);
		LCD.drawString("Jack-Rabbit - ver.", 0, 0);
		LCD.drawString("1 alpha (Mar 2022)", 0, 1);
		LCD.drawString("", 0, 2);
		LCD.drawString("By Robotics Group", 0, 3);
		LCD.drawString("2 (Adam Tay,", 0, 4);
		LCD.drawString("Sam Brown,", 0, 5);
		LCD.drawString("Bharat Karki and", 0, 6);
		LCD.drawString("Hiji A.)", 0, 7);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
}
