package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class DriveForTime extends CommandBase {
	private double driveSpeed;
	private double time;
	
    public DriveForTime(double driveSpeed, double time) {
        requires(drive);
        this.driveSpeed = driveSpeed;
        this.time = time;
    }

    protected void initialize() {
    	this.setTimeout(time);
    }

    protected void execute() {
    	drive.arcadeDrive(driveSpeed, 0, false);
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    protected void interrupted() {
    	end();
    }
}
