package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class AutoRightLeftScaleFirst extends CommandGroup {

    public AutoRightLeftScaleFirst() {
        addParallel(new ElevatorToScale());
        addSequential(new DriveMotionProfile("wallToLeftScale", false));
        addParallel(new GrabberOpen());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2, 14, 12, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(62, true));
        addSequential(new DriveMotionProfile("leftScaleToRightSwitch", false));
        addSequential(new ObtainCube(3.25));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1.5, 14, 12, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(1, 14, 12, 60, true)));
        addSequential(new ElevatorToBottom());
        
        // 21, 21, -90
        // 21,  11, -90
        // 23, 8, 180
    }
}