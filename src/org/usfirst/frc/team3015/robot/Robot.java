package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.lib.android.AndroidServer;
import org.usfirst.frc.team3015.robot.commands.CommandBase;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

public class Robot extends TimedRobot {
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	public static volatile boolean isEnabled = false;

	@Override
	public void robotInit() {
		this.setPeriod(Constants.kPeriod);
		
//		chooser.addDefault("Auto", new Auto());
//		chooser.addObject("My Auto", new MyAutoCommand());
//		SmartDashboard.putData("Auto mode", chooser);
		
		CommandBase.init();
		
		AndroidServer server = AndroidServer.getInstance();
		server.addTargetUpdateReceiver(CommandBase.drive);
	}

	@Override
	public void disabledInit() {
		isEnabled = false;
		CommandBase.drive.test.set(false);
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		isEnabled = true;
		autonomousCommand = chooser.getSelected();

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		isEnabled = true;
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
		CommandBase.drive.test.set(true);
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		
	}
}
