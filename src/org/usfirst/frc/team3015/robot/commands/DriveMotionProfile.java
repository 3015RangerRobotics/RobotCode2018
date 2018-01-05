package org.usfirst.frc.team3015.robot.commands;

import java.util.Timer;
import java.util.TimerTask;

public class DriveMotionProfile extends CommandBase {
	private boolean isFinished = false;
	private final double period = 0.01;
	private double[][] leftMotion;
	private double[][] rightMotion;

    public DriveMotionProfile(double[][] motionProfile) {
    	requires(drive);
    	this.leftMotion = motionProfile;
    	this.rightMotion = motionProfile;
    }
    
    public DriveMotionProfile(double[][] leftMotion, double[][] rightMotion) {
    	requires(drive);
    	this.leftMotion = leftMotion;
    	this.rightMotion = rightMotion;
    }

    protected void initialize() {
    	isFinished = false;
    	if(leftMotion.length != rightMotion.length) {
    		System.out.println("Left and right profiles not of equal length!");
    		this.cancel();
    		return;
    	}
    	Timer controlLoop = new Timer();
    	controlLoop.schedule(pidLoop, 0, (long) period * 1000);//Runs the loop every 10ms
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	drive.tankDrive(0, 0);
    }

    protected void interrupted() {
    	end();
    }
    
    TimerTask pidLoop = new TimerTask() {
		int i =  0;
		double prevErrorL = 0;
		double prevErrorR = 0;
		
		@Override
		public void run() {
			if(i < leftMotion.length) {
				double goalPosL = leftMotion[i][0];
				double goalVelL = leftMotion[i][1];
				double goalAccL = leftMotion[i][2];
				
				double goalPosR = rightMotion[i][0];
				double goalVelR = rightMotion[i][1];
				double goalAccR = rightMotion[i][2];
				
				double errorL = goalPosL - drive.getLeftDistance();
				double errorDerivL = ((errorL - prevErrorL) / period) - goalVelL;
				
				double errorR = goalPosR - drive.getRightDistance();
				double errorDerivR = ((errorR - prevErrorR) / period) - goalVelR;
				
				double pwmL = (drive.kP * errorL) + (drive.kD * errorDerivL) + (drive.kV * goalVelL) + (drive.kA * goalAccL);
				double pwmR = (drive.kP * errorR) + (drive.kD * errorDerivR) + (drive.kV * goalVelR) + (drive.kA * goalAccR);
				
				prevErrorL = errorL;
				prevErrorR = errorR;
				
				drive.tankDrive(pwmL, pwmR);
				i++;
			}else {
				isFinished = true;
				prevErrorL = 0;
				prevErrorR = 0;
				i = 0;
				pidLoop.cancel();
			}
		}
	};
}
