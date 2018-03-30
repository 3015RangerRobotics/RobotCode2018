package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GrabberIntakeOutForTime extends CommandBase {
	private double time;
    public GrabberIntakeOutForTime(double time) {
        requires(grabber);
        this.time = time;
    }
    protected void initialize() {
    	this.setTimeout(time);
    }
    protected void execute() {
    	grabber.intakeOut();
    }
    protected boolean isFinished() {
        return isTimedOut();
    }
    protected void end() {
    	grabber.intakeStop();
    }
    protected void interrupted() {
    	end();
    }
}
