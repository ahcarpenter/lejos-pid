import lejos.nxt.*;
import lejos.navigation.*;
import java.lang.Math.*;
import lejos.subsumption.*;

public class PID implements Behavior
{
	double vr=0, vl=0, t=.1, b=11.5, pterm=0, iterm=0, dterm=0, kp=1.0, ki=.6, kd=.5, correction=0, theta0, vr_vl_rad;
	Pilot pilot;
	StoredVar theta;
	int rightClick, leftClick;

 public PID(StoredVar value)
 {
	theta = value;
	TachoNavigator = new Pilot(5.6F, 11.5F, Motor.B, Motor.C, true);
 }
 
 public boolean takeControl( )
 {
	vl = Math.abs(pilot.getLeftActualSpeed()); 		
	vr = Math.abs(pilot.getRightActualSpeed());

LCD.clear();
LCD.drawString("L_actual", 1, 4);
LCD.drawInt((int)vl, 1, 3);
LCD.drawString("R_actual", 1, 2);
LCD.drawInt((int)vr, 1, 1);
Button.waitForPress( );
LCD.clear();

	if(vl != vr)
		return true;
	else
		return false;
 }
 
 public void suppress( )
 {
 }
 
 public void action( )
 {
	theta0 = theta.getTheta();
	vl = Math.abs(pilot.getLeftActualSpeed( )); 		
	vr = Math.abs(pilot.getRightActualSpeed( )); 
	
	vr_vl_rad = (vr-vl)*(Math.PI/180);

	pterm = ((vr+vl)/2)*(Math.sin((vr_vl_rad*t)/b + theta0));
	iterm = (((-(b*(vr + vl))/(2*(vr-vl)))*(Math.cos(vr_vl_rad*t)/b + theta0)) - (Math.cos(theta0)));
	dterm = (((vr + vl)*(vr - vl))/(2*b))*(Math.cos((vr_vl_rad*t)/b + theta0));

LCD.clear( ); 
LCD.drawString("pterm", 1, 2);
LCD.drawInt((int)pterm, 1, 1);
LCD.drawString("iterm", 1, 4);
LCD.drawInt((int)iterm, 1, 3);
LCD.drawString("dterm", 1, 6);
LCD.drawInt((int)dterm, 1, 5);
Button.waitForPress( );
LCD.clear();

	correction = (kp*pterm) + (ki*iterm) + (kd*dterm);
	theta0 = ((vr - vl)*(t)*(Math.PI/180));		//converts theta0 to radians
	theta.setTheta(theta0);
	


LCD.drawString("correction = ", 1, 4);
LCD.drawInt((int)correction, 1, 3);
LCD.drawString("vr = ", 1, 2);
LCD.drawInt((int)vr, 1, 1);
LCD.drawString("vl = ", 1, 6);
LCD.drawInt((int)vl, 1, 5);
Button.waitForPress( );
LCD.clear();


	vr = vr - correction;
	Motor.C.setSpeed((int)vr);
  }
 }
	


















