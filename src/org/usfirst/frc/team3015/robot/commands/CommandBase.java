package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static OI oi;
	
	public static void init() {
		oi = new OI();
	}
}
