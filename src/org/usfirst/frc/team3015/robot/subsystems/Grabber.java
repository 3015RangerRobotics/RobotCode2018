package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.GrabberOpen;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Grabber that can open and close to contain power cubes
 * Operates on 2 double solenoids
 */
public class Grabber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	DoubleSolenoid grabberSolenoid;
	
	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new GrabberOpen());
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

