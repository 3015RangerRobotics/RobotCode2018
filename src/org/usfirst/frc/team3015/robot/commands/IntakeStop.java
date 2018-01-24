package org.usfirst.frc.team3015.robot.commands;

/**
 * Stops the intake
 */
public class IntakeStop extends CommandBase {

    public IntakeStop() {
    	requires(intake);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	intake.stopIntake();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	intake.stopIntake();
    }

    protected void interrupted() {
    	end();
    }
}
