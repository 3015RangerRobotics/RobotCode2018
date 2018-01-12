package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {
	//0.1029573493872
	//TODO: Tune these
	public final double kP = 0.3;
	public final double kI = 0.0;
	public final double kD = 0.0;
	
	//TODO: Tune these: conversion from real-world units to percentage output.
	public final double kV = 0.091;// kV = 1 / max velocity 0.091
	public final double kA = 0.0325;// adjust kA until tracking well, then adjust pid
	
	//TODO: Find this
	public final double kDistancePerPulse = 0.008;
	
	VictorSP leftDrive;
	VictorSP rightDrive;
	Encoder leftEncoder;
	Encoder rightEncoder;
	
	public Drive() {
		leftDrive = new VictorSP(Constants.leftDriveMotor);
		leftEncoder = new Encoder(Constants.leftDriveEncoder1, Constants.leftDriveEncoder2);
		leftDrive.setInverted(true);
		leftEncoder.setReverseDirection(true);
		leftEncoder.setDistancePerPulse(kDistancePerPulse);
		rightDrive = new VictorSP(Constants.rightDriveMotor);
		rightEncoder = new Encoder(Constants.rightDriveEncoder1, Constants.rightDriveEncoder2);
		rightEncoder.setDistancePerPulse(kDistancePerPulse);
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
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
    
    public double getLeftVelocity() {
    	return leftEncoder.getRate();
    }
    
    public double getRightVelocity() {
    	return rightEncoder.getRate();
    }
}

