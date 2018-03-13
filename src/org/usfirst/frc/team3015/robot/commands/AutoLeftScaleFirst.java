package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class AutoLeftScaleFirst extends CommandGroup {

    public AutoLeftScaleFirst() {
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale", false));
        addParallel(new GrabberOpen());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(110, false));
        addSequential(new ObtainCube(5.5));
        addSequential(new WaitCommand(0.5));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 12, 6, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 12, 6, 60, true)));
        addSequential(new ElevatorToBottom());
    }
}
