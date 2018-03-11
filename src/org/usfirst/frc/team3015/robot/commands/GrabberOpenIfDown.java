package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberOpenIfDown extends CommandBase {

    public GrabberOpenIfDown() {
        requires(grabber);
    }

    protected void initialize() {
    	if(elevator.getDistance() < 5) {
    		grabber.openGrabber();
    	}
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
