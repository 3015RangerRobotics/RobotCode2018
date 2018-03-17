package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorToBottom extends CommandBase {

    public ElevatorToBottom() {
        requires(elevator);
    }

    protected void initialize() {
    	
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, elevator.elevatorHeightBottom);
    }

    protected boolean isFinished() {
        return elevator.isAtBottom() && elevator.getDistance() < 40;
    }
    
    protected void end() {
    	elevator.set(ControlMode.PercentOutput, 0);
    }

    protected void interrupted()  {
    	end();
    }
}
