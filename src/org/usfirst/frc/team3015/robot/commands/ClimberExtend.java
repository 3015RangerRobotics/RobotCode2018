package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ClimberExtend extends CommandBase {

    public ClimberExtend() {
        requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
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
