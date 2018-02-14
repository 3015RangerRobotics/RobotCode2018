package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberBottom extends CommandBase {

    public ClimberBottom() {
    	requires(climber);
    }

    protected void initialize() {

    }

    protected void execute() {
    	switch(climber.getLocation()) {
			case 1:
			case 2:
				climber.retractClimber();
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
