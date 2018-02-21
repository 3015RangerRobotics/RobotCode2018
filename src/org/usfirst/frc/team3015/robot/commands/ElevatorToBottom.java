package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

/**
 *
 */
public class ElevatorToBottom extends CommandBase {

    public ElevatorToBottom() {
        requires(elevator);
    }

    protected void initialize() {
    	double d = Constants.elevatorHeightBottom - elevator.getDistance();
    	
    	if(d < 0) {
    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, -Constants.elevatorMaxV, -Constants.elevatorAcc, 60)).start();
    	}else {
    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, Constants.elevatorMaxV, Constants.elevatorAcc, 60)).start();
    	}
    	
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }
    
    protected void end() {
    }

    protected void interrupted()  {
    }
}
