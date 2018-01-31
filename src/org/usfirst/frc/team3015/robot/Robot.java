package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.robot.commands.CommandBase;

import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
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
		CommandBase.init();
//		chooser.addDefault("Auto", new Auto());
//		chooser.addObject("My Auto", new MyAutoCommand());
//		SmartDashboard.putData("Auto mode", chooser);
		this.setPeriod(Constants.kPeriod);
		
//		AndroidServer server = AndroidServer.getInstance();
//		server.addTargetUpdateReceiver(CommandBase.drive);
		
//		UsbCamera camera = new UsbCamera("USB Camera 0", 0);
//		MjpegServer server = new MjpegServer("server_USB Camera 0", 5800);
//		server.setSource(camera);
	}

	@Override
	public void disabledInit() {
		isEnabled = false;
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
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
		
	}
}
