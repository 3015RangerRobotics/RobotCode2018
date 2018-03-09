package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.lib.android.AndroidServer;
import org.usfirst.frc.team3015.robot.Constants.AutoMode;
import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	Command autonomousCommand;
	Command leftScaleOnly,
		rightScaleOnly,
		leftSwitchOnly,
		rightSwitchOnly,
		bothLeftScalePriority,
		bothRightScalePriority,
		bothLeftSwitchPriority,
		bothRightSwitchPriority,
		rightLeftScalePriority,
		leftRightScalePriority,
		rightLeftSwitchPriority,
		leftRightSwitchPriority,
		baseline;
	SendableChooser<AutoMode> chooser = new SendableChooser<>();

	@Override
	public void robotInit() {
		this.setPeriod(Constants.kPeriod);
		
		CommandBase.init();
		
		leftScaleOnly = new AutoLeftScaleOnly();
		rightScaleOnly = new AutoRightScaleOnly();
		leftSwitchOnly = new AutoLeftSwitchOnly();
		rightSwitchOnly = null;
		bothLeftScalePriority = new AutoLeftScalePriority();
		bothRightScalePriority = new AutoRightScalePriority();
		bothLeftSwitchPriority = null;
		bothRightSwitchPriority = null;
		rightLeftScalePriority = new AutoRightLeftScalePriority();
		leftRightScalePriority = null;
		rightLeftSwitchPriority = null;
		leftRightSwitchPriority = new AutoLeftRightSwitchPriority();
		baseline = new AutoBaseline();
		
		chooser.addDefault("None", AutoMode.kNone);
		chooser.addObject("Scale Only", AutoMode.kScaleOnly);
		chooser.addObject("Switch Only", AutoMode.kSwitchOnly);
		chooser.addObject("Scale Priority", AutoMode.kScalePriority);
		chooser.addObject("Switch Priority", AutoMode.kSwitchPriority);
		chooser.addObject("Baseline", AutoMode.kBaseline);
		
		SmartDashboard.putData("Auto Mode", chooser);
		
		AndroidServer server = AndroidServer.getInstance();
		server.addTargetUpdateReceiver(CommandBase.drive);
		
		SmartDashboard.putData(new PowerDistributionPanel());
	}

	@Override
	public void disabledInit() {
		
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
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
		}else if(autoMode == AutoMode.kSwitchOnly) {
			if(gameData.charAt(0) == 'L') {
				autonomousCommand = this.leftSwitchOnly;
			}else if(gameData.charAt(0) == 'R') {
				autonomousCommand = this.rightSwitchOnly;
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}else if(autoMode == AutoMode.kScalePriority) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				autonomousCommand = this.leftRightScalePriority;
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				autonomousCommand = this.rightLeftScalePriority;
			}else {
				DriverStation.reportError("Invalid Game Data!", false);
				autonomousCommand = null;
			}
		}else if(autoMode == AutoMode.kSwitchPriority) {
			if(gameData.charAt(0) == 'L' && gameData.charAt(1) == 'R') {
				autonomousCommand = this.leftRightSwitchPriority;
			}else if(gameData.charAt(0) == 'R' && gameData.charAt(1) == 'L') {
				autonomousCommand = this.rightLeftSwitchPriority;
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
	}

	@Override
	public void testPeriodic() {
		
	}
}
