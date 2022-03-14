# CS1822RoboticsProjectNew documentation
## Details
This is the documentation for the CS1822RoboticsProjectNew repository.
<br>
<br>
<b>Creation date: March 11th, 2022</b>
<br>
<b>Authors: Hiji Arunthavarajah, Sam Brown, Bharat Karki and Adam Tay</b>
## Bugs in program by class
### Driver
* NullPointerException encountered in LightLevel behaviour, specifically when light level is too dark.
* IllegalArgumentException encountered in LightLevel behaviour - sensor mode is not recognised by program.
* DeviceException encountered - Driver class is unable to open sensor port.
## Classes and descriptions
### Driver
The Driver class is the main class that will combine all behaviours together through use of an Arbitrator object.
#### Arbitrator
Link to leJOS documentation on Arbitrator class: https://lejos.sourceforge.io/ev3/docs/lejos/robotics/subsumption/Arbitrator.html
#### Behaviour priorities
Priorities of behaviours are listed below from highest to lowest:
1. BatteryLevel
2. EmergencyStop
3. RabbitSounds
4. Walkabout
### Walkabout
The Walkabout class is an overarching behaviour class that combines all movement-related behaviour classes together, except the TableTrundle and Obstacle classes.<br><br>Both the TableTrundle and Obstacle classes will handle the behaviour of the robot if it is near the edge of the table, or if it encounters an obstacle respectively, and will be of separate priorities to the main Walkabout class of behaviours.
## Positions of sensors
Sensors are located in the following ports on the brick:
* Sensor port 1 - Sound sensor
* Sensor port 2 - Unused sensor port
* Sensor port 3 - Light sensor
* Sensor port 4 - Ultrasonic sensor
