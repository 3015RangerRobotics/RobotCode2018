package org.usfirst.frc.team3015.robot.commands;

public class ClimberUp extends CommandBase {

    public ClimberUp() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	climber.climbUp();
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
