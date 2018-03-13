package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.lib.android.AndroidServer;
import org.usfirst.frc.team3015.robot.Constants.AutoMode;
import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	Command autonomousCommand;
	Command leftScaleOnly,
		rightScaleOnly,
		leftBoth,
		rightBoth,
		leftRightBoth,
		rightLeftBoth,
		baseline;
	SendableChooser<AutoMode> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		this.setPeriod(Constants.kPeriod);
		
		CommandBase.init();
		
		leftScaleOnly = new AutoLeftScaleOnly();
		rightScaleOnly = new AutoRightScaleOnly();
		leftBoth = new AutoLeftScaleFirst();
		rightBoth = new AutoRightScaleFirst();
//		rightLeftBoth = new AutoRightLeftScaleFirst();
		rightLeftBoth = new AutoRightSwitchOnly();
		leftRightBoth = new AutoLeftRightSwitchFirst();
		baseline = new AutoBaseline();
		
		chooser.addDefault("None", AutoMode.kNone);
		chooser.addObject("Scale Only", AutoMode.kScaleOnly);
		chooser.addObject("Scale & Switch", AutoMode.kBoth);
		chooser.addObject("Baseline", AutoMode.kBaseline);
		
		SmartDashboard.putData("Auto Mode", chooser);
		
		AndroidServer server = AndroidServer.getInstance();
		server.addTargetUpdateReceiver(CommandBase.drive);
		
//		SmartDashboard.putData(new PowerDistributionPanel());
	}

	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Battery Voltage", RobotController.getBatteryVoltage());
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	}

	@Override
	public void autonomousInit() {
		CommandBase.drive.resetGyro();
		String gameData = DriverStation.getInstance().getGameSpecificMessage();
		AutoMode autoMode = chooser.getSelected();
		
		if(autoMode == AutoMode.kNone) {
			autonomousCommand = null;
		}else if(autoMode == AutoMode.kBaseline) {
			autonomousCommand = this.baseline;
		}else if(autoMode == AutoMode.kScaleOnly){
			if(gameData.charAt(1) == 'L') {
				autonomousCommand = this.leftScaleOnly;
			}else if(gameData.charAt(1) == 'R') {
				autonomousCommand = this.rightScaleOnly;
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}else if(autoMode == AutoMode.kBoth) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				autonomousCommand = this.leftRightBoth;
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				autonomousCommand = this.rightLeftBoth;
			}else if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'L'){
				autonomousCommand = this.leftBoth;
				System.out.println("what");
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'R'){
				autonomousCommand = this.rightBoth;
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}else {
			DriverStation.reportError("Auto Mode?", false);
			autonomousCommand = null;
		}

		if (autonomousCommand != null) {
			autonomousCommand.start();
		}else {
			DriverStation.reportWarning("No Auto Command, Doing Nothing!", false);
		}
		
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Match Time", DriverStation.getInstance().getMatchTime());
	}

	@Override
	public void teleopInit() {
		CommandBase.drive.resetGyro();
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
