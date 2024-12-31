import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Main {
	final static int HEAD_ROTATION = -120;
	final static int HEAD_ROTATION_CALIB = 180;
	
	public static void main(String[] args) {
		//all motors used
		BaseRegulatedMotor mLeft = new NXTRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mHead = new EV3MediumRegulatedMotor(MotorPort.B);
		BaseRegulatedMotor mRight = new NXTRegulatedMotor(MotorPort.C);

		//all sensors used
		EV3TouchSensor touchSensor = new EV3TouchSensor(SensorPort.S1);
		NXTSoundSensor soundSensor = new NXTSoundSensor(SensorPort.S2);
		EV3ColorSensor colSensor = new EV3ColorSensor(SensorPort.S3);
		EV3UltrasonicSensor distSensor = new EV3UltrasonicSensor(SensorPort.S4);
		
		//introduction screen
		SplashScreen();

		//sensor calibration and motor synchronisation
		float backgroundNoise = Calibrate.calibrateBackground(soundSensor);
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
		
		//adjusting the head motor so it is in the right place
		mHead.rotate(HEAD_ROTATION_CALIB);
		mHead.rotate(HEAD_ROTATION);
		
		//all behaviours
		Forward forward = new Forward(mLeft, mRight);
		BatteryLevel btLevel = new BatteryLevel();
		EmergencyStop stop = new EmergencyStop();
		CheckColor checkCol = new CheckColor(mLeft, mRight, colSensor, mHead);
		SoundResponse sound = new SoundResponse(mLeft, mRight, soundSensor, backgroundNoise);
		TableTrundle floor = new TableTrundle(mLeft, mRight, distSensor, touchSensor);
		Arbitrator ab = new Arbitrator(new Behavior[] {forward, checkCol, sound, floor, stop, btLevel});

		//running arbitrator
		ab.go();
	}
	
	public static void SplashScreen() {
		Button.LEDPattern(7);
		LCD.drawString("Jack-Rabbit - ver.", 0, 0);
		LCD.drawString("Final (Mar 2022)", 0, 1);
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
