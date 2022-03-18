import lejos.hardware.Button;
import lejos.hardware.Sound;
import lejos.hardware.lcd.LCD;
import lejos.hardware.port.Port;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.NXTSoundSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.SampleProvider;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.subsumption.Behavior;

class SoundFilter implements SampleProvider{
	private float threshold;
	private final int timeGap;
	private final SampleProvider soundSampleProvider;
	private long lastHeard;
	
	SoundFilter(SensorMode soundMode, float threshold, int timeGap) {
		this.threshold = threshold;
		this.timeGap = timeGap;
		soundSampleProvider = soundMode;
		lastHeard = -2 * timeGap;
	}
	
	public void fetchSample(float level[], int index) {
		level[index] = 0.0f;
		long now = System.currentTimeMillis();
		
		if (now - lastHeard > timeGap) {
			soundSampleProvider.fetchSample(level, index);
			
			if (level[index] >= threshold) {
				level[index] = 1.0f;
				lastHeard = now;
			}
			else {
				level[index] = 0.0f;
			}
		}
	}

	public int sampleSize() {
		return 1;
	}
	
	public void setThreshold(float threshold) {
		this.threshold = threshold;
	}
}

class SoundResponse implements Behavior {
	
	private MovePilot pilot;
	private SampleProvider soundSampleProvider;
	private float[] soundSample = new float[1];
	private float maxSoundLevel;
	
	public SoundResponse(SoundFilter soundSampleProvider, float maxSoundLevel) {
		this.soundSampleProvider = soundSampleProvider;
		this.maxSoundLevel = maxSoundLevel;
	}
	
	@Override
	public boolean takeControl() {
		soundSampleProvider.fetchSample(soundSample, 0);
		
		return soundSample[0] == 1.0f;
	}

	@Override
	public void action() {
		pilot.stop();
		Button.LEDPattern(5);
		LCD.drawString("I heard something", 0, 0);
		Button.ENTER.waitForPressAndRelease();
		LCD.clear();
	}

	@Override
	public void suppress() {
		((SoundFilter) soundSampleProvider).setThreshold(maxSoundLevel);
		pilot.setLinearSpeed(180);
		pilot.forward();
	}
}
