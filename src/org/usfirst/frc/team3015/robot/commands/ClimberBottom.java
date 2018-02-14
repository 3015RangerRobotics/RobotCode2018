package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberBottom extends CommandBase {

	int pos;
	boolean isFinished = false;
	
    public ClimberBottom() {
    	requires(climber);
    }

    protected void initialize() {
    	pos = climber.getLocation();
    }

    protected void execute() {
    	switch(pos) {
			case 1:
			case 2:
			case -1:
				climber.retractClimber();
				break;
			default:
				climber.stopClimber();
				isFinished = true;
				break;
    	}
    	if(climber.getLocation() == 0) {
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
    	end();
    }
}
