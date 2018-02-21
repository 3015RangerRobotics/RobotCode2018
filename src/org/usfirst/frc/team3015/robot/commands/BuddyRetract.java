package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class BuddyRetract extends CommandBase {

    public BuddyRetract() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	buddyClimb.buddyRetract();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    	end();
    }
}
