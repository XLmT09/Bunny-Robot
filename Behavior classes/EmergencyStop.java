import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class EmergencyStop implements Behavior {
	private MovePilot pilot;
	private EV3TouchSensor ts = new EV3TouchSensor(SensorPort.S3);
	private SampleProvider sp = ts.getTouchMode();
	private float[] fetchTouch = new float[1];
	private int isPressed;
	private final int FIVE_SECONDS = 5000;
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Emergency STOP behavior by Adam Tay", 0, 1);
	}
	
	public boolean takeControl() {
		sp.fetchSample(fetchTouch, 0);
		isPressed = (int) fetchTouch[0];
		return Button.ESCAPE.isDown() || isPressed == 1;
	}
	
	public void action() {
		pilot.stop();
		Delay.msDelay(FIVE_SECONDS);
	}
	
	public void suppress() {} // suppress method is empty
}
