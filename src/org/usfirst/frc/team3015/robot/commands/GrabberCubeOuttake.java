package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class GrabberCubeOuttake extends CommandGroup {

    public GrabberCubeOuttake() {
        addSequential(new GrabberOpenIfDown());
        addSequential(new GrabberDown(false));
        addSequential(new GrabberIntakeOut());
    }
}
