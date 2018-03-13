package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ClimberUp extends CommandBase {

    public ClimberUp() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		climber.climbUp();
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	climber.stopClimber();
    }

    protected void interrupted() {
    	end();
    }
}
