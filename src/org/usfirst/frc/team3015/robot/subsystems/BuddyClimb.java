package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class BuddyClimb extends Subsystem {
	private DoubleSolenoid buddySolenoid;

    public BuddyClimb() {
    	buddySolenoid = new DoubleSolenoid(Constants.buddySolenoid1, Constants.buddySolenoid2);
    }

    public void initDefaultCommand() {
    	
    }
    
    /**
     * Deploy the buddy climb forks
     */
    public void buddyExtend() {
    	buddySolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    /**
     * Lock the buddy climb forks
     */
    public void buddyRetract() {
    	buddySolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

