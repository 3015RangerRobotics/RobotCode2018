package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberBottom extends CommandBase {

	int pos;
	
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
				end();
				break;
    	}
    	if(climber.getLocation() == 0) {
    		end();
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
