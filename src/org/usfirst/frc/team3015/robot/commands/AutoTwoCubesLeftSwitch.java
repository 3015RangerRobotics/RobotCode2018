package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoTwoCubesLeftSwitch extends CommandGroup {

    public AutoTwoCubesLeftSwitch() {
    	addSequential(new GrabberClose());
    	addParallel(new ElevatorToSwitch());  	
    	addSequential(new DriveMotionProfile("wallToLeftSwitch"));
    	addSequential(new GrabberOpen());
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveMotionProfile("leftSwitchToCubePrep"));
    	addSequential(new DriveToCube(.25));
    	addSequential(new ElevatorToSwitch());
    	addSequential(new DriveForTime(.5, 1));
    	addSequential(new GrabberOpen());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(-1, -10, -5)));
    	addSequential(new ElevatorToBottom());
    	
    }
}
