package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BuddyClimb extends Subsystem {
	Solenoid buddySolenoid;

    public BuddyClimb() {
    	buddySolenoid = new Solenoid(Constants.buddySolenoid);
    }

    public void initDefaultCommand() {
        
    }
    
    public void buddyExtend() {
    	buddySolenoid.set(true);
    }
    
    public void buddyRetract() {
    	buddySolenoid.set(false);
    }
}

