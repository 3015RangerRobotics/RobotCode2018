package org.usfirst.frc.team3015.robot.commands;

/**
 * Stops the intake
 */
public class IntakeStop extends CommandBase {

    public IntakeStop() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.stopIntake();
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
