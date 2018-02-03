package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.BuddyClimb;
import org.usfirst.frc.team3015.robot.subsystems.Climber;
import org.usfirst.frc.team3015.robot.subsystems.Drive;
import org.usfirst.frc.team3015.robot.subsystems.Elevator;
import org.usfirst.frc.team3015.robot.subsystems.Grabber;
import org.usfirst.frc.team3015.robot.subsystems.Intake;
import org.usfirst.frc.team3015.robot.subsystems.ManhattanProject;
import org.usfirst.frc.team3015.robot.subsystems.OurCompressor;

import edu.wpi.first.wpilibj.command.Command;

public abstract class CommandBase extends Command {
	public static Drive drive;
	public static OI oi;
	public static Intake intake; 
	public static Grabber grabber;
	public static OurCompressor ourCompressor;
	public static ManhattanProject manhattan;
	public static Climber climber;
	public static BuddyClimb buddyClimb;
	public static Elevator elevator;
	
	public static void init() {
		drive = new Drive();
		intake = new Intake();
//		grabber = new Grabber();
//		ourCompressor = new OurCompressor();
//		manhattan = new ManhattanProject();		
//		climber = new Climber();
//		buddyClimb = new BuddyClimb();
//		elevator = new Elevator();
		
		//SUBSYSTEMS BEFORE THIS POINT!!!!!!!
		oi = new OI();
	}
}
