package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightLeftScaleFirst extends CommandGroup {

    public AutoRightLeftScaleFirst() {
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale", false));
        addParallel(new GrabberCubeEject());
        addParallel(new ElevatorToBottomDelayed(1));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 10, 60, true)));
        addSequential(new DriveTurnToAngle(62, true));
        addSequential(new DriveMotionProfile("leftScaleToRightSwitch", false));
        addParallel(new ObtainCubeToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3.25, 14, 7, 60, false)));
        addSequential(new WaitCommand(1));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 10, 60, true)));
        addSequential(new ElevatorToBottom());
        
        // 21, 21, -90
        // 21,  11, -90
        // 23, 8, 180
    }
}