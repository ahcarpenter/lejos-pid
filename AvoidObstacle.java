//File: AvoidObstacle.java
//Name: Dan Bastin, Drew Carpenter, Paul Simms
//Date: 04/27/09
//Description: AvoidObstacle subclass - highest level action

import lejos.subsumption.*;
import lejos.nxt.*;
import lejos.navigation.*;

public class AvoidObstacle implements Behavior
{
 UltrasonicSensor ultrasonicSensor;
 
 public AvoidObstacle( )
 {
   ultrasonicSensor = new UltrasonicSensor(SensorPort.S4);
 }
 
 public boolean takeControl( ) //When AvoidObstacle should take over
 {
  ultrasonicSensor.continuous();
  return ((ultrasonicSensor.getDistance( ) < 50 )); // < 23cm
 }
 
 public void suppress( )
 {
  Motor.B.stop( );
  Motor.C.stop( );
 }
 
 public void action( ) //AvoidObstacle's action's
 {
  LCD.drawString("Obstacle!", 10, 15, true);
  Motor.B.backward( );
  Motor.C.backward( );
  try
  {
   Thread.sleep(1000); //1000ms = 1 sec
  }
  catch(Exception e) { } //ignore if sleep fails
  Motor.B.stop( );
  Motor.C.stop( );
  Motor.C.forward( );
  try
  {
   Thread.sleep(500); //500ms = .5 sec
  }
  catch(Exception e) { }
  Motor.C.stop( );
 }
}//end AvoidObstacle
