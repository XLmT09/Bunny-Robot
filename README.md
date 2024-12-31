# LeJos Bunny Robot
In this project, we designed and built an intelligent robotic bunny using LEGO bricks, the LeJOS EV3 platform, and its advanced sensors. The LeJOS EV3 provides a lightweight Java Virtual Machine (JVM) that enables Java programming for LEGO robots. It also offers a comprehensive library for controlling motors, sensors, and other hardware components, giving us the tools to create a responsive and lifelike robotic bunny.

## 1 Features
The goal of this project was to replicate the behavior of a real bunny through creative engineering and programming. The robotic bunny moves using a unique hybrid mechanism: it is equipped with two rear wheels for propulsion and two front legs that lift the bunny’s body off the ground, mimicking the characteristic "hopping" motion of a rabbit.

### 1.1 Features Included are:
- **Hybrid Hopping Mechanism:**
The rear wheels provide forward movement, while the two front legs lift the bunny’s body to create a convincing hopping motion.\
- **Environmental Awareness and Interaction:**
  - **Obstacle Avoidance:** The bunny uses ultrasonic and touch sensors to detect obstacles and prevent collisions. It also ensures safety by avoiding falls from elevated surfaces, such as tables.
  - **Color-Based Behavior:** Using its EV3 color sensor, the bunny recognizes specific colors—green for grass and orange for carrots—and accelerates toward them, simulating excitement.
  - **Sound Sensitivity:** The robot responds to loud noises by "getting scared," stopping in place, and looking around as if startled.
  - **Low-Light Adaptation:** In the dark, the bunny hops "blindly," adding an unpredictable and playful dimension to its behavior.

## 2 Robot Technical Specifications
### 2.1 Classes
**Driver Class:**\
The Driver class is the main class that will combine all behaviours together through use of an Arbitrator object.\
**Arbitrator Class:**\
Link to leJOS documentation on Arbitrator class: https://lejos.sourceforge.io/ev3/docs/lejos/robotics/subsumption/Arbitrator.html\
**Walkabout Class**\
The Walkabout class is an overarching behaviour class that combines all movement-related behaviour classes together, except the TableTrundle and Obstacle classes.
**TableTrundle and Obstacle Classes**\
Both the TableTrundle and Obstacle classes will handle the behaviour of the robot if it is near the edge of the table, or if it encounters an obstacle respectively, and will be of separate priorities to the main Walkabout class of behaviours.

### 2.2 Behaviour priorities
Priorities of behaviours to be executed from highest to lowest:
1. BatteryLevel
2. EmergencyStop
3. RabbitSounds
4. Walkabout

## 2.3 Positions of Sensors
Sensors are located in the following ports on the brick:
* Sensor port 1 - Touch sensor
* Sensor port 2 - Sound sensor
* Sensor port 3 - Light sensor
* Sensor port 4 - Ultrasonic sensor

## 3 Contributors
- Hijithan Arunthavarajah
- Sam Brown
- Bharat Karki
- Adam Tay

## 4 Credits
1. [Inspiration Design for the Project](https://ev3lessons.com/RobotDesigns/instructions/EAST3RBUNNY.pdf)
2. [Sound File Implentation](https://gist.github.com/andibakti/0d5162f6c0ddd007131af83dd3537acd)
3. [Happy Sound](https://www.youtube.com/watch?v=m-uVpN4djzE)
4. [Scared Sound](https://www.youtube.com/watch?v=S83hCpKBgeQ) 
5. [Eating Sound](https://www.youtube.com/watch?v=xgPCtX3EvPI)