package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.CommandBase;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	public TalonSRX elevatorTalonSRX;
	DigitalInput elevatorBottomLimit;
	
	public final double elevatorMaxV = 10.0;
	public final double elevatorAcc = 15.0;
	public final double elevatorHeightBottom = 0;
	public final double elevatorHeightSwitch = 26;
	public final double elevatorHeightScale = 66;
	public final double elevatorHeightScaleLow = 57;
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
    	if(isAtBottom()) {
    		elevatorTalonSRX.setSelectedSensorPosition(0, 0, 0);
    	}
    	
//    	System.out.println(elevatorTalonSRX.getOutputCurrent());
    }
    
    public Elevator() {
    	elevatorTalonSRX = new TalonSRX(Constants.elevatorTalonSRX);
    	elevatorTalonSRX.configVoltageCompSaturation(13, 10);
    	elevatorTalonSRX.enableVoltageCompensation(true);
    	elevatorTalonSRX.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
    	elevatorTalonSRX.setSensorPhase(true);
    	elevatorTalonSRX.setSelectedSensorPosition(0, 0, 0);
    	elevatorTalonSRX.setInverted(false);
    	elevatorTalonSRX.configPeakCurrentLimit(40, 10);
    	elevatorTalonSRX.configPeakCurrentDuration(200, 10);
    	elevatorTalonSRX.configContinuousCurrentLimit(30, 10);
    	elevatorTalonSRX.enableCurrentLimit(true);
    	//middle value
    	elevatorTalonSRX.config_kP(0, kElevatorP, 0);
    	elevatorTalonSRX.config_kI(0, kElevatorI, 0);
    	elevatorTalonSRX.config_kD(0, kElevatorD, 0);
    	elevatorTalonSRX.config_kF(0, kElevatorF, 0);
    	elevatorTalonSRX.configPeakOutputForward(1.0, 10);
    	elevatorTalonSRX.configPeakOutputReverse(-0.1, 10);
    	
    	elevatorBottomLimit = new DigitalInput(Constants.elevatorBottomLimit);
    }
    
    public void set(ControlMode mode, double value) {
    	elevatorTalonSRX.set(mode, value);
//    	System.out.println("elev power:"+elevatorTalonSRX.getMotorOutputVoltage()+"elev vel:"+elevatorTalonSRX.getSelectedSensorVelocity(0)/pulsesPerInch);
    	if(Math.abs(elevatorTalonSRX.getMotorOutputVoltage()) >= 4 && Math.abs(elevatorTalonSRX.getSelectedSensorVelocity(0)/pulsesPerInch) < 0.5) {
    		CommandBase.oi.coDriverRumble(1.0);
    	}else {
    		CommandBase.oi.coDriverRumble(0);
    	}
    }
    
    public double getDistance() {
    	return elevatorTalonSRX.getSelectedSensorPosition(0) / pulsesPerInch;
    }
    
    public double getRawDistance() {
    	return elevatorTalonSRX.getSelectedSensorPosition(0);
    }
    
    public boolean isAtBottom() {
    	return !elevatorBottomLimit.get();
    }
}
