package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {
	DoubleSolenoid climberSolenoid;
	
	public Climber() {
		climberSolenoid = new DoubleSolenoid(Constants.climberSolenoid1, Constants.climberSolenoid2);
	}
	
    public void initDefaultCommand() {
        
    }
    
    public void extendClimber() {
    	climberSolenoid.set(DoubleSolenoid.Value.kForward);
    }
    
    public void retractClimber() {
    	climberSolenoid.set(DoubleSolenoid.Value.kReverse);
    }
}

