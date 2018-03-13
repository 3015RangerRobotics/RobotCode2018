package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class BuddyRetract extends CommandBase {

    public BuddyRetract() {
        requires(buddyClimb);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		buddyClimb.buddyRetract();
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
