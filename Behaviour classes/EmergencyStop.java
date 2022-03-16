import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class EmergencyStop implements Behavior {
	private MovePilot pilot;
	
	public EmergencyStop(MovePilot p) {
		this.pilot = p;
	}
	
	public void printVersion() {
		LCD.drawString("V2 - Robotics Group 2", 0, 0);
		LCD.drawString("Emergency STOP behavior by Adam Tay and Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		return Button.ENTER.isDown();
	}
	
	public void action() {
		pilot.stop();
		System.exit(0);
	}
	
	public void suppress() {}
}
