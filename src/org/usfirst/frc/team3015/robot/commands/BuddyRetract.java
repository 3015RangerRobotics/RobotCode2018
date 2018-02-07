package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class BuddyRetract extends CommandBase {

    public BuddyRetract() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	buddyClimb.buddyRetract();
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