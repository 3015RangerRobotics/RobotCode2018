package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.robot.OI;
import org.usfirst.frc.team3015.robot.subsystems.BuddyClimb;
import org.usfirst.frc.team3015.robot.subsystems.Climber;
import org.usfirst.frc.team3015.robot.subsystems.Drive;
import org.usfirst.frc.team3015.robot.subsystems.Elevator;
import org.usfirst.frc.team3015.robot.subsystems.Grabber;
import org.usfirst.frc.team3015.robot.subsystems.OurCompressor;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public abstract class CommandBase extends Command {
	public static Drive drive;
	public static OI oi;
	public static Grabber grabber;
	public static OurCompressor ourCompressor;
	public static Climber climber;
	public static BuddyClimb buddyClimb;
	public static Elevator elevator;
	
	public static void init() {
		drive = new Drive();
		grabber = new Grabber();
		ourCompressor = new OurCompressor();	
		climber = new Climber();
		buddyClimb = new BuddyClimb();
		elevator = new Elevator();
		//SUBSYSTEMS BEFORE THIS POINT!!!!!!!
		oi = new OI();
	}
}
