package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Runs the compressor
 * Stops if lower than set match time or voltage
 */
public class OurCompressorAuto extends CommandBase {
	private final double CUTOFF_VOLTAGE = 8.0;
	private boolean fillCompressor = true;
	
    public OurCompressorAuto() {
        requires(ourCompressor);
    }

    protected void initialize() {
    	ourCompressor.startCompressor();
    	fillCompressor = true;
    }

    protected void execute() {
    	SmartDashboard.putNumber("pressureSensor", Math.round(ourCompressor.getPressure()));

    	if(DriverStation.getInstance().isAutonomous() || RobotController.getBatteryVoltage() <= CUTOFF_VOLTAGE){
    		ourCompressor.stopCompressor();
    	}else{
    		if(fillCompressor){
    			ourCompressor.startCompressor();
    			if(ourCompressor.getPressure() >= 118){
    				fillCompressor = false;
    			}
    		}else{
    			ourCompressor.stopCompressor();
    			if(ourCompressor.getPressure() <= 60){
    				fillCompressor = true;
    			}
    		}
    	}
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    	ourCompressor.stopCompressor();
    }

    protected void interrupted() {
    	end();
    }
}