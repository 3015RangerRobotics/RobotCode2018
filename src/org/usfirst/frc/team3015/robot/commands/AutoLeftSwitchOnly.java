package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitchOnly extends CommandGroup {

    public AutoLeftSwitchOnly() {
        addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("leftSwitchOnly"));
        addSequential(new GrabberCubeEject());
        addParallel(new ElevatorToBottomDelayed(0.25));
        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(5, 0, -56, 14, 14, 60, true)));
        addSequential(new ObtainCube(3.7));
        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(3.7, 0, 56, 14, 14, 60, true)));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 14, 60, false)));
        addSequential(new GrabberCubeEject());
        addParallel(new ElevatorToBottomDelayed(0.25));
        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(4, 0, -50, 14, 14, 60, true)));
        addSequential(new ObtainCube(2.5));
        addSequential(new DriveTurnToAngleEncoders(-75));        
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3, 14, 14, 60, false)));
        addSequential(new GrabberCubeEject());
        addParallel(new ElevatorToBottomDelayed(0.25));
        
//        addSequential(new GrabberClose());
//        addParallel(new ElevatorToSwitch());
//        addSequential(new DriveMotionProfile("leftSwitchOnly"));
//        addSequential(new GrabberCubeEject());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 10, 60, true)));
//        addParallel(new ElevatorToBottom());
//        addSequential(new DriveTurnToAngleEncoders(56));
//        addSequential(new ObtainCube(3.7));
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.6, 14, 10, 60, true)));
//        addParallel(new ElevatorToSwitch());
//        addSequential(new DriveTurnToAngleEncoders(-56));
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4.5, 14, 10, 60, false)));
//        addSequential(new GrabberCubeEject());
//        addSequential(new ElevatorToBottom());
    }
}
