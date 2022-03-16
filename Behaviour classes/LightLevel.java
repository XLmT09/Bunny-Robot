import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

public class LightLevel implements Behavior {
	private MovePilot pilot;
	private EV3ColorSensor cs;
	SampleProvider sp;
	float[] lightLevel = new float[1];
	private boolean suppressed = true;
	
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
		return lightLevel[0] == 0.00f;
	}
	
	public void action() {
		pilot.setLinearSpeed(100);
		// plays wav file at full volume
		//Sound.playSample(new File("scared rabbit sound.wav"), Sound.VOL_MAX);
		// red flashing LED light on EV3 brick
		//Button.LEDPattern(5);
		LCD.drawString("" + lightLevel[0], 0, 3);
		
	}
	
	public void suppress() {
		LCD.clear();
		pilot.setLinearSpeed(180);	
	}
}
