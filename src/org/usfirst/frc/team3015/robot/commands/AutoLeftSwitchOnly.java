package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoLeftSwitchOnly extends CommandGroup {

    public AutoLeftSwitchOnly() {
        addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("leftSwitchOnly"));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 12, 8, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(58));
        addSequential(new ObtainCube(2.5));        
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.25, 12, 8, 60, true)));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveTurnToAngleEncoders(-58));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3, 12, 8, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new ElevatorToBottom());
    }
}
