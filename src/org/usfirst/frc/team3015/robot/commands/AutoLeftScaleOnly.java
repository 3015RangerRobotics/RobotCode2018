package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftScaleOnly extends CommandGroup {

    public AutoLeftScaleOnly() {
    	addSequential(new GrabberClose());
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale", false));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngle(103, false));
        addParallel(new ObtainCubeToScale());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5.5, 12, 6, 60, false)));
        addSequential(new DriveTurnToAngle(140, true));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 12, 6, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}