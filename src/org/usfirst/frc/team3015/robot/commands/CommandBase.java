package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.Drive;
import org.usfirst.frc.team3015.robot.subsystems.Grabber;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static Drive drive;
	public static Grabber grabber;
	public static OI oi;
	
	public static void init() {
		drive = new Drive();
		grabber = new Grabber();
		oi = new OI();
	}
}
