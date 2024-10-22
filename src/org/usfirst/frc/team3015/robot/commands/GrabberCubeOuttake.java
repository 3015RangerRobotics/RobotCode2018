package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class GrabberCubeOuttake extends CommandGroup {

    public GrabberCubeOuttake() {
        addSequential(new GrabberOpen());
        addSequential(new WaitCommand(0.25));
        addSequential(new GrabberDown());
        addSequential(new GrabberIntakeOut());
    }
}
