//need to pass theta(degrees) and distance in

double vr=0, vl=0, t=.1, b=9.25, theta0=0, pterm=0, iterm=0, dterm=0, kp=0, ki=0, kd=0, correction;
double turnangle;
int countleft = 0, countright = 0, rightturnclicks, leftturnclicks;
boolean atGoal = false;

vl = pilot.getLeftActualSpeed(); 		//these two commands are automatically updated every 100 milliseconds
vr = pilot.getRightActualSpeed(); 		//so I am not sure if they go in the loop or not

pterm = ((vr+vl)/2)*Math.sin(((vr-vl)*t)/b + theta0);
iterm = ((b*(vr + vl))/(2*(vr-vl)))*(Math.cos(((vr-vl)*t)/b + theta0) - Math.cos(theta0);
dterm = (((vr + vl)*(vr - vl))/(2*b))*Math.cos(((vr - vl)*t)/b + theta0);

correction = kp*pterm + ki*iterm + kd*dterm;

if (correction == 0)
	start over
				// i don't know this command;	
else
{
	theta0 = (vr - vl)*t*(Math.PI/180);		//since theta0 is being used inside trig functions, i believe we want to convert it to radians
	theta0 = Math.toRadians(theta0);
	vr = vr - correction;
	Motor.C.setSpeed((int)vr);		// i forget which port our motor is plugged into
}




//To turn accurately without using the pilot class
//We calculate the angle as usual, then with that angle in degrees we do:
if (theta !=0)	//we do everything in here if theta is nonzero, if it is zero, we deal with that differently
{
	rightturnclicks = (theta/360)*(9.25*(Math.PI))*(10/1.3);	//this calculates the number of clicks needed to change the direction robbie faces
	leftturnclicks = -rightturnclicks;			//by moving the wheels along the circumference of a circle with a diameter
	
	resetTachoCount;		
	
	countleft = pilot.getLeftCount();		//We now get both tachocounts for our loop
	countright = pilot.getRightCount();
}

while ((countright < rightturnclicks) && (countleft < leftturnclicks))
{	
 if(rightturnclicks > 0)
 {
	Motor.C.backward();			//the direction of these move commands, as others in this program, may need to be reversed
	Motor.B.forward();
 }

 if(rightturnclicks < 0)
 {
	Motor.C.forward();
	Motor.B.backward();
 }

vr = pilot.getRightActualSpeed();
vl = Math.abs(pilot.getLeftActualSpeed( ));	//we take the absolute value so we can again use PID to synchronize the rotation velocity of the wheels

pterm = ((vr+vl)/2)*Math.sin(((vr-vl)*t)/b + theta0);
iterm = ((b*(vr + vl))/(2*(vr-vl)))*(Math.cos(((vr-vl)*t)/b + theta0) - Math.cos(theta0);
dterm = (((vr + vl)*(vr - vl))/(2*b))*Math.cos(((vr - vl)*t)/b + theta0);

correction = kp*pterm + ki*iterm + kd*dterm;

if (correction = 0)
	go back to top of loop;			//once again, i am unsure of this command

vr = vr - correction;

Motor.C.setspeed(vr);	

countleft = pilot.getLeftCount();		//We get both tachocounts for our loop again so we can check the progress we have made
countright = pilot.getRightCount();
}



















