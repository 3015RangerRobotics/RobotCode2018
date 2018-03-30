package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.robot.Constants.AutoMode;
import org.usfirst.frc.team3015.robot.Constants.Side;
import org.usfirst.frc.team3015.robot.commands.AutoBaseline;
import org.usfirst.frc.team3015.robot.commands.AutoLeftSwitchOnly;
import org.usfirst.frc.team3015.robot.commands.AutoOppositeScaleOnly;
import org.usfirst.frc.team3015.robot.commands.AutoRightSwitchOnly;
import org.usfirst.frc.team3015.robot.commands.AutoSameScaleOnly;
import org.usfirst.frc.team3015.robot.commands.CommandBase;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	Command autonomousCommand;
	Command leftScaleLeftStart,
		rightScaleRightStart,
		rightScaleLeftStart,
		leftScaleRightStart,
		leftSwitchMiddleStart,
		rightSwitchMiddleStart,
		baseline;
	SendableChooser<AutoMode> autoChooser = new SendableChooser<>();
	SendableChooser<Side> sideChooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		this.setPeriod(Constants.kPeriod);
		
		CommandBase.init();
		
		leftScaleLeftStart = new AutoSameScaleOnly(false);
		rightScaleRightStart = new AutoSameScaleOnly(true);
		rightScaleLeftStart = new AutoOppositeScaleOnly(false);
		leftScaleRightStart = new AutoOppositeScaleOnly(true);
		leftSwitchMiddleStart = new AutoLeftSwitchOnly();
		rightSwitchMiddleStart = new AutoRightSwitchOnly();
		baseline = new AutoBaseline();
		
//		chooser.addDefault("None", AutoMode.kNone);
		autoChooser.addObject("Scale Only", AutoMode.kScaleOnly);
		autoChooser.addObject("Switch Only", AutoMode.kSwitchOnly);
		autoChooser.addObject("Scale & Switch", AutoMode.kBoth);
		autoChooser.addDefault("Baseline", AutoMode.kBaseline);
		SmartDashboard.putData("Auto Mode", autoChooser);
		
		sideChooser.addObject("Left Start", Side.kLeft);
		sideChooser.addObject("Right Start", Side.kRight);
		SmartDashboard.putData("Start Side", sideChooser);
		
//		AndroidServer server = AndroidServer.getInstance();
//		server.addTargetUpdateReceiver(CommandBase.drive);
		
		UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
		camera.setFPS(30);
		camera.setResolution(400, 300);
	}

	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	}

	@Override
	public void autonomousInit() {
		CommandBase.drive.resetGyro();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		AutoMode autoMode = autoChooser.getSelected();
		Side startSide = sideChooser.getSelected();
		
		if(autoMode == AutoMode.kBaseline) {
			autonomousCommand = this.baseline;
		}else if(autoMode == AutoMode.kScaleOnly){
			if(gameData.charAt(1) == 'L') {
				if(startSide == Side.kLeft) {
					autonomousCommand = this.leftScaleLeftStart;
				}else if(startSide == Side.kRight){
					autonomousCommand = this.leftScaleRightStart;
				}
			}else if(gameData.charAt(1) == 'R') {
				if(startSide == Side.kLeft) {
					autonomousCommand = this.rightScaleLeftStart;
				}else if(startSide == Side.kRight){
					autonomousCommand = this.rightScaleRightStart;
				}
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}else if(autoMode == AutoMode.kSwitchOnly) {
			if(gameData.charAt(0) == 'L') {
				autonomousCommand = this.leftSwitchMiddleStart;
			}else if(gameData.charAt(0) == 'R') {
				autonomousCommand = this.rightSwitchMiddleStart;
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}
		else {
			DriverStation.reportError("No Auto Mode Selected", false);
			autonomousCommand = null;
		}
		
		if (autonomousCommand != null) {
			autonomousCommand.start();
		}else {
			DriverStation.reportError("No Auto Command, Driving to Baseline!", false);
			autonomousCommand = this.baseline;
			autonomousCommand.start();
		}
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	}

	@Override
	public void teleopInit() {
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	}

	@Override
	public void testPeriodic() {
		
	}
}
