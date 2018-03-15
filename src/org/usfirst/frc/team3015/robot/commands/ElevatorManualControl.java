package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class ElevatorManualControl extends CommandBase {
	double position = 0;

    public ElevatorManualControl() {
        requires(elevator);
    }

    protected void initialize() {
    	this.position = elevator.getRawDistance();
    }

    protected void execute() {
    	if((!elevator.isAtBottom() || oi.getCoDriverSumTriggers() > 0) || elevator.getDistance() > 65) {
    		this.position += oi.getCoDriverSumTriggers() * 100;
    	}
    	elevator.set(ControlMode.Position, position);
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
