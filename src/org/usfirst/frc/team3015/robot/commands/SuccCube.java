package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class SuccCube extends CommandGroup {
	
    public SuccCube(double distance) {
        addSequential(new GrabberDown());
        addParallel(new DriveMotionProfile(MotionProfiles.generate1D(distance, 12, 8, 60, false)));
        addSequential(new GrabberIntakeTilCube());
    }
}