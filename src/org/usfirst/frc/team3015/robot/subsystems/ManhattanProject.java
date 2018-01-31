package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.ManhattanRetract;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ManhattanProject extends Subsystem {
	VictorSP rollers;
	DoubleSolenoid extension;
	private final double rollerSpeed = 0.5;
	
	public ManhattanProject() {
		rollers = new VictorSP(Constants.manhattenRollers);
		extension = new DoubleSolenoid(Constants.manhattenExtender1, Constants.manhattenExtender2);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new ManhattanRetract());
    }
	
	public void extend() {
		extension.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retract() {
		extension.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void rollerUp() {
		rollers.set(rollerSpeed);
	}
	
	public void rollerDown() {
		rollers.set(-rollerSpeed);
	}
	
	public void rollerStop() {
		rollers.set(0);
	}
}
