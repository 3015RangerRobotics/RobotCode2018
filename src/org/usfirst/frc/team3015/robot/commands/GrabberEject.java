package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberEject extends CommandBase {

    public GrabberEject() {
    	requires(grabber);
//    	requires(elevator);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(elevator.getDistance() < 10) {
    		grabber.reverseIntake();
    		grabber.openGrabber();
    	} else {
    		grabber.openGrabber();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	grabber.stopIntake();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
