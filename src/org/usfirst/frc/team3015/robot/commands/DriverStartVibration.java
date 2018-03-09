package org.usfirst.frc.team3015.robot.commands;

public class DriverStartVibration extends CommandBase {

    public DriverStartVibration() {
        
    }

    protected void initialize() {
    	oi.driverRumble(1);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
