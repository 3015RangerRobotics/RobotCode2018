package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.RobotController;

public class OurCompressorAuto extends CommandBase {
	private final double CUTOFF_VOLTAGE = 10.0;
//	private boolean fillCompressor = true;
	
    public OurCompressorAuto() {
        requires(ourCompressor);
    }

    protected void initialize() {
    	ourCompressor.startCompressor();
//    	fillCompressor = true;
    }

    protected void execute() {
    	if(DriverStation.getInstance().isAutonomous() || RobotController.getBatteryVoltage() <= CUTOFF_VOLTAGE ||
    			(DriverStation.getInstance().getMatchTime() < 20 && DriverStation.getInstance().getMatchTime() > 0)){
    		ourCompressor.stopCompressor();
    	}else{
//    		if(fillCompressor){
//    			ourCompressor.startCompressor();
//    			if(ourCompressor.getPressure() >= 120){
//    				fillCompressor = false;
//    			}
//    		}else{
//    			ourCompressor.stopCompressor();
//    			if(ourCompressor.getPressure() <= 60){
//    				fillCompressor = true;
//    			}
//    		}
    		ourCompressor.startCompressor();
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