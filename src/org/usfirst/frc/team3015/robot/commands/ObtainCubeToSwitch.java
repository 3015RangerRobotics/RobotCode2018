package org.usfirst.frc.team3015.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class ObtainCubeToSwitch extends CommandGroup {
	
    public ObtainCubeToSwitch() {
    	addSequential(new ElevatorToBottom());
        addSequential(new GrabberDown(true));
        addSequential(new GrabberIntakeTilCube());
        addSequential(new GrabberUp());
        addSequential(new WaitCommand(.1));
        addSequential(new GrabberClose());
        addSequential(new WaitCommand(.25));
        addSequential(new ElevatorToSwitch());
    }
}