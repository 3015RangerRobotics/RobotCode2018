package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberDown extends CommandBase {

    public ClimberDown() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	climber.retractClimber();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.stopClimber();
    }

    protected void interrupted() {
    	end();
    }
}
