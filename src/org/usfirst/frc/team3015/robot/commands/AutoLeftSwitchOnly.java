package org.usfirst.frc.team3015.robot.commands;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoLeftSwitchOnly extends CommandGroup {

    public AutoLeftSwitchOnly() {
    	  addSequential(new GrabberClose());
          addParallel(new ElevatorToSwitch());
          addSequential(new DriveMotionProfile("leftSwitchOnly"));
          addSequential(new GrabberCubeEject());
          addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5, 14, 14, 100, true)));
          addParallel(new ElevatorToBottom());
          addSequential(new DriveTurnToAngleEncoders(54));
          addSequential(new ObtainCube(3.8));
          addSequential(new DriveMotionProfile(MotionProfiles.generate1D(2.6, 14, 14, 100, true)));
          addParallel(new ElevatorToSwitch());
          addSequential(new DriveTurnToAngleEncoders(-54));
          addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4.5, 14, 14, 100, false)));
          addSequential(new GrabberCubeEject());
          addParallel(new ElevatorToBottomDelayed(.25));
          addSequential(new DriveMotionProfile(MotionProfiles.generate1D(4, 14, 14, 100, true)));
          addSequential(new DriveTurnToAngleEncoders(40));
          addSequential(new ObtainCube(2.75));
          
//        addSequential(new GrabberClose());
//        addParallel(new ElevatorToSwitch());
//        addSequential(new DriveMotionProfile("leftSwitchOnly"));
//        addSequential(new GrabberCubeEject());
//        addParallel(new ElevatorToBottomDelayed(0.25));
//        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(5, 0, -57, 14, 14, 100, true)));
//        addSequential(new ObtainCube(3.8));
//        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(3.8, 0, 57, 14, 14, 100, true)));
//        addParallel(new ElevatorToSwitch());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(5.3, 14, 14, 100, false)));
//        addSequential(new GrabberCubeEject());
//        addParallel(new ElevatorToBottomDelayed(0.25));
//        addSequential(new DriveMotionProfile(MotionProfiles.generate2D(4.2, 0, -56, 14, 14, 100, true)));
//        addSequential(new ObtainCube(3.4));
//        addSequential(new DriveTurnToAngleEncoders(-75));        
//        addParallel(new ElevatorToSwitch());
//        addSequential(new DriveMotionProfile(MotionProfiles.generate1D(3, 14, 14, 100, false)));
//        addSequential(new GrabberCubeEject());
//        addParallel(new ElevatorToBottomDelayed(0.25));
    }
}
