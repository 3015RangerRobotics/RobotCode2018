package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class BuddyExtend extends CommandBase {

    public BuddyExtend() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	buddyClimb.buddyExtend();
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
