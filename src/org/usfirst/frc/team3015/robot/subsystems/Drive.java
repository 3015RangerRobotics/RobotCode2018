package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	//TODO: Tune these
	public final double kP = 0.1;
	public final double kI = 0.0;
	public final double kD = 0.1;
	
	//TODO: Tune these: conversion from real-world units to percentage output
	public final double kV = 1.0;
	public final double kA = 1.0;
	
	//TODO: Find this
	public final double kDistancePerPulse = 1.0;
	
	VictorSP leftDrive;
	VictorSP rightDrive;
	Encoder leftEncoder;
	Encoder rightEncoder;
	
	public Drive() {
		leftDrive = new VictorSP(RobotMap.leftDriveMotor);
		leftEncoder = new Encoder(RobotMap.leftDriveEncoder1, RobotMap.leftDriveEncoder2);
		leftDrive.setInverted(true);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setDistancePerPulse(kDistancePerPulse);
		rightDrive = new VictorSP(RobotMap.rightDriveMotor);
		rightEncoder = new Encoder(RobotMap.rightDriveEncoder1, RobotMap.rightDriveEncoder2);
		rightEncoder.setDistancePerPulse(kDistancePerPulse);
	}

    public void initDefaultCommand() {
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void tankDrive(double left, double right) {
    	leftDrive.set(left);
    	rightDrive.set(right);
    }
    
    public double getLeftDistance() {
    	return leftEncoder.getDistance();
    }
    
    public double getRightDistance() {
    	return rightEncoder.getDistance();
    }
}

