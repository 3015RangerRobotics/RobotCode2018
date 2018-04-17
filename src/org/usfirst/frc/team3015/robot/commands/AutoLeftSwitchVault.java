package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitchVault extends CommandGroup {

    public AutoLeftSwitchVault() {
        addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("leftSwitchOnly"));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 12, 8, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(58));
        addSequential(new SuccCube(2.5));
        addSequential(new DriveTurnToAngleEncoders(122.5));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 12, 8, 60, false)));
        addSequential(new GrabberIntakeOutForTime(.75));
        addSequential(new GrabberUp());
    }
}
