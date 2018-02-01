package org.usfirst.frc.team3015.robot.subsystems;

import org.rangerrobotics.lib.android.TargetInfo;
import org.rangerrobotics.lib.android.TargetUpdate;
import org.rangerrobotics.lib.android.messages.TargetUpdateReceiver;
import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Drive Subsystem, used for driving
 */
public class Drive extends Subsystem implements TargetUpdateReceiver{
	//TODO: Tune these
	public final double kDriveP = 1.3;
	public final double kDriveI = 0.0;
	public final double kDriveD = 0.01;
	
	//TODO: Tune these
	public final double kTurnP = 0.01;
	public final double kTurnI = 0;
	public final double kTurnD = 0.0015;
	
	//TODO: Tune these: conversion from real-world units to percentage output.
	public final double kV = 0.0625;// kV = 1 / max velocity 0.091
	public final double kA = 0.013;// adjust kA until tracking well, then adjust pid
	
	//TODO: Find this
	public final double kDistancePerPulse = 0.0090477405;
	
	public final double maxVelocity = 16.0;
	public final double maxAcceleration = 5;
	
	VictorSP leftDrive;
	VictorSP rightDrive;
	Encoder leftEncoder;
	Encoder rightEncoder;
	AHRS imu;
	
	public TargetInfo bestTarget = null;
	
	public Drive() {
		leftDrive = new VictorSP(Constants.leftDriveMotor);
		leftEncoder = new Encoder(Constants.leftDriveEncoder1, Constants.leftDriveEncoder2);
		leftDrive.setInverted(true);
		leftEncoder.setReverseDirection(false);
		leftEncoder.setDistancePerPulse(kDistancePerPulse);
		rightDrive = new VictorSP(Constants.rightDriveMotor);
		rightEncoder = new Encoder(Constants.rightDriveEncoder1, Constants.rightDriveEncoder2);
		rightEncoder.setReverseDirection(true);
		rightEncoder.setDistancePerPulse(kDistancePerPulse);
		imu = new AHRS(I2C.Port.kOnboard);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
	
	@Override
	public void periodic() {
		System.out.println(getAngle() + ", " + ((bestTarget != null) ? bestTarget.getXAngle() : "null"));
	}
	
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
    
    public void tankDrive(double left, double right) {
    	leftDrive.set(left);
    	rightDrive.set(right);
    }
    
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;

        moveValue = limit(moveValue);
        rotateValue = limit(rotateValue);

        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
        	if (moveValue >= 0.0) {
        		moveValue = moveValue * moveValue;
        	} else {
	            moveValue = -(moveValue * moveValue);
	        }
	        if (rotateValue >= 0.0) {
	        	rotateValue = rotateValue * rotateValue;
	        } else {
	        	rotateValue = -(rotateValue * rotateValue);
	        }
        }

        if (moveValue > 0.0) {
        	if (rotateValue > 0.0) {
        		leftMotorSpeed = moveValue - rotateValue;
        		rightMotorSpeed = Math.max(moveValue, rotateValue);
	        } else {
	        	leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        	rightMotorSpeed = moveValue + rotateValue;
	        }
	    } else {
	    	if (rotateValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	    		rightMotorSpeed = moveValue + rotateValue;
	        } else {
	        	leftMotorSpeed = moveValue - rotateValue;
	        	rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	        }
	    }	
	    tankDrive(leftMotorSpeed, rightMotorSpeed);
    }
    
    protected static double limit(double num) {
        if (num > 1.0) {
        	return 1.0;
        }
        if (num < -1.0) {
        	return -1.0;
        }
        return num;
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
    
    public double getAngle() {
    	return imu.getYaw();
    }
    
    public void resetGyro() {
    	imu.zeroYaw();
    }
    
	@Override
	public void onUpdateReceived(TargetUpdate update) {
		TargetInfo shortestDistance = null;
		for(TargetInfo target:update.getTargets()) {
			if(shortestDistance == null) {
				shortestDistance = target;
			}else if(target.getDistance() < shortestDistance.getDistance()){
				shortestDistance = target;
			}
		}
		bestTarget = shortestDistance;
	}
	
	public class IMUPidSource implements PIDSource{

		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			return getAngle();
		}
		
	}
	
	public class TurnPidOutput implements PIDOutput{
		@Override
		public void pidWrite(double turnSpeed) {
			arcadeDrive(0, turnSpeed, false);
			System.out.print(turnSpeed);
		}
	}
	
	public class DriveToCubePidOutput implements PIDOutput{
		double driveSpeed;
		
		public DriveToCubePidOutput(double driveSpeed) {
			this.driveSpeed = driveSpeed;
		}
		@Override
		public void pidWrite(double turnSpeed) {
			arcadeDrive(driveSpeed, turnSpeed, false);
		}
	}
}

