package org.usfirst.frc.team3015.robot.commands;


/**
 *
 */
public class ClimberMiddle extends CommandBase {

    public ClimberMiddle() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(climber);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(climber.getLocation()) {
    		case 0:
    			climber.extendClimber();
    			break;
    		case 2:
    			climber.retractClimber();
    			break;
			default:
    			end();
    			break;
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
