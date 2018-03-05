package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoTwoCubesBothLeft extends CommandGroup {

    public AutoTwoCubesBothLeft() {
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale"));
        addParallel(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngle(103, false));
        addParallel(new ObtainCubeToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5.5, 12, 6, 60, false)));
        addSequential(new WaitCommand(1));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 6, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}
