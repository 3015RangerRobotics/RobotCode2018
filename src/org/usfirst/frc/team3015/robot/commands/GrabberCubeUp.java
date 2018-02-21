package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabberCubeUp extends CommandGroup {

    public GrabberCubeUp() {
        addSequential(new GrabberUp());
        addSequential(new GrabberClose());
    }
}
