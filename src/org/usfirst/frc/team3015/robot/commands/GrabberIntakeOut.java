package org.usfirst.frc.team3015.robot.commands;

public class GrabberIntakeOut extends CommandBase {

    public GrabberIntakeOut() {
        requires(grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	grabber.intakeOut();
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
