package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Grabber that can open and close to contain power cubes
 * Operates on a double solenoids
 */
public class Grabber extends Subsystem {
	DoubleSolenoid grabberSolenoid;
	DigitalInput grabberSwitch1;
	DigitalInput grabberSwitch2; //limit switch
	
	public Grabber() {
		grabberSolenoid = new DoubleSolenoid(Constants.grabberSolenoid1, Constants.grabberSolenoid2);
		grabberSwitch1 = new DigitalInput(Constants.grabberLimitSwitch1);
		grabberSwitch2 = new DigitalInput(Constants.grabberLimitSwitch2);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void closeGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void openGrabber() {
    	grabberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public boolean isSwitchTriggered() {
    	return (grabberSwitch1.get() && grabberSwitch2.get());
    }
    
}

