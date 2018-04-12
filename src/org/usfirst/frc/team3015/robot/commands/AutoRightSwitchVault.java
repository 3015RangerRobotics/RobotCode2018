package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoRightSwitchVault extends CommandGroup {

    public AutoRightSwitchVault() {
        addSequential(new GrabberClose());
        addParallel(new ElevatorToSwitch());
        addSequential(new DriveMotionProfile("rightSwitchOnly"));
        addSequential(new GrabberCubeEject());
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 12, 8, 60, true)));
        addParallel(new ElevatorToBottom());
        addSequential(new DriveTurnToAngleEncoders(-62));
        addSequential(new SuccCube(3.2));        
        addSequential(new DriveTurnToAngleEncoders(-81.5));
        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(6.5, 12, 8, 60, false)));
        addSequential(new GrabberIntakeOutForTime(.75));
        addSequential(new GrabberUp());
    }
}
