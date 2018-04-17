package org.usfirst.frc.team3015.robot.commands;

public class DriveWithGamepad extends CommandBase {
	private double maxControllerChange = 0.02;
	private double lastDriveValue = 0;

    public DriveWithGamepad() {
    	requires(drive);
    }

    protected void initialize() {
    	lastDriveValue = 0;
    }

    protected void execute() {
    	double driveValue = oi.getDriverLeftStickY() * 0.9;
    	
    	if(elevator.getDistance() > 25) {
		    if(driveValue > lastDriveValue + maxControllerChange) {
		    	driveValue = lastDriveValue + maxControllerChange;
		    } else if(driveValue < lastDriveValue - maxControllerChange) {
	    		driveValue = lastDriveValue - maxControllerChange;
	    	}
//		    driveValue *= 0.7;
		    if(Math.abs(oi.getDriverLeftStickY()) <= 0.1) {
		    	driveValue = oi.getDriverLeftStickY();
		    }	
    	}
    	
	    if(Math.abs(oi.getDriverRightStickX()) >= 0.1) {
	    	drive.curvatureDrive(driveValue, oi.getDriverRightStickX()/1.25, false, true);
	    }else {
	    	drive.arcadeDrive(driveValue, oi.getDriverLeftStickX()/1.25, true);
	    }
	    
    	lastDriveValue = driveValue;
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
