package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ZAutoRightLeftSwitchFirst extends CommandGroup {

    public ZAutoRightLeftSwitchFirst() {
    	addSequential(new GrabberClose());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(12.75, 12, 8, 60, false)));
    	addParallel(new ElevatorToSwitch());
    	addSequential(new DriveMotionProfile("leftSwitchToRightSwitch"));
    	addSequential(new DriveTurnToAngleEncoders(135));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3, 14, 10, 60, false)));
    	addSequential(new GrabberCubeEject());
    	addParallel(new ElevatorToBottom());
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
    	addSequential(new ObtainCube(2));
    	addSequential(new DriveTurnToAngleEncoders(72));
    	addSequential(new DriveMotionProfile(MotionProfiles.generate1D(6, 12, 8, 60, false)));
    }
}