package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;

/**
 *
 */
public class DriveToCube extends CommandBase {
	private PIDController pidController;
	
    public DriveToCube(double driveSpeed) {
        requires(drive);
        this.pidController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.new IMUPidSource(), drive.new DriveToCubePidOutput(driveSpeed));
    }

    protected void initialize() {
    	drive.resetGyro();
    	pidController.setAbsoluteTolerance(.5);
    	pidController.setOutputRange(-1, 1);
    	pidController.setSetpoint(drive.bestTarget.getXAngle());
    	pidController.enable();
    }

    protected void execute() { 
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