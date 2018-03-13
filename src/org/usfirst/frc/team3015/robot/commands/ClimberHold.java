package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;

public class ClimberHold extends CommandBase {

    public ClimberHold() {
    	requires(climber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(DriverStation.getInstance().getMatchTime() < 30) {
    		climber.climberHold();
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
