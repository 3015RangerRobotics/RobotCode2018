package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberTop extends CommandBase {

    public ClimberTop() {
        requires(climber);
    }

    protected void initialize() {

    }

    protected void execute() {
    	switch(climber.getLocation()) {
			case 0:
			case 1:
				climber.extendClimber();
				break;
			default:
				end();
				break;
    	}
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
