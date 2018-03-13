package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 */
public class ClimberDown extends CommandBase {

    public ClimberDown() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		climber.retractClimber();
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
