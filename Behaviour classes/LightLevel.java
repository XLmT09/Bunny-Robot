import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class LightLevel implements Behavior {
	private MovePilot pilot;
	private EV3ColorSensor cs;
	private SampleProvider sp;
	private float[] lightLevel = new float[1];
	
	public LightLevel(MovePilot p, EV3ColorSensor cs) {
		this.pilot = p;
		this.cs = cs;
		this.sp = cs.getAmbientMode();
	}
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Light level behaviour by Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		cs.fetchSample(lightLevel, 0);
		return lightLevel[0] == 0.00f; // return true if completely dark - for testing purposes only
	}
	
	public void action() {
		pilot.setLinearSpeed(100);
		//Sound.playSample(new File("scared rabbit sound.wav"), Sound.VOL_MAX); // plays wav file at full volume
		//Button.LEDPattern(5); // red flashing LED light on EV3 brick
		LCD.drawString("" + lightLevel[0], 0, 3);
		
	}
	
	public void suppress() {
		LCD.clear();
		pilot.setLinearSpeed(180);	
	}
}
