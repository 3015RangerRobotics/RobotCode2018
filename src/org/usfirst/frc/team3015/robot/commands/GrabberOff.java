package org.usfirst.frc.team3015.robot.commands;

/**
 * Sets the grabber to off, might not need this, runs once
 */
public class GrabberOff extends CommandBase {

    public GrabberOff() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.offGrabber();
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
