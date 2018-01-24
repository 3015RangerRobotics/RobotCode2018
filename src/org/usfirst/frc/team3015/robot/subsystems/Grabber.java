package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Grabber that can open and close to contain power cubes
 * Operates on 2 double solenoids
 */
public class Grabber extends Subsystem {
	DoubleSolenoid grabberSolenoid;
	
	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void closeGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void offGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kOff);
    }
    
}

