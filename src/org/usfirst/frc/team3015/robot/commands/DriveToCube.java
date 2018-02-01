package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

/**
 *
 */
public class DriveToCube extends CommandBase {
	private PIDController pidController;
	private double driveSpeed;
	
    public DriveToCube(double driveSpeed) {
        requires(drive);
        this.driveSpeed = driveSpeed;
        this.pidController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.new IMUPidSource(), new PIDOutput(){
        	public void pidWrite(double output) {
        		
        	}
        });
    }

    protected void initialize() {
    	drive.resetGyro();
    	pidController.setAbsoluteTolerance(.5);
    	pidController.setOutputRange(-1, 1);
    	pidController.setSetpoint(drive.bestTarget.getXAngle());
    	pidController.enable();
    }

    protected void execute() { 
    	drive.arcadeDrive(driveSpeed, pidController.get(), false);
    }

    protected boolean isFinished() {
    	if(drive.bestTarget.getDistance() < 2) {
    		return true;
    	} 
    	return false;
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    	pidController.disable();
    }

    protected void interrupted() {
    	end();
    }
}