package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorToScale extends CommandBase {

    public ElevatorToScale() {
        requires(elevator);
    }

    protected void initialize() {
    		
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, elevator.elevatorHeightScale * elevator.pulsesPerInch);
    	
    	if(elevator.getDistance() < 23) {
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
