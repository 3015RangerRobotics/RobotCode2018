package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberLockOut extends CommandBase {

    public ClimberLockOut() {
    	requires(climber);
    }

    protected void initialize() {
    	climber.climberLockOut();
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
