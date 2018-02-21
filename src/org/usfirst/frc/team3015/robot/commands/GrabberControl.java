package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DoubleSolenoid;

/**
 *
 */
public class GrabberControl extends CommandBase {
	private boolean lastAValue = false;

    public GrabberControl() {
        requires(grabber);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
//    	if(oi.getDriverAButton()) {
//    		if(oi.getDriverLB()) {
//    			grabber.openGrabber();
//				grabber.setEjector(DoubleSolenoid.Value.kForward);
//				grabber.intakeOut();
//    		}else {
//    			if(!grabber.isCubePresent()) {
//        			grabber.openGrabber();
//        			grabber.setEjector(DoubleSolenoid.Value.kReverse);
//        			
//        			if(grabber.isAnglerDown()) {
//        				grabber.anglerStop();
//        				grabber.intakeIn();
//        			}else {
//        				grabber.intakeDown();
//        			}
//        		}else {
//        			grabber.closeGrabber();
//        			grabber.intakeStop();
//        			grabber.intakeUp();
//        		}
//    		}
//    	}else if(oi.getDriverAButton() != lastAValue){
//    		if(grabber.isCubePresent()) {
//    			new ElevatorToSwitch().start();
//    		}else {
////    			new GrabberReset().start();
//    		}
//    	}
//    	lastAValue = oi.getDriverAButton();
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	grabber.anglerStop();
    	grabber.intakeStop();
    }

    protected void interrupted() {
    	end();
    }
}
