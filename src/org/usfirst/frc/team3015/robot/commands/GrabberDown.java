package org.usfirst.frc.team3015.robot.commands;

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
    		if(!grabber.isIntakeDown()) {
        		grabber.intakeDown();
        	}
      	}
    }

    protected boolean isFinished() {
    		return grabber.isIntakeDown() || (elevator.getDistance() > 10 && !override);
    }

    protected void end() {
    	grabber.anglerStop();
    }

    protected void interrupted() {
    	end();
    }
}
