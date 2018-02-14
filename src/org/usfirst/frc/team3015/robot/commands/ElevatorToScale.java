package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

/**
 *
 */
public class ElevatorToScale extends CommandBase {

    public ElevatorToScale() {
        requires(elevator);
    }

    protected void initialize() {
    	double d = Constants.elevatorHeightScale - elevator.getDistance();
    	
    	if(d < 0) {
    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, -Constants.elevatorMaxV, -Constants.elevatorAcc, Constants.kPeriod)).start();
    	}else {
    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, Constants.elevatorMaxV, Constants.elevatorAcc, Constants.kPeriod)).start();
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
