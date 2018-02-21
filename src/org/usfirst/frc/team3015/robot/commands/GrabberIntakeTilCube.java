package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberIntakeTilCube extends CommandBase {

    public GrabberIntakeTilCube() {
        requires(grabber);
    }

    protected void initialize() {
    }

    protected void execute() {
    	if(!grabber.isCubePresent()) {
    		grabber.intakeIn();
    	}
    }

    protected boolean isFinished() {
        return grabber.isCubePresent() || oi.getDriverLB();
    }

    protected void end() {
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
