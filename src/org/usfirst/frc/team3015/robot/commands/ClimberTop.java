package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberTop extends CommandBase {

	int pos;
	
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
				end();
				break;
    	}
    	if(climber.getLocation() == 2) {
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
