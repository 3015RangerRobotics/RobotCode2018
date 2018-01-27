package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.MotionProfiles;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveToCube extends CommandBase {

    public DriveToCube() {
        requires(drive);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	double[][] motionProfile = MotionProfiles.generate1D(drive.bestTarget.getDistance(), drive.maxVelocity, drive.maxAcceleration, 0.01);
    	Command command = new DriveMotionProfile(motionProfile);
    	command.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
