package org.usfirst.frc.team3015.robot.commands;

public class GrabberClose extends CommandBase {

    public GrabberClose() {
        requires(grabber);
    }

    protected void initialize() {
    	grabber.closeGrabber();
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
