package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class BuddyExtend extends CommandBase {

    public BuddyExtend() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
        	buddyClimb.buddyExtend();	
    	}
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
