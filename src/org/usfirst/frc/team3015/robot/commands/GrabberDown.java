package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

/**
 *
 */
public class GrabberDown extends CommandBase {
	private boolean override;

    public GrabberDown(boolean override) {
        requires(grabber);
        this.override = override;
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	if(elevator.getDistance() < 10 || override) {
    		if(!grabber.isAnglerDown()) {
        		grabber.intakeDown();
        	}
      	}
    }

    protected boolean isFinished() {
    		return grabber.isAnglerDown() || (elevator.getDistance() > 10 && !override);
    }

    protected void end() {
    	grabber.anglerStop();
    }

    protected void interrupted() {
    	end();
    }
}
