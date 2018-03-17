package org.usfirst.frc.team3015.robot.commands;

public class GrabberDown extends CommandBase {

    public GrabberDown() {
        requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(!grabber.isIntakeDown()) {
        	grabber.intakeDown();
        }
    }

    protected boolean isFinished() {
    	return grabber.isIntakeDown();
    }

    protected void end() {
    	grabber.intakeAnglerStop();
    }

    protected void interrupted() {
    	end();
    }
}
