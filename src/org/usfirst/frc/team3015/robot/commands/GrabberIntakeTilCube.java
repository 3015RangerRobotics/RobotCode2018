package org.usfirst.frc.team3015.robot.commands;

/**
 *
 */
public class GrabberIntakeTilCube extends CommandBase {
	private int cubeCount = 0;
	
    public GrabberIntakeTilCube() {
        requires(grabber);
    }

    protected void initialize() {
    	cubeCount = 0;
    }

    protected void execute() {
    	if(!grabber.isCubePresent()) {
    		grabber.intakeIn();
    		cubeCount = 0;
    	}else {
    		grabber.intakeIn();
    		cubeCount++;
    	}
    }

    protected boolean isFinished() {
        return cubeCount >= 10;
    }

    protected void end() {
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
