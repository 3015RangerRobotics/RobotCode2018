package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberUp extends CommandBase {

    public GrabberUp() {
        requires(grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
        if(!grabber.isAnglerUp()) {
       		grabber.intakeUp();
    	}
    }

    protected boolean isFinished() {
        return grabber.isAnglerUp();
    }

    protected void end() {
    	grabber.anglerStop();
    }

    protected void interrupted() {
    	end();
    }
}
