package org.usfirst.frc.team3015.robot.commands;

/**
 * Opens the grabber, runs once
 */
public class GrabberOpen extends CommandBase {

    public GrabberOpen() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.openGrabber();
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
