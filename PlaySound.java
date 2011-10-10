//File: PlaySound.java
//Name: Dan Bastin, Drew Carpenter, Paul Simms
//Date: 04/27/09
//Description: PlaySound

import lejos.subsumption.*;
import lejos.nxt.*;
import lejos.navigation.*;

public class PlaySound implements Behavior
{
	Pilot pilot;
 
 public PlaySound( )
 {
	pilot = new Pilot(5.6F, 11.5F, Motor.B, Motor.C, true);
 }
 
 public boolean takeControl( ) //When AvoidObstacle should take over
 {
  if(pilot.isMoving( ) != true)
	return true;
  else 
	return false	
 }
 
 public void suppress( )
 {
  Motor.B.stop( );
  Motor.C.stop( );
 }
 
 public void action( ) //AvoidObstacle's action's
 {
  Sound.twoBeeps();
 }
}//end AvoidObstacle
