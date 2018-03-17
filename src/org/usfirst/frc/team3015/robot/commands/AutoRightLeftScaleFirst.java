package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightLeftScaleFirst extends CommandGroup {

    public AutoRightLeftScaleFirst() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 12, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(-62));
        addSequential(new DriveMotionProfile("leftScaleToRightSwitch"));
        addSequential(new ObtainCube(3.25));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 12, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 12, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}