package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

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
