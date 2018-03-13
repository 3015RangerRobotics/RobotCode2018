package org.usfirst.frc.team3015.robot.commands;

public class GrabberCloseIfCube extends CommandBase {

    public GrabberCloseIfCube() {
        requires(grabber);
    }

    protected void initialize() {
    	if(grabber.isCubePresent()) {
    		grabber.closeGrabber();
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
