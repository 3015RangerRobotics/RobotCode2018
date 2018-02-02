package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class BuddyExtend extends CommandBase {

    public BuddyExtend() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	buddyClimb.buddyExtend();
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
