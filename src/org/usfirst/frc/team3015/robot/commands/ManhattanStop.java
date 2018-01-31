package org.usfirst.frc.team3015.robot.commands;

/**
 * 
 */
public class ManhattanStop extends CommandBase {

    public ManhattanStop() {
    	requires(manhattan);
    }

    protected void initialize() {
    	
    	
    }

    protected void execute() {
    	manhattan.rollerStop();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	end();
    }
}
