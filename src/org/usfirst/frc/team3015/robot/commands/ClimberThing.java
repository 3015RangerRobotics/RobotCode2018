package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberThing extends CommandBase {

    public ClimberThing() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	climber.climberLockOut();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.climberLockStop();
    }

    protected void interrupted() {
    	end();
    }
}
