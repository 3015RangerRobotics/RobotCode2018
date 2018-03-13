package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ClimberLockOut extends CommandBase {

    public ClimberLockOut() {
    	requires(climber);
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		climber.climberLockOut();
    	}
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
