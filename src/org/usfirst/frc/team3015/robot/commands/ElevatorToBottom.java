package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class ElevatorToBottom extends CommandBase {

    public ElevatorToBottom() {
        requires(elevator);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, Constants.elevatorHeightBottom);
    	System.out.println(elevator.getDistance());
    	if(elevator.elevatorTalonSRX.getMotorOutputVoltage() > 4){
    		System.err.println("ELEVATOR VOLTAGE OVER 4");
    	}
    }

    protected boolean isFinished() {
        return elevator.getDistance() < 3;
    }
    
    protected void end() {
    	elevator.setPercent(0);
    }

    protected void interrupted()  {
    	end();
    }
}
