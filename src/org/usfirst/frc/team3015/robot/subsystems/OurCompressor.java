package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.OurCompressorAuto;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Compressor subsystem
 * Prefixed with "Our" to prevent naming conflicts
 */
public class OurCompressor extends Subsystem {
	private Compressor compressor;
	private AnalogPotentiometer pressureSensor;
	
	public OurCompressor() {
		compressor = new Compressor();
		pressureSensor = new AnalogPotentiometer(Constants.pressureSensor, 250, -25);
	}
	
	public void initDefaultCommand() {
    	setDefaultCommand(new OurCompressorAuto());
    }
	
	/**
	 * Make the compressor run based on the state of the pressure switch.
	 */
	public void startCompressor() {
		compressor.start();
	}
	
	/**
	 * Stop the compressor from running
	 */
	public void stopCompressor() {
		compressor.stop();
	}
	
	/**
	 * Get the pressure sensor's reading
	 * @return Pressure reading in PSI
	 */
	public double getPressure() {
		return pressureSensor.get();
	}
}