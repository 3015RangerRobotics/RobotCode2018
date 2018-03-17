package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabberCubeOuttake extends CommandGroup {

    public GrabberCubeOuttake() {
        addSequential(new GrabberOpen());
        addSequential(new GrabberDown());
        addSequential(new GrabberIntakeOut());
    }
}
