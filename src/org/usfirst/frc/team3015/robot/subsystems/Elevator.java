package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.ElevatorManualControl;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	TalonSRX elevatorTalonSRX;
	AnalogPotentiometer elevatorPotentiometer;
	
	public final double kElevatorP = 0.0;
	public final double kElevatorI = 0.0;
	public final double kElevatorD = 0.0;
	public final double kV = 0.0;
    public final double kA = 0.0;
    
    public void initDefaultCommand() {
    	this.setDefaultCommand(new ElevatorManualControl());
    }
    
    public Elevator() {
    	elevatorTalonSRX = new TalonSRX(Constants.elevatorTalonSRX);
    	elevatorPotentiometer = new AnalogPotentiometer(Constants.elevatorPotentiometer);
    	
    	elevatorTalonSRX.config_kP(0, kElevatorP, 0);
    	elevatorTalonSRX.config_kI(0, kElevatorI, 0);
    	elevatorTalonSRX.config_kD(0, kElevatorD, 0);
    }
    
    public void set(ControlMode mode, double value) {
    	elevatorTalonSRX.set(mode, value);
    }   
    
    public double getDistance() {
    	return elevatorTalonSRX.getSelectedSensorPosition(0);
    }
    
}
