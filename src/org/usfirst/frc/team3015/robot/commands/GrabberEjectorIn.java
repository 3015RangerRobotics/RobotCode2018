package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabberEjectorIn extends CommandBase {

    public GrabberEjectorIn() {
        requires(grabber);
    }

    protected void initialize() {
    	grabber.ejectorIn();
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
