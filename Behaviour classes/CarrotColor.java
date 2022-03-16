import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;
import lejos.utility.Delay;

public class CarrotColor implements Behavior{
	EV3ColorSensor cs;
	SampleProvider sp;
	float[] samples = new float[4];
	private MovePilot pilot;
	
	CarrotColor(MovePilot p, EV3ColorSensor cs) {
		this.pilot = p; 
		this.cs = cs;
		this.sp = cs.getRGBMode();
	}
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Carrot colour detection behavior by Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 1);
		//return true if detect orange or green
		return ((samples[1]>0.221) && (samples[1]<0.255f) && (samples[2]>0.124) && (samples[2]<0.178f) && (samples[3]>0.005f) && (samples[3]<0.085f)) ||
		((samples[1]>0.012f) && (samples[1]<0.163f) && (samples[2]>0.113f) && (samples[2]<0.255f) && (samples[3]>0.000f) && (samples[3]<0.152f));			
	}
	
	public void action() {
		LCD.drawString("Act", 0, 0);
		pilot.setLinearSpeed(120);
		Delay.msDelay(1000);
	}
	
	public void suppress() {
		LCD.clear();
		pilot.setLinearSpeed(50);
	} 

}
