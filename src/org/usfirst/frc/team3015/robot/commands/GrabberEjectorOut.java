package org.usfirst.frc.team3015.robot.commands;

public class GrabberEjectorOut extends CommandBase {

    public GrabberEjectorOut() {
        requires(grabber);
    }

    protected void initialize() {
    	if(elevator.getDistance() > 5) {
    		grabber.ejectorOut();
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
