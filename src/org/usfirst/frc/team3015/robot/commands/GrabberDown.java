package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberDown extends CommandBase {

    public GrabberDown() {
        requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(elevator.getDistance() < 10) {
    		if(!grabber.isAnglerDown()) {
        		grabber.intakeDown();
        	}
      	}
    }

    protected boolean isFinished() {
    		return grabber.isAnglerDown() || elevator.getDistance() > 10;
    }

    protected void end() {
    	grabber.anglerStop();
    }

    protected void interrupted() {
    	
    }
}
