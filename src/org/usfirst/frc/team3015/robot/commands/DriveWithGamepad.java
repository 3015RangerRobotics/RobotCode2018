  package org.usfirst.frc.team3015.robot.commands;

/**
 * Driver control with gamepad
 */
public class DriveWithGamepad extends CommandBase {
	private double maxControllerChange = 0.02;
	private double lastValue = 0;

    public DriveWithGamepad() {
    	requires(drive);
    }

    protected void initialize() {
    	drive.resetGyro();
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
	    
    	drive.arcadeDrive(driveValue, oi.getDriverLeftStickX()/1.25, true);
    	lastValue = driveValue;
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	drive.setMotorOutputs(0, 0);
    }

    protected void interrupted() {
    	end();
    }
}
