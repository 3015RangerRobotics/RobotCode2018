package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberRetract extends CommandBase {

    public ClimberRetract() {
    	requires(climber);
    }

    protected void initialize() {
    	climber.retractClimber();
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
