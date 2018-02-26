package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class GrabberCubeUp extends CommandGroup {

    public GrabberCubeUp() {
        addSequential(new GrabberUp());
        addSequential(new GrabberClose());
        addSequential(new DriverStopVibration());
    }
}