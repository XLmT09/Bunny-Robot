# CS1822RoboticsProjectNew documentation
## Details
This is the first version of documentation for the CS1822RoboticsProjectNew repository.
<br>
<br>
<b>Creation date: March 11th, 2022</b>
<br>
<b>Authors: Hiji Arunthavarajah, Sam Brown, Bharat Karki and Adam Tay</b>
## Bugs in program by class
### Driver class
1. NullPointerException thrown when EmergencyStop behaviour takes control of the robot. This is probably to do with the Behaviour array within the arbitrator object or lack of arguments in the declaration of the EmergencyStop behaviour.
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
The Walkabout class is an overarching behaviour class that combines all movement-related behaviour classes together.
## Positions of sensors
Sensors are located in the following ports on the brick:
* Sensor port 1 - Sound sensor
* Sensor port 2 - Ultrasonic sensor
* Sensor port 3 - Light sensor
* Sensor port 4 - Unused sensor port
