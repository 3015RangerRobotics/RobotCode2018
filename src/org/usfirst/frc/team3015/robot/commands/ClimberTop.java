package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberTop extends CommandBase {

	int pos;
	boolean isFinished = false;
	
    public ClimberTop() {
        requires(climber);
    }

    protected void initialize() {
    	pos = climber.getLocation();
    }

    protected void execute() {
    	switch(pos) {
			case 0:
			case 1:
			case -1:
				climber.extendClimber();
				break;
			default:
				climber.stopClimber();
				isFinished = true;
				break;
    	}
    	if(climber.getLocation() == 2) {
    		climber.stopClimber();
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    	isFinished = true;
    }
}
