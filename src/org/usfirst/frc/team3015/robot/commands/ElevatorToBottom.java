package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorToBottom extends CommandBase {
	private double elevatorAtBottomCount = 0;

    public ElevatorToBottom() {
        requires(elevator);
    }

    protected void initialize() {
    	this.elevatorAtBottomCount = 0;
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, elevator.elevatorHeightBottom);
    	if(elevator.isAtBottom()) {
    		this.elevatorAtBottomCount++;
    	}else {
    		this.elevatorAtBottomCount = 0;
    	}
    }

    protected boolean isFinished() {
        return this.elevatorAtBottomCount >= 10 && elevator.getDistance() < 10;
    }
    
    protected void end() {
    	elevator.set(ControlMode.PercentOutput, 0);
    }

    protected void interrupted()  {
    	end();
    }
}
