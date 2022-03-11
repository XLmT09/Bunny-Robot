# CS1822RoboticsProjectNew documentation
## Details
This is the first version of documentation for the CS1822RoboticsProjectNew repository.
<b>Created on: March 11th, 2022</b>
<b>Authors: Hiji Arunthavarajah, Sam Brown, Bharat Karki and Adam Tay</b>
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
