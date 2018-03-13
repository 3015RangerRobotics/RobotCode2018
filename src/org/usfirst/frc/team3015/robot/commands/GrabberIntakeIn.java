package org.usfirst.frc.team3015.robot.commands;

public class GrabberIntakeIn extends CommandBase {

    public GrabberIntakeIn() {
        requires(grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(elevator.getDistance() < 10) {
    		grabber.intakeIn();
    	}
    }

    protected boolean isFinished() {
   		return elevator.getDistance() > 10;
    }

    protected void end() {
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
