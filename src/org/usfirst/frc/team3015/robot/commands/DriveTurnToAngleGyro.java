package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTurnToAngleGyro extends CommandBase implements PIDOutput{
	PIDController turnController;
	double setpoint = 0;
	int onTargetCount = 0;

    public DriveTurnToAngleGyro(double angle) {
        requires(drive);
        this.setpoint = angle;
        turnController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.imu, this);
        turnController.setInputRange(-180.0, 180.0);
        turnController.setOutputRange(-1.0, 1.0);
        turnController.setAbsoluteTolerance(1.0);
        turnController.setContinuous(true);
    }

    protected void initialize() {
    	drive.resetGyro();
    	turnController.setSetpoint(setpoint);
    	turnController.enable();
    	onTargetCount = 0;
    }

    protected void execute() {
    	drive.arcadeDrive(0, turnController.get(), false);
    	if(turnController.onTarget()) {
    		onTargetCount++;
    	}else {
    		onTargetCount = 0;
    	}
    }

    protected boolean isFinished() {
        return onTargetCount >= 10;
    }

    protected void end() {
    	drive.setMotorOutputs(0, 0);
    	turnController.disable();
    }

    protected void interrupted() {
    	end();
    }

	@Override
	public void pidWrite(double output) {
		
	}
}
