package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ObtainCubeVision extends CommandGroup {
	
    public ObtainCubeVision(double distance) {
        addSequential(new GrabberDown());
        addParallel(new DriveToCube(distance));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new WaitCommand(.15));
        addSequential(new GrabberUp());
        addSequential(new WaitCommand(.15));
        addSequential(new GrabberClose());
    }
}