import lejos.hardware.lcd.LCD;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.robotics.SampleProvider;
import lejos.robotics.subsumption.Behavior;

public class CarrotColor implements Behavior {
	EV3ColorSensor cs = new EV3ColorSensor(SensorPort.S1);
	SampleProvider sp = cs.getAmbientMode();
	float[] samples = new float[4]; 
	sp = cs.getRGBMode();
	sp.fetchSample(samples, 1);
	
	public void printVersion() {
		LCD.drawString("V1 - Robotics Group 2", 0, 0);
		LCD.drawString("Carrot colour detection behavior by Hiji", 0, 1);
	}
	
	public boolean takeControl() {
		sp.fetchSample(samples, 1);
		//return true if detect orange or green
		return (samples[1]>0.221) && (samples[1]<0.255f) && (samples[2]>0.124) && (samples[2]<0.178f) && (samples[3]>0.005f) && (samples[3]<0.085f)) ||
		((samples[1]>0.012f) && (samples[1]<0.163f) && (samples[2]>0.113f) && (samples[2]<0.255f) && (samples[3]>0.000f) && (samples[3]<0.152f));			
	}
	
	public void action() {
	}
	
	public void suppress() {} 
}