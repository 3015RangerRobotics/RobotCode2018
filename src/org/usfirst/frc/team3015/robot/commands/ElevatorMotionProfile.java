package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.Robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.Timer;

/**
 * Follows a motion profile
 */
public class ElevatorMotionProfile extends CommandBase {
	private volatile boolean isFinished = false;
	private double[][] motion;
	private volatile int i =  0;
	private volatile double prevError = 0;

    public ElevatorMotionProfile(double[][] motionProfile) {
    	requires(elevator);
    	this.motion = motionProfile;
    }

    protected void initialize() {
    	isFinished = false;
    	i = 0;
    	prevError = 0;
    	
    	new Thread(() -> {
    		double lastTime = 0;
    		
    		while(!isFinished && Robot.isEnabled) {
    			if(Timer.getFPGATimestamp() >= lastTime + 0.01) {
    				lastTime = Timer.getFPGATimestamp();
    				threadedExecute();
    			}
    			try {
    				Thread.sleep(2);
    			}catch(InterruptedException e) {
    				e.printStackTrace();
    			}
    		}
    	}).start();
    }
    
    protected synchronized void threadedExecute() {
    	if(i < motion.length) {
			double goalPos = motion[i][0];
			double goalVel = motion[i][1];
			double goalAcc = motion[i][2];
			
			double error = goalPos - elevator.getDistance();
			double errorDeriv = ((error - prevError) / Constants.kPeriod) - goalVel;
			
			double pwm = (elevator.kElevatorP * error) + (elevator.kElevatorD * errorDeriv) + (elevator.kV * goalVel) + (elevator.kA * goalAcc);
			
			System.out.println(goalPos + ", " + elevator.getDistance());
			
			prevError = error;
			
			elevator.setPercent(pwm);
			i++;
		}else {
			isFinished = true;
		}
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	elevator.setPercent(0);
    }

    protected void interrupted() {
    	isFinished = true;
    	end();
    }
}
