package org.usfirst.frc.team3015.robot.commands;

public class GrabberOpen extends CommandBase {

    public GrabberOpen() {
        requires(grabber);
    }

    protected void initialize() {
    	grabber.openGrabber();
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
