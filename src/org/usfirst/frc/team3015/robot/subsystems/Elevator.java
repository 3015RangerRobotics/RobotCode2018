package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.ElevatorHold;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	public TalonSRX elevatorTalonSRX;
	
	//331 per inch
	
	public final double kElevatorP = 0.0;
	public final double kElevatorI = 0.0;
	public final double kElevatorD = 0.0;
	public final double kV = 0.1;
    public final double kA = 0.0;
    
    public final double pulsesPerInch = 331;
    
    @Override
    public void initDefaultCommand() {
    	this.setDefaultCommand(new ElevatorHold());
    }
    
    @Override
    public void periodic() {
//    	System.out.println(elevatorTalonSRX.getSelectedSensorVelocity(0) / pulsesPerInch);
    }
    
    public Elevator() {
    	elevatorTalonSRX = new TalonSRX(Constants.elevatorTalonSRX);
    	elevatorTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	elevatorTalonSRX.setSensorPhase(false);
    	elevatorTalonSRX.setSelectedSensorPosition(0, 0, 0);
    	
    	//middle value
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
