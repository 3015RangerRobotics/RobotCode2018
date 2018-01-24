package org.usfirst.frc.team3015.robot.commands;

/**
 * Starts the intake to shoot out cubes
 */
public class IntakeOut extends CommandBase {

    public IntakeOut() {
    	requires(intake);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	intake.reverseIntake();
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
