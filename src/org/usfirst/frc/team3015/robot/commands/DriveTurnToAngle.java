package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 */
public class DriveTurnToAngle extends CommandBase {
	private double angle;
	private PIDController pidController;

    public DriveTurnToAngle(double angle) {
        requires(drive);   
        this.angle = angle;
        this.pidController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.imuPidSource, drive.turnPidOutput);
    }

    protected void initialize() {
    	pidController.setAbsoluteTolerance(.5);
    	pidController.setOutputRange(-1, 1);
    	pidController.setSetpoint(angle);
    	pidController.enable();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return pidController.onTarget();
    }

    protected void end() {
    	drive.arcadeDrive(0, 0, false);
    	pidController.disable();
    }

    protected void interrupted() {
    	end();
    }
}
