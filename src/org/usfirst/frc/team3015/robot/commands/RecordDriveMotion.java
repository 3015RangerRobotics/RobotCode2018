package org.usfirst.frc.team3015.robot.commands;

import java.util.ArrayList;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class RecordDriveMotion extends CommandBase {
	private volatile ArrayList<double[]> leftMotion;
	private volatile ArrayList<double[]> rightMotion;
	private volatile int pointIndex = 0;
	private volatile boolean isFinished = false;

    public RecordDriveMotion() {
    	
    }

    protected void initialize() {
    	drive.resetEncoders();
    	leftMotion.clear();
    	rightMotion.clear();
    	this.pointIndex = 0;
    	this.isFinished = false;
    	
    	new Thread(() -> {
    		double lastTime = 0;
    		
    		while(!isFinished && DriverStation.getInstance().isEnabled()) {
	    		if(Timer.getFPGATimestamp() >= lastTime + Constants.kPeriod) {
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
    	if(pointIndex == 0) {
    		leftMotion.add(pointIndex, new double[] {0, 0, 0});
    		rightMotion.add(pointIndex, new double[] {0, 0, 0});
    	}else {
    		double leftPos = drive.getLeftDistance();
    		double leftVel = drive.getLeftVelocity();
    		double leftAccel = (leftVel - leftMotion.get(pointIndex - 1)[2]) / Constants.kPeriod;
    		
    		double rightPos = drive.getRightDistance();
    		double rightVel = drive.getRightVelocity();
    		double rightAccel = (rightVel - rightMotion.get(pointIndex - 1)[2]) / Constants.kPeriod;
    		
    		leftMotion.add(pointIndex, new double[] {leftPos, leftVel, leftAccel});
    		rightMotion.add(pointIndex, new double[] {rightPos, rightVel, rightAccel});
    	}
    	pointIndex++;
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	isFinished = true;
    	System.out.println("Left:");
    	for(double[] point : leftMotion) {
    		System.out.println(point[0] + "," + point[1] + "," + point[2]);
    	}
    	System.out.println("Right:");
    	for(double[] point : rightMotion) {
    		System.out.println(point[0] + "," + point[1] + "," + point[2]);
    	}
    }

    protected void interrupted() {
    	end();
    }
}
