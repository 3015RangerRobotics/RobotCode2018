package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.ElevatorHold;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	public TalonSRX elevatorTalonSRX;
	
	//331 per inch
	
	public final double kElevatorP = 0.6;
	public final double kElevatorI = 0.0;
	public final double kElevatorD = 0.2;
	public final double kElevatorF = 0.005;
	public final double kV = 0.04;
    public final double kA = 0.01;
    
    public final double pulsesPerInch = 331;
    
    @Override
    public void initDefaultCommand() {
//    	this.setDefaultCommand(new ElevatorHold());
    }
    
    @Override
    public void periodic() {
//    	System.out.println(getDistance());
    	SmartDashboard.putData(this);
    }
    
    public Elevator() {
    	elevatorTalonSRX = new TalonSRX(Constants.elevatorTalonSRX);
    	elevatorTalonSRX.configVoltageCompSaturation(13, 10);
    	elevatorTalonSRX.enableVoltageCompensation(true);
    	elevatorTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	elevatorTalonSRX.setSensorPhase(true);
    	elevatorTalonSRX.setSelectedSensorPosition(0, 0, 0);
    	elevatorTalonSRX.setInverted(false);
    	elevatorTalonSRX.configPeakCurrentDuration(0, 10);
    	elevatorTalonSRX.configPeakCurrentLimit(0, 10);
    	//middle value
    	elevatorTalonSRX.config_kP(0, kElevatorP, 0);
    	elevatorTalonSRX.config_kI(0, kElevatorI, 0);
    	elevatorTalonSRX.config_kD(0, kElevatorD, 0);
    	elevatorTalonSRX.config_kF(0, kElevatorF, 0);
    	elevatorTalonSRX.configPeakOutputForward(1.0, 10);
    	elevatorTalonSRX.configPeakOutputReverse(-0.4, 10);
    }
    
    public void set(ControlMode mode, double value) {
    	elevatorTalonSRX.set(mode, value);
    	System.out.println("elev power:"+elevatorTalonSRX.getMotorOutputVoltage()+"elev pos:"+elevatorTalonSRX.getSelectedSensorPosition(0));
    }   
    
    public void setPercent(double value) {
    	elevatorTalonSRX.set(ControlMode.PercentOutput, value);
    	System.out.println("elev power:"+elevatorTalonSRX.getMotorOutputPercent()+"elev pos:"+elevatorTalonSRX.getSelectedSensorPosition(0));
    } 
    
    public double getDistance() {
    	return elevatorTalonSRX.getSelectedSensorPosition(0) / pulsesPerInch;
    }
}
