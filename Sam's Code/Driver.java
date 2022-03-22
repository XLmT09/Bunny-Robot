import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.NXTRegulatedMotor;
import lejos.hardware.port.MotorPort;
//import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
//import lejos.robotics.chassis.Chassis;
//import lejos.robotics.chassis.Wheel;
//import lejos.robotics.chassis.WheeledChassis;
//import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;

public class Driver {
	public void printVersion() {
		LCD.drawString("V3 - Robotics Group 2", 0, 0);
		LCD.drawString("Driver class by Adam Tay", 0, 1);
	}
	
	public static void SplashScreen() { // Splash screen method
		Sound.twoBeeps();
		Button.LEDPattern(7);
		LCD.drawString("Jack-Rabbit - ver.", 0, 0);
		LCD.drawString("1 alpha (Mar 2022)", 0, 1);
		LCD.drawString("By Group R02", 0, 3);
		LCD.drawString("Adam Tay", 0, 4);
		LCD.drawString("Sam Brown", 0, 5);
		LCD.drawString("Bharat Karki", 0, 6);
		LCD.drawString("Hiji A.", 0, 7);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}
	
//	private static MovePilot getPilot(Port left, Port right, int diam, int offset) {
//		BaseRegulatedMotor mL = new NXTRegulatedMotor(left);
//		Wheel wL = WheeledChassis.modelWheel(mL, diam).offset(-1 * offset);
//		BaseRegulatedMotor mR = new NXTRegulatedMotor(right);
//		Wheel wR = WheeledChassis.modelWheel(mR, diam).offset(offset);
//		Wheel[] wheels = new Wheel[] {wR, wL};
//		Chassis chassis = new WheeledChassis(wheels, WheeledChassis.TYPE_DIFFERENTIAL);
//		return new MovePilot(chassis);
//	}

	public static void main(String[] args) {
		SplashScreen(); // Calls splash screen method
		
		final int HEAD_ROTATION= -120;
		final int HEAD_ROTATION_CALIB = 180;
		
		BaseRegulatedMotor mLeft = new NXTRegulatedMotor(MotorPort.A);
		BaseRegulatedMotor mRight = new NXTRegulatedMotor(MotorPort.C);
		mLeft.synchronizeWith(new BaseRegulatedMotor[] {mRight});
		
		BaseRegulatedMotor mHead = new EV3LargeRegulatedMotor(MotorPort.B);
		
		mLeft.setSpeed(360);
		mRight.setSpeed(360);
		mHead.setSpeed(180);
		
		mHead.rotate(HEAD_ROTATION_CALIB);
		mHead.rotate(HEAD_ROTATION);
		
		EV3UltrasonicSensor us = new EV3UltrasonicSensor(SensorPort.S4);
		EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S3);
		EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S1);
		
		Behavior trundle = new Trundle(mLeft, mRight, mHead);
		Behavior tableTrundle = new TableTrundle(mLeft, mRight, us, ts);
		Behavior lightLevel = new LightLevel(mLeft, mRight, cs);
		Behavior emergencyStop = new EmergencyStop();
		Behavior batteryLevel = new BatteryLevel();
		
		Arbitrator ab = new Arbitrator(new Behavior[] {trundle, tableTrundle, lightLevel, emergencyStop, batteryLevel}); // Create arbitrator
		ab.go();
		
		us.close();
	}
}
