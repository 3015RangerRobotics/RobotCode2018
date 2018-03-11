package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;

/**
 * Follows a motion profile
 */
public class DriveMotionProfile extends CommandBase {
	private volatile boolean isFinished = false;
	private double[][] leftMotion;
	private double[][] rightMotion;
	private volatile int i =  0;
	private volatile double prevErrorL = 0;
	private volatile double prevErrorR = 0;

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
    
    public DriveMotionProfile(String filename, boolean reversed) {
    	requires(drive);
    	this.leftMotion = MotionProfiles.loadProfile(filename + "Left", reversed);
    	this.rightMotion = MotionProfiles.loadProfile(filename + "Right", reversed);
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
			double kP = drive.kDriveP;
			double kD = drive.kDriveD;
			double kV = drive.kV;
			double kA = drive.kA;
			
			double pwmL = (kP * errorL) + (kD * errorDerivL) + (kV * goalVelL) + (kA * goalAccL);
			double pwmR = (kP * errorR) + (kD * errorDerivR) + (kV * goalVelR) + (kA * goalAccR);
			
			System.out.println(goalPosL + ", " + goalPosR + ", " + drive.getLeftDistance() + ", " + drive.getRightDistance());
			
			prevErrorL = errorL;
			prevErrorR = errorR;
			
			drive.setMotorOutputs(pwmL, pwmR);
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
    	drive.setMotorOutputs(0, 0);
    }

    protected void interrupted() {
    	isFinished = true;
    	end();
    }
}
