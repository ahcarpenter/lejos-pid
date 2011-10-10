//File: FindX.java
//Name: Dan Bastin, Drew Carpenter, Paul Simms
//Date: 04/27/09
//Description: Finds ten X points

import lejos.subsumption.*;
import lejos.navigation.*;

public class FindX
{
 public static void main (String[]args)
 {
	StoredVar storeVar = new StoredVar(0.0);
	Behavior executeRoute = new ExecuteRoutenew();
	//Behavior pid = new PID(storeVar);  
	Behavior avoidObstacle = new AvoidObstacle( );

 
  	Behavior [] bArray = {executeRoute}; //creates new array of behaviors
  	WorkingArbitrator FindX = new WorkingArbitrator(bArray);
  
  	FindX.start( );
 }
}//end FindX

