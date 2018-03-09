package org.usfirst.frc.team3015.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj.Timer;

public class ElevatorToScale extends CommandBase {
	private double startTime;

    public ElevatorToScale() {
        requires(elevator);
    }

    protected void initialize() {
    	startTime = Timer.getFPGATimestamp();  	
    }

    protected void execute() {
    	elevator.set(ControlMode.Position, elevator.elevatorHeightScale * elevator.pulsesPerInch);
    	
    	if(Timer.getFPGATimestamp() - startTime < 1) {
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
