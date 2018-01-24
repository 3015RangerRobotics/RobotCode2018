package org.usfirst.frc.team3015.robot.commands;

/**
 * Starts the intake to bring cubes in
 */
public class IntakeIn extends CommandBase {

	public IntakeIn() {
    	requires(intake);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	intake.intake();
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
