package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class ManhattanRetract extends CommandBase {

    public ManhattanRetract() {
    	requires(manhattan);
    }
    
    protected void initialize() {
    	manhattan.retract();
    	manhattan.rollerStop();
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
