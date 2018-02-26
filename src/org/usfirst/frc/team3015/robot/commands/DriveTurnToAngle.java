package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

/**
 * Turns to an angle using the imu
 */
public class DriveTurnToAngle extends CommandBase {
	private double[][] leftProfile;
	private double[][] rightProfile;
	
    public DriveTurnToAngle(double angle) {
        requires(drive);
        double arcLength = (Constants.wheelBaseWidth * Math.PI) * (angle / 360);
        arcLength *= 1.13;
        
        double[][] profile = MotionProfiles.generate1D(arcLength, 10, 7, 60, false);
        leftProfile = profile.clone();
        rightProfile = new double[profile.length][3];
        
        for(int i = 0; i < profile.length; i++) {
        	rightProfile[i][0] = -profile[i][0];
        	rightProfile[i][1] = -profile[i][1];
        	rightProfile[i][2] = -profile[i][2];
        }
    }

    protected void initialize() {
    	new DriveMotionProfile(leftProfile, rightProfile, true).start();
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
