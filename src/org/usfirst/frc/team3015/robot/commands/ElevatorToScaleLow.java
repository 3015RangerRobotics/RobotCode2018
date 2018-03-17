package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorToScaleLow extends CommandBase {

    public ElevatorToScaleLow() {
        requires(elevator);
    }

    protected void initialize() {
    		
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, elevator.elevatorHeightScaleLow * elevator.pulsesPerInch);
    	
    	if(elevator.getDistance() < 25) {
    		grabber.intakeOutSlowly();
    	}else {
    		grabber.intakeStop();
    	}
    }

    protected boolean isFinished() {
        return false;
    }
    
    protected void end() {
    	elevator.set(ControlMode.PercentOutput, 0);
    	grabber.intakeStop();
    }

    protected void interrupted()  {
    	end();
    }
}
