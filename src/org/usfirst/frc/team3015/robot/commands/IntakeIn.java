package org.usfirst.frc.team3015.robot.commands;

/**
 * Starts the intake to bring cubes in
 */
public class IntakeIn extends CommandBase {

	public IntakeIn() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.intake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	grabber.stopIntake();
    }

    protected void interrupted() {
    	end();
    }
}
