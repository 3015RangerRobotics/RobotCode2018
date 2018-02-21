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
        
        leftProfile = MotionProfiles.generate1D(arcLength, 10, 7, 60);
        rightProfile = MotionProfiles.generate1D(-arcLength, -10, -7, -60);
    }

    protected void initialize() {
    	new DriveMotionProfile(leftProfile, rightProfile).start();
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
