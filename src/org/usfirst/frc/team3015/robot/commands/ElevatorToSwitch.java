package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class ElevatorToSwitch extends CommandBase {

    public ElevatorToSwitch() {
        requires(elevator);
    }

    protected void initialize() {
//    	double d = Constants.elevatorHeightSwitch - elevator.getDistance();
//    	System.out.println(d);
//    	
//    	if(d < 0) {
//    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, -Constants.elevatorMaxV, -Constants.elevatorAcc, -60)).start();
//    	}else {
//    		new ElevatorMotionProfile(MotionProfiles.generate1D(d, Constants.elevatorMaxV, Constants.elevatorAcc, 60)).start();
//    	}
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, Constants.elevatorHeightSwitch * elevator.pulsesPerInch);
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	elevator.set(ControlMode.PercentOutput, 0);
    	
    }

    protected void interrupted()  {
    	end();
    }
}
