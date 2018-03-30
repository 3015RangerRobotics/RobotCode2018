package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchOnly extends CommandGroup {

    public AutoRightSwitchOnly() {
//        addSequential(new GrabberClose());
//        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("rightSwitchOnly"));
//        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 12, 8, 60, true)));
//        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(-62));
        
//        addSequential(new ObtainCube(3.2));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3.2, 12, 8, 60, false)));
        
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.35, 12, 8, 60, true)));
//        addParallel(new ElevatorToSwitch());
        addSequential(new DriveTurnToAngleEncoders(62));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3.25, 12, 8, 60, false)));
//        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 8, 60, true)));
//        addSequential(new ElevatorToBottom());
    }
}
