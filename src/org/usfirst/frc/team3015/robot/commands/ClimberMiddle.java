package org.usfirst.frc.team3015.robot.commands;


/**
 *
 */
public class ClimberMiddle extends CommandBase {
	
	int pos;
	boolean isFinished = false;

    public ClimberMiddle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	pos = climber.getLocation();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(pos) {
    		case 0:
    			climber.extendClimber();
    			break;
    		case 2:
    		case -1:
    			climber.retractClimber();
    			break;
			default:
				climber.stopClimber();
    			isFinished = true;
    			break;
    	}
    	if(climber.getLocation() == 1) {
    		climber.stopClimber();
    		isFinished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
