package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ManhattanRun extends CommandBase {

    public ManhattanRun() {
    	requires(manhattan);
    }

    protected void initialize() {
    	manhattan.extend();
    }

    protected void execute() {
    	manhattan.rollerUp();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	manhattan.retract();
    	manhattan.rollerStop();
    }

    protected void interrupted() {
    	end();
    }
}
