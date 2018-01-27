package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.commands.ManhattanStop;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class ManhattanProject extends Subsystem {
	VictorSP rollers;
	Solenoid extension;
	private final double rollerSpeed = 0.5;
	
	public ManhattanProject() {
		rollers = new VictorSP(Constants.manhattenRollers);
		extension = new Solenoid(Constants.manhattenExtender);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new ManhattanStop());
    }
	
	public void extend() {
		extension.set(true);
	}
	
	public void retract() {
		extension.set(false);
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

