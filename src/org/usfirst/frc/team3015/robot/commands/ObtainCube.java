package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ObtainCube extends CommandGroup {
	
    public ObtainCube(double distance) {
        addSequential(new GrabberDown());
        addParallel(new DriveMotionProfile(MotionProfiles.generate1D(distance, 12, 6, 60, false)));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new WaitCommand(.15));
        addSequential(new GrabberUp());
        addSequential(new WaitCommand(.15));
        addSequential(new GrabberClose());
    }
}