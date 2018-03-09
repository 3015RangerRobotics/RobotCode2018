package org.usfirst.frc.team3015.robot.commands;

public class DriverStopVibration extends CommandBase {

    public DriverStopVibration() {
        
    }

    protected void initialize() {
    	oi.driverRumble(0);
    }

    protected void execute() {
    	
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	
    }

    protected void interrupted() {
    	
    }
}
