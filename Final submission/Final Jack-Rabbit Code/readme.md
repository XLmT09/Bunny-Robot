# Robotics Group 2 - Description of Each File
## Classes
<b>BatteryLevel</b> - Runs the BatteryLevel behavior of the robot to ensure that the program doesn't run if the battery gets too low
<br>
<b>Calibrate</b> - Runs calibration for the SoundSensor object, so that when SoundResponse runs it isn't too sensitive
<br>
<b>CheckColor</b> - Checks for changes in color in the environment, reacting to black as if it's blind, and green or orange as if it's food
<br>
<b>EmergencyStop</b> - Causes the program to shut off and exit when the ENTER button of the EV3 brick is pressed
<br>
<b>Forward</b> - Simple behavior, just causes the program to move forward indefinitely unless another behavior takes hold
<br>
<b>Main</b> - The main class of the program, ties together all the behaviors and calibration necessary, also initialising sensors and motors
<br>
<b>SoundResponse</b> - Causes the robot to stop for three seconds if a (relatively) loud noise is detected
<br>
<b>TableTrundle</b> - Checks for changes in the floor of the environment, to ensure that it does not fall off of a ledge or table, and also for obstacles to ensure it does not crash into them
<br>
<br>
<b>Group members: Hiji Arunthavarajah, Sam Brown, Bharat Karki and Adam Tay</b>
