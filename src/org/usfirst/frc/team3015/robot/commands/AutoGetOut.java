package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoGetOut extends CommandGroup {

    public AutoGetOut() {
    	addParallel(new ElevatorToSwitch());
    	addSequential(new DriveMotionProfile("getOut"));
    	addSequential(new GrabberCubeEject());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 10, 60, true)));
    	addSequential(new ElevatorToBottom());
    }
}
