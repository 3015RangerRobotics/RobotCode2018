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
	DoubleSolenoid leftGrab;
	DoubleSolenoid rightGrab;
	
	public Grabber() {
		leftGrab = new DoubleSolenoid(Constants.leftGrabSolenoid1, Constants.leftGrabSolenoid2);
		rightGrab = new DoubleSolenoid(Constants.rightGrabSolenoid1,Constants.rightGrabSolenoid2);
	}
	
    public void initDefaultCommand() {
        setDefaultCommand(new GrabberOpen());
    }
    
    public void closeGrabber() {
    	leftGrab.set(DoubleSolenoid.Value.kReverse);
    	rightGrab.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openGrabber() {
    	leftGrab.set(DoubleSolenoid.Value.kForward);
    	rightGrab.set(DoubleSolenoid.Value.kForward);
    }
    
    public void offGrabber() {
    	leftGrab.set(DoubleSolenoid.Value.kOff);
    	rightGrab.set(DoubleSolenoid.Value.kOff);
    }
    
}

