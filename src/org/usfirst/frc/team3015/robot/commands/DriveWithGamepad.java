  package org.usfirst.frc.team3015.robot.commands;

/**
 * Driver control with gamepad
 */
public class DriveWithGamepad extends CommandBase {
	private double maxControllerChange = 0.02;
	private double lastDriveValue = 0;
	private double lastTurnValue = 0;

    public DriveWithGamepad() {
    	requires(drive);
    }

    protected void initialize() {
//    	drive.resetGyro();
    	lastDriveValue = 0;
    	lastTurnValue = 0;
    }

    protected void execute() {
    	double driveValue = oi.getDriverLeftStickY();
    	double turnValue = oi.getDriverLeftStickX() / 1.25;
    	
    	if(elevator.getDistance() > 25) {
		    if(driveValue > lastDriveValue + maxControllerChange) {
		    	driveValue = lastDriveValue + maxControllerChange;
		    } else if(driveValue < lastDriveValue - maxControllerChange) {
	    		driveValue = lastDriveValue - maxControllerChange;
	    	}
		    
//		    if(turnValue > lastTurnValue + maxControllerChange) {
//		    	turnValue = lastTurnValue + maxControllerChange;
//		    } else if(turnValue < lastTurnValue - maxControllerChange) {
//		    	turnValue = lastTurnValue - maxControllerChange;
//	    	}
		    
		    if(Math.abs(oi.getDriverLeftStickY()) <= 0.1) {
		    	driveValue = oi.getDriverLeftStickY();
		    }
		    
//		    if(Math.abs(oi.getDriverLeftStickX()) <= 0.15) {
//		    	turnValue = oi.getDriverLeftStickX();
//		    }	
    	}
	    if(Math.abs(oi.getDriverRightStickX()) >= 0.1) {
	    	drive.curvatureDrive(driveValue, oi.getDriverRightStickX()/1.25, false);
	    }else {
	    	drive.arcadeDrive(driveValue, oi.getDriverLeftStickX()/1.25, true);
	    }
//    	drive.arcadeDrive(driveValue, oi.getDriverLeftStickX()/1.25, true);
    	lastDriveValue = driveValue;
    	lastTurnValue = turnValue;
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
