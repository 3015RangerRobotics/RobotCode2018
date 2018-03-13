package org.usfirst.frc.team3015.robot;

public class DriveSignal{
	public double leftSignal;
	public double rightSignal;
	
	/**
	 * Creates a drive signal with a left and right motor output
	 * @param leftSignal The left motor signal
	 * @param rightSignal The right motor output
	 */
	public DriveSignal(double leftSignal, double rightSignal) {
		this.leftSignal = leftSignal;
		this.rightSignal = rightSignal;
	}
}
