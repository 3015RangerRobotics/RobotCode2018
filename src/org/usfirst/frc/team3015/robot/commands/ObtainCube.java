package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ObtainCube extends CommandGroup {
	
    public ObtainCube() {
        addSequential(new GrabberDown(true));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new GrabberUp());
        addSequential(new GrabberClose());
    }
}