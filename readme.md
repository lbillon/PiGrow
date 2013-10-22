PIGrower
=========
PIGrower is a simple Java gardening automation software for the Raspberry PI.

Features
--------

* Auto watering of the plants on a defined schedule (every x hours...)
* Lighting of the plants that can be set to (on, off or auto)
* Reads input from serial interface to gather ambient lighting data
* Remote control from a neat web interface

Tech
-----------
PIGrower is powered by the following technologies : 

* Java JEE
* The Spring Framework ()
* Twitter Bootstrap
* Angular.js
* PI4J


Installation
--------------
PIGrower deploys as a simple .war package in your favorite Servlet 3.0-compliant webapp container.

However, this software requires access to the /dev/mem in order to access the GPIO. 
A simple way to run it on a RaspberryPI is to use [webapp-runner](https://github.com/jsimone/webapp-runner)

License
----
MIT

  
    
