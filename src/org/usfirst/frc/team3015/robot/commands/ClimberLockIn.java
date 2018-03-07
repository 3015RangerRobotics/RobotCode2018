package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberLockIn extends CommandBase {

    public ClimberLockIn() {
    	requires(climber);
    }

    protected void initialize() {
    	climber.climberLockIn();
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
