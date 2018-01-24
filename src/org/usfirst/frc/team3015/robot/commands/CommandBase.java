package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.Drive;
import org.usfirst.frc.team3015.robot.subsystems.Grabber;
import org.usfirst.frc.team3015.robot.subsystems.Intake;
import org.usfirst.frc.team3015.robot.subsystems.OurCompressor;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static Drive drive;
	public static OI oi;
	public static Intake intake; 
	public static Grabber grabber;
	public static OurCompressor ourCompressor;
	
	public static void init() {
		drive = new Drive();
		intake = new Intake();
		grabber = new Grabber();
		ourCompressor = new OurCompressor();

		
		
		
		
		
		//SUBSYSTEMS BEFORE THIS POINT!!!!!!!
		oi = new OI();

	}
}
