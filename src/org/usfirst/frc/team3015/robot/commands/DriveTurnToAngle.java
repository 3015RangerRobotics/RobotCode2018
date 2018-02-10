package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.RobotController;

/**
 * Turns to an angle using the imu
 */
public class DriveTurnToAngle extends CommandBase {
	private double angle;
	private double minDrive = 0.12;
	private double onTargetCount = 0;
	private PIDController pidController;

    public DriveTurnToAngle(double angle) {
        requires(drive);   
        this.angle = angle;
    }

    protected void initialize() {
    	drive.resetGyro();
        this.pidController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.new IMUPidSource(), new PIDOutput(){
        	public void pidWrite(double output) {
        		
        	}
        });
    	pidController.setAbsoluteTolerance(1);
    	pidController.setOutputRange(-1, 1);
    	pidController.setSetpoint(angle);
    	pidController.enable();
    	
    }

    protected void execute() {
    	double out = pidController.get();
    	System.out.println(out + ", " + drive.getAngle());
    	if(out <= minDrive && out >= -minDrive) {
    		if(out < 0) {
    			drive.arcadeDrive(0, -minDrive * 12.5 / RobotController.getInputVoltage(), false);
    		}else {
    			drive.arcadeDrive(0, minDrive * 12.5 / RobotController.getInputVoltage(), false);
    		}
    	}else {
    		drive.arcadeDrive(0, out, false);
    	}
    	
    	if(pidController.onTarget()) {
    		onTargetCount++;
    	}else {
    		onTargetCount = 0;
    	}
    }

    protected boolean isFinished() {
        return onTargetCount >= 10;
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    	pidController.disable();
    }

    protected void interrupted() {
    	end();
    }
}
