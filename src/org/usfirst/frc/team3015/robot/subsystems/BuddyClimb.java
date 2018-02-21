package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BuddyClimb extends Subsystem {
	DoubleSolenoid buddySolenoid;

    public BuddyClimb() {
    	buddySolenoid = new DoubleSolenoid(Constants.buddySolenoid1, Constants.buddySolenoid2);
    }

    public void initDefaultCommand() {
//        setDefaultCommand(new BuddyRetract());
    }
    
    public void buddyExtend() {
    	buddySolenoid.set(DoubleSolenoid.Value.kReverse);
    }
    
    public void buddyRetract() {
    	buddySolenoid.set(DoubleSolenoid.Value.kForward);
    }
}

