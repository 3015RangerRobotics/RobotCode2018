package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ManhattanProject extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP rollers;
	Solenoid extension;
	
	public ManhattanProject() {
		rollers = new VictorSP(Constants.manhattenRollers);
		extension = new Solenoid(Constants.manhattenExtender);
	}
	
	public void extend() {
		extension.set(true);
	}
	
	public void retract() {
		extension.set(false);
	}
	
	public void rollerUp() {
		rollers.set(Constants.rollerSpeed);
	}
	
	public void rollerDown() {
		rollers.set(Constants.rollerSpeed);
	}
	
	public void rollerStop() {
		rollers.set(0);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

