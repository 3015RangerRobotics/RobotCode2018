package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ObtainCube extends CommandGroup {
	
    public ObtainCube() {
    	addParallel(new ElevatorToBottom());
        addSequential(new GrabberDown(true));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new GrabberUp());
        addSequential(new WaitCommand(.1));
        addSequential(new GrabberClose());
    }
}