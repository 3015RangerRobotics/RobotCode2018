package org.usfirst.frc.team3015.robot.commands;

/**
 * Starts the intake to bring cubes in, stops when cube is present
 */
public class IntakeInUntilCubePresent extends CommandBase {
	
	boolean isFinished = false;
	
	public IntakeInUntilCubePresent() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.intake();
    	
    	if(grabber.isCubePresent()) {
    		grabber.stopIntake();
    		grabber.closeGrabber();
    		isFinished = true;
    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	grabber.stopIntake();
    }

    protected void interrupted() {
    	end();
    }
}
