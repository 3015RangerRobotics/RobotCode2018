package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.Drive;
import org.usfirst.frc.team3015.robot.subsystems.Prototype3CIM;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static Drive drive;
	public static OI oi;
	public static Prototype3CIM prototype3cim;
	
	public static void init() {
		drive = new Drive();
		oi = new OI();
	}
}
