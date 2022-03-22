import lejos.hardware.lcd.LCD;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

public class Calibrate {
	public static float calibrateBackground(NXTSoundSensor us) {
		float[] soundSample = new float[5];
		float averageLevel;
		float totalLevel = 0.f;
		final int ONE_SECOND = 1000;
		SampleProvider sp = us.getDBAMode();
		
		LCD.drawString("Getting background noise", 1, 1);
		
		for (int i = 0; i < soundSample.length; i++) {
			sp.fetchSample(soundSample, i);
			Delay.msDelay(ONE_SECOND);
			totalLevel += soundSample[i];
		}
		
		LCD.clear();
		
		averageLevel = totalLevel / 5;
		return averageLevel;
	}
}
