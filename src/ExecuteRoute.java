//File: ExecuteRoute.java
//Name: Ian, Drew Carpenter, Sam
//Date: 04/29/09
//Description: ExecuteRoute

import lejos.nxt.*;
import lejos.navigation.*;
import java.lang.Math.*;
import lejos.subsumption.*;

public class ExecuteRoute implements Behavior
{
	double x1 = 0, y1 = 0, x2 = 1, y2 = 3, distance = 0, x = 0, y = 0, x_squared = 0, y_squared = 0, theta = 0, theta_ini = 0, rightturnclicks = 0;
	TachoNavigator pilot;
 
 public ExecuteRoute( )
 {
	 pilot = new TachoNavigator(5.6F, 9.5F, Motor.B, Motor.C, true);
 }
 
 public boolean takeControl( )
 {
  	return true;
 }
 
 public void suppress( )
 {
 	Motor.B.stop( );
	Motor.C.stop( );
 }
 
 public void action( )
 {
	x = x2 - x1;
  	y = y2 - y1;

 	x_squared = Math.pow((Math.abs(x)), 2.0);
  	y_squared = Math.pow((Math.abs(y)), 2.0);
  
	distance = Math.sqrt(y_squared + x_squared);
  
  	if((x >= 0) && (y >= 0))
  	{
   		theta = Math.acos(x / distance);
  		theta = (theta*180)/(Math.PI);
   		theta = Math.abs(theta);
  		
LCD.clear( );   
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x > 0) && (y < 0))
  	{
   		theta = -(Math.acos(x / distance));
   		theta = (theta*180)/(Math.PI);
   		
LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x < 0) && (y > 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);

LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}

  	if((x <= 0) && (y <= 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);
   		theta = -theta;
   
LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );

  	}

	distance = distance * 22.9;

	if(theta > 0)
	{
LCD.drawString("rotating1", 1, 3);
Button.waitForPress( );
LCD.clear( );
		pilot.rotate((float)theta, false);
	}
	
	if(theta<0){
LCD.drawString("rotating2", 1, 3);
Button.waitForPress( );
LCD.clear( );
		pilot.rotate(-(float)theta, false);
	}

	pilot.travel((float)distance, true);
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////  

 	x1 = 1; 
  	y1 = 3; 
  	x2 = -1; 
  	y2 = 0; 
  	theta_ini = theta;
	
	x = x2 - x1;
  	y = y2 - y1;
   
  	x_squared = Math.pow((Math.abs(x)), 2.0);
  	y_squared = Math.pow((Math.abs(y)), 2.0);
  
	distance = Math.sqrt(y_squared + x_squared);
  
  	if((x >= 0) && (y >= 0))
  	{
   		theta = Math.acos(x / distance);
  		theta = (theta*180)/(Math.PI);
   		theta = Math.abs(theta);
		theta = theta - theta_ini;
  		
LCD.clear( );   
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x > 0) && (y < 0))
  	{
   		theta = -(Math.acos(x / distance));
   		theta = (theta*180)/(Math.PI);
		theta = theta - theta_ini;
   		
LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x < 0) && (y > 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);

LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}

  	if((x <= 0) && (y <= 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);
		theta = theta + theta_ini;
		//theta = (theta + 180);
		theta = -theta;
		
LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
  	}

	distance = distance * 22.9;
	//theta = theta - theta_ini;

LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
	
LCD.drawString("rotating11", 1, 3);
Button.waitForPress( );
LCD.clear( );
	pilot.rotate((float)theta, false);
	pilot.travel((float)distance, true);

//////////////////////////////////////////////////////////////////////////


 	x1 = -1; 
  	y1 = 0; 
  	x2 = 2; 
  	y2 = -3; 
  	theta_ini = theta;
	
	x = x2 - x1;
  	y = y2 - y1;
   
  	x_squared = Math.pow((Math.abs(x)), 2.0);
  	y_squared = Math.pow((Math.abs(y)), 2.0);
  
	distance = Math.sqrt(y_squared + x_squared);
  
  	if((x >= 0) && (y >= 0))
  	{
   		theta = Math.acos(x / distance);
  		theta = (theta*180)/(Math.PI);
   		theta = Math.abs(theta);
		theta = theta - theta_ini;
  		
LCD.clear( );   
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x > 0) && (y < 0))
  	{
   		theta = (Math.acos(x / distance));
   		theta = (theta*180)/(Math.PI);
		theta= theta + theta_ini;
		theta = theta - (180 -(((Math.acos(x / distance))*180)/(Math.PI)));
LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );
  	}
   
  	if((x < 0) && (y > 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);

LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}

  	if((x <= 0) && (y <= 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);
		theta = theta + theta_ini;
		theta = -theta;
		
LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
  	}

	distance = distance * 22.9;

LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
	
LCD.drawString("rotating11", 1, 3);
Button.waitForPress( );
LCD.clear( );
	pilot.rotate((float)theta, false);
	pilot.travel((float)distance, true);
	
///////////////////////////////////////////////////////////////////



 	x1 = 2; 
  	y1 = -3; 
  	x2 = -1; 
  	y2 = 4; 
  	theta_ini = theta;
	
	x = x2 - x1;
  	y = y2 - y1;
   
  	x_squared = Math.pow((Math.abs(x)), 2.0);
  	y_squared = Math.pow((Math.abs(y)), 2.0);
  
	distance = Math.sqrt(y_squared + x_squared);
  
  	if((x >= 0) && (y >= 0))
  	{
   		theta = Math.acos(x / distance);
  		theta = (theta*180)/(Math.PI);
   		theta = Math.abs(theta);
		theta = theta - theta_ini;
  		
LCD.clear( );   
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}
   
  	if((x > 0) && (y < 0))
  	{
   		theta = (Math.acos(x / distance));
   		theta = (theta*180)/(Math.PI);
		theta= theta + theta_ini;
		theta = theta - (180 -(((Math.acos(x / distance))*180)/(Math.PI)));
LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );
  	}
   
  	if((x < 0) && (y > 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);
		theta= theta + theta_ini;
		theta = theta - (180 -(((Math.acos(x / distance))*180)/(Math.PI)));
		theta = - theta;

LCD.clear( ); 
LCD.drawInt((int)theta, 1, 3);
Button.waitForPress( );
LCD.clear( );

  	}

  	if((x <= 0) && (y <= 0))
  	{
   		theta = Math.acos(x / distance);
   		theta = (theta*180)/(Math.PI);
		theta = theta + theta_ini;
		theta = -theta;
		
LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
  	}

	distance = distance * 22.9;

LCD.drawInt((int)theta, 1, 3); //outputs angle
Button.waitForPress( );
LCD.clear( );	
	
LCD.drawString("rotating11", 1, 3);
Button.waitForPress( );
LCD.clear( );
	pilot.rotate((float)theta, false);
	pilot.travel((float)distance, true);
 } 
}
