package org.usfirst.frc.team3015.robot.commands;

import java.util.HashMap;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.motionProfiles.MotionProfiles.Side;
import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

public class DriveToCube extends CommandBase{
	private volatile boolean isFinished = false;
	private double[][] leftMotion = null;
	private double[][] rightMotion = null;
	private volatile int i =  0;
	private volatile double prevErrorL = 0;
	private volatile double prevErrorR = 0;
	private double fallbackDistance;
	private volatile double startTime;

    public DriveToCube(double fallbackDistance) {
    	requires(drive);
    	this.fallbackDistance = fallbackDistance;
    }
    
    protected void initialize() {
    	drive.resetEncoders();
    	isFinished = false;
    	i = 0;
    	prevErrorL = 0;
    	prevErrorR = 0;
    	startTime = Timer.getFPGATimestamp();
    	
//    	if(leftMotion.length != rightMotion.length) {
//    		System.out.println("Left and right profiles not of equal length!");
//    		this.cancel();
//    		return;
//    	}
    	
    	new Thread(() -> {
    		double lastTime = 0;
    		
    		while(!isFinished && DriverStation.getInstance().isEnabled()) {
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
    	if(leftMotion == null) {
    		if(drive.bestTarget != null) {
    			double distanceFromCamera = drive.bestTarget.getDistance();
    			double xAngleFromCamera = drive.bestTarget.getYAngle();//vertical phone = swap angles
    			
    			double a = Math.sqrt(((distanceFromCamera*distanceFromCamera) + (Constants.cameraXOffsetFt*Constants.cameraXOffsetFt)) - 
            			(2 * distanceFromCamera * Constants.cameraXOffsetFt * Math.cos(Math.toRadians(90 - xAngleFromCamera))));
            	double correctedDistance = Math.sqrt((a*a) - (Constants.cameraYOffsetFt*Constants.cameraYOffsetFt));
            	double cubeAngle = Math.toDegrees(Math.asin(Math.toRadians((Math.sin((90 - xAngleFromCamera)) / a) * Constants.cameraXOffsetFt)));
            	double correctedXAngle = (180 - cubeAngle - (90 - xAngleFromCamera)) - 90;
            	
            	HashMap<Side, double[][]> profile = MotionProfiles.generateProfileToCube(correctedXAngle, correctedDistance, 14, 10, 60);
            	leftMotion = profile.get(Side.kLeft);
            	rightMotion = profile.get(Side.kRight);
        	}else {
        		if(Timer.getFPGATimestamp() - startTime < 1) {
        			return;
        		}else {
        			double[][] profile = MotionProfiles.generate1D(fallbackDistance, 14, 10, 60, false);
        			leftMotion = profile;
        			rightMotion = profile;
        		}
        	}
    	}else {
	    	if(i < leftMotion.length) {
				double goalPosL = leftMotion[i][0];
				double goalVelL = leftMotion[i][1];
				double goalAccL = leftMotion[i][2];
				
				double goalPosR = rightMotion[i][0];
				double goalVelR = rightMotion[i][1];
				double goalAccR = rightMotion[i][2];
				
				double errorL = goalPosL - drive.getLeftDistance();
				double errorDerivL = ((errorL - prevErrorL) / Constants.kPeriod) - goalVelL;
				
				double errorR = goalPosR - drive.getRightDistance();
				double errorDerivR = ((errorR - prevErrorR) / Constants.kPeriod) - goalVelR;
				
				double kP = drive.kDriveP;
				double kD = drive.kDriveD;
				double kV = drive.kV;
				double kA = drive.kA;
				
				double pwmL = (kP * errorL) + (kD * errorDerivL) + (kV * goalVelL) + (kA * goalAccL);
				double pwmR = (kP * errorR) + (kD * errorDerivR) + (kV * goalVelR) + (kA * goalAccR);
				
	//			System.out.println(goalPosL + ", " + goalPosR + ", " + drive.getLeftDistance() + ", " + drive.getRightDistance());
				
				prevErrorL = errorL;
				prevErrorR = errorR;
				
				drive.setMotorOutputs(pwmL, pwmR);
				i++;
			}else {
				isFinished = true;
			}
    	}
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	drive.setMotorOutputs(0, 0);
    }

    protected void interrupted() {
    	isFinished = true;
    	end();
    }
}
