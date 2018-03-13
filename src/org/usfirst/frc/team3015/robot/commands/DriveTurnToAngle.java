package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class DriveTurnToAngle extends CommandBase implements PIDOutput{
	PIDController turnController;
	double setpoint = 0;
	int onTargetCount = 0;
	double minTurn = 0.1;
	private boolean isAbsolute;

    public DriveTurnToAngle(double angle, boolean isAbsolute) {
        requires(drive);
        this.setpoint = angle;
        this.isAbsolute = isAbsolute;
        turnController = new PIDController(drive.kTurnP, drive.kTurnI, drive.kTurnD, drive.imu, this);
        turnController.setInputRange(-180.0, 180.0);
        turnController.setOutputRange(-1.0 + minTurn, 1.0 - minTurn);
        turnController.setAbsoluteTolerance(1.0);
        turnController.setContinuous(true);
    }

    protected void initialize() {
    	if(!isAbsolute) {
	    	setpoint += drive.getAngle();
	    	
	    	if(setpoint > 180) {
	    		setpoint -= 360;
	    	}else if(setpoint < -180) {
	    		setpoint += 360;
	    	}
    	}
    	turnController.setSetpoint(setpoint);
    	turnController.enable();
    	onTargetCount = 0;
    }

    protected void execute() {
    	double output = turnController.get();
    	
    	if(output < 0) {
    		System.out.println(output - minTurn);
    		drive.arcadeDrive(0, output - minTurn, false);
    	}else {
    		System.out.println(output + minTurn);
    		drive.arcadeDrive(0, output + minTurn, false);
    	}
    	
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
