package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ClimberLockIn extends CommandBase {

    public ClimberLockIn() {
    	requires(climber);
    }

    protected void initialize() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		climber.climberLockIn();
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
