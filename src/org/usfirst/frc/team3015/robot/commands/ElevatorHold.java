package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

/**
 *
 */
public class ElevatorHold extends CommandBase {
	private double startPos;

    public ElevatorHold() {
        requires(elevator);
    }

    protected void initialize() {
    	startPos = elevator.getDistance();
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, startPos);
    	
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	elevator.set(ControlMode.PercentOutput, 0);
    }

    protected void interrupted() {
    	end();
    }
}
