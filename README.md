</div>

<div align="center">
  
  <h1>Waste Incinerator</h1>
  
  Distributed software system for a ***waste Incinerator service***. The project had been developed for the course of Software Systems Engineering M of the 
  University of Bologna, using the [SCRUM agile framework](https://www.scrum.org/resources/what-is-scrum).
  
  [System requirements](https://github.com/NicoleGiulianelli2/TemaFinale2024/blob/main/commons/System%20requirements.pdf)
  ·
  [SCRUM Guide](./commons/2020-Scrum-Guide-US.pdf)

   <img align="center" width="50%" src="./commons/gifRobot.gif"/>

</div>


## The system

The system is based on the concept of **actors** and therefore operates by exchanging messages
It consists of different elements:

#### Virtualrobot/basicRobot
Web application provided by the customer that simulates a Robot. The robot is confined in a virtual environment (a rectangular room) and can receive commands to perform some actions. 
The robot's movements are intermediated by an interface called basicRobot which also allows to control other types of (physical) robots.

<div align="center">
<img align="center" width="50%" src="./commons/ScreenshotRobot.png"/>
</div>

#### Waste Incinerator Core

The core of the application has three components: the **WIS** (coordinator), the **Incinerator** and the **OpRobot**. The WIS has the task of understanding when the requirements are met and sending a signal to the OpRobot, which will start the routine by moving the virtualRobot.
