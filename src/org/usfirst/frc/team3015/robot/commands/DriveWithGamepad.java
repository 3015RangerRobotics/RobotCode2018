package org.usfirst.frc.team3015.robot.commands;

/**
 * Driver control with gamepad
 */
public class DriveWithGamepad extends CommandBase {
	private double maxControllerChange = 0.01;
	private double lastValue = 0;

    public DriveWithGamepad() {
    	requires(drive);
    }

    protected void initialize() {
    }

    protected void execute() {
    	double driveValue = oi.getDriverLeftStickY();
    	
	    if(driveValue > lastValue + maxControllerChange) {
	    	driveValue = lastValue + maxControllerChange;
	    } else if(driveValue < lastValue - maxControllerChange) {
    		driveValue = lastValue - maxControllerChange;
    	}
	    
	    if(Math.abs(oi.getDriverLeftStickY()) <= 0.1) {
	    	driveValue = oi.getDriverLeftStickY();
	    }
	    
    	drive.arcadeDrive(driveValue, oi.getDriverRightStickX()/1.5, true);
    	lastValue = driveValue;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    }

    protected void interrupted() {
    	end();
    }
}
