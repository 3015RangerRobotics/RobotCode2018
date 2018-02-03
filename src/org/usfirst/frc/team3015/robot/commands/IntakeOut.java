package org.usfirst.frc.team3015.robot.commands;

/**
 * Starts the intake to shoot out cubes
 */
public class IntakeOut extends CommandBase {

    public IntakeOut() {
    	requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	grabber.reverseIntake();
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
