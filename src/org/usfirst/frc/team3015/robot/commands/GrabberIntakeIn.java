package org.usfirst.frc.team3015.robot.commands;

public class GrabberIntakeIn extends CommandBase {

    public GrabberIntakeIn() {
        requires(grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	grabber.intakeIn();
    }

    protected boolean isFinished() {
   		return false;
    }

    protected void end() {
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
