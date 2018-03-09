package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 * Turns to an angle using the imu
 */
public class DriveTurnToAngle extends CommandBase {
	private double[][] leftMotion;
	private double[][] rightMotion;
	private boolean isFinished = false;
	private int i = 0;
	private double prevErrorL = 0;
	private double prevErrorR = 0;
	
    public DriveTurnToAngle(double angle, boolean reverseDirection) {
        requires(drive);
        double arcLength = (Constants.wheelBaseWidth * Math.PI) * (angle / 360);
        arcLength *= 1.13;
        
        double[][] profile = MotionProfiles.generate1D(arcLength, 14, 12, 60, false);
        leftMotion = new double[profile.length][3];
        rightMotion = new double[profile.length][3];
        
        for(int i = 0; i < profile.length; i++) {
        	if(!reverseDirection) {
	        	rightMotion[i][0] = -profile[i][0];
	        	rightMotion[i][1] = -profile[i][1];
	        	rightMotion[i][2] = -profile[i][2];
	        	leftMotion[i][0] = profile[i][0];
	        	leftMotion[i][1] = profile[i][1];
	        	leftMotion[i][2] = profile[i][2];
        	}else {
        		rightMotion[i][0] = profile[i][0];
	        	rightMotion[i][1] = profile[i][1];
	        	rightMotion[i][2] = profile[i][2];
	        	leftMotion[i][0] = -profile[i][0];
	        	leftMotion[i][1] = -profile[i][1];
	        	leftMotion[i][2] = -profile[i][2];
        	}
        }
    }

    protected void initialize() {
    	drive.resetEncoders();
    	isFinished = false;
    	i = 0;
    	prevErrorL = 0;
    	prevErrorR = 0;
    	
    	if(leftMotion.length != rightMotion.length) {
    		System.out.println("Left and right profiles not of equal length!");
    		this.cancel();
    		return;
    	}
    	
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

    protected void execute() {
    	
    }
    
    protected synchronized void threadedExecute() {
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
			
//			System.out.println(errorL + ", " + errorR);
			double kP = drive.kTurnP;
			double kD = drive.kTurnD;
			double kV = drive.kV + drive.kTurnV;
			
			double pwmL = (kP * errorL) + (kD * errorDerivL) + (kV * goalVelL) + (drive.kA * goalAccL);
			double pwmR = (kP * errorR) + (kD * errorDerivR) + (kV * goalVelR) + (drive.kA * goalAccR);
			
//			System.out.println(goalPosL + ", " + goalPosR + ", " + drive.getLeftDistance() + ", " + drive.getRightDistance());
			
			prevErrorL = errorL;
			prevErrorR = errorR;
			
			drive.setMotorOutputs(pwmL, pwmR);
			i++;
		}else {
			isFinished = true;
		}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	drive.setMotorOutputs(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
