package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoRightSwitchOnly extends CommandGroup {

    public AutoRightSwitchOnly() {
        addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("rightSwitchOnly"));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 10, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(-52));
        addSequential(new ObtainCube(3.2));        
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.35, 14, 10, 60, true)));
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveTurnToAngleEncoders(52));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4.25, 14, 10, 60, false)));
        addSequential(new GrabberCubeEject());
        addSequential(new ElevatorToBottom());
    }
}
