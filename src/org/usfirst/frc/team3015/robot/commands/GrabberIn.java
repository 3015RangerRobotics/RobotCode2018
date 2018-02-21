package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 * Starts the intake to bring cubes in, stops when cube is present
 */
public class GrabberIn extends CommandBase {
	boolean isFinished = false;
	
	public GrabberIn() {
    	requires(grabber);
    }

    protected void initialize() {
    	grabber.openGrabber();
    }

    protected void execute() {
//    	if(!grabber.isCubePresent()) {
//    		if(grabber.isAnglerDown()) {
//    			grabber.anglerStop();
//    			grabber.openGrabber();
//    			grabber.setEjector(DoubleSolenoid.Value.kReverse);
//    			grabber.intakeIn();
//    		}else {
//    			grabber.intakeDown();
//    		}
//    	}else {
//    		grabber.intakeStop();
//    		if(grabber.isAnglerUp()) {
//    			grabber.anglerStop();
//    			grabber.closeGrabber();
//    			grabber.setEjector(DoubleSolenoid.Value.kReverse);
//    			isFinished = true;
//    		}else {
//    			grabber.intakeUp();
//    		}
//    	}
    }

    protected boolean isFinished() {
        return isFinished;
    }

    protected void end() {
    	grabber.anglerStop();
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
