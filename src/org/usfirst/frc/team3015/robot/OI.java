package org.usfirst.frc.team3015.robot;

import org.usfirst.frc.team3015.motionProfiles.MotionProfiles;
import org.usfirst.frc.team3015.robot.commands.*;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	//Buttons for driver
	XboxController driver = new XboxController(0);
	Button driverA1 = new JoystickButton(driver, 1);
	Button driverB2 = new JoystickButton(driver, 2);
	Button driverX3 = new JoystickButton(driver, 3);
	Button driverY4 = new JoystickButton(driver, 4);
	Button driverLB5 = new JoystickButton(driver, 5);
	Button driverRB6 = new JoystickButton(driver, 6);
	Button driverSEL7 = new JoystickButton(driver, 7);
	Button driverSTART8 = new JoystickButton(driver, 8);
	Button driverLS9 = new JoystickButton(driver, 9);
	Button driverRS10 = new JoystickButton(driver, 10);
	Button driverDLeft = new DPad(driver, DPad.Value.kDPadLeft);
	Button driverDUp = new DPad(driver, DPad.Value.kDPadUp);
	Button driverDDown = new DPad(driver, DPad.Value.kDPadDown);
	Button driverDRight = new DPad(driver, DPad.Value.kDPadRight);
	Button driverLTrigger = new TriggerButton(driver, Hand.kLeft);
	Button driverRTrigger = new TriggerButton(driver, Hand.kRight);
	//Buttons for coDriver
	XboxController coDriver = new XboxController(1);
	Button coDriverA1 = new JoystickButton(coDriver, 1);
	Button coDriverB2 = new JoystickButton(coDriver, 2);
	Button coDriverX3 = new JoystickButton(coDriver, 3);
	Button coDriverY4 = new JoystickButton(coDriver, 4);
	Button coDriverLB5 = new JoystickButton(coDriver, 5);
	Button coDriverRB6 = new JoystickButton(coDriver, 6);
	Button coDriverSEL7 = new JoystickButton(coDriver, 7);
	Button coDriverSTART8 = new JoystickButton(coDriver, 8);
	Button coDriverLS9 = new JoystickButton(coDriver, 9);
	Button coDriverRS10 = new JoystickButton(coDriver, 10);
	Button coDriverDLeft = new DPad(coDriver, DPad.Value.kDPadLeft);
	Button coDriverDUp = new DPad(coDriver, DPad.Value.kDPadUp);
	Button coDriverDDown = new DPad(coDriver, DPad.Value.kDPadDown);
	Button coDriverDRight = new DPad(coDriver, DPad.Value.kDPadRight);
	Button coDriverLTrigger = new TriggerButton(coDriver, Hand.kLeft);
	Button coDriverRTrigger = new TriggerButton(coDriver, Hand.kRight);
	
	Button bothSelects = new DoubleButton(driverSEL7, coDriverSEL7);
	
	public OI() {
//		driverA1.whenPressed(new DriveMotionProfile("wallToRightSwitch"));
//		driverA1.whenPressed(new GrabberClose());
//		driverB2.whenPressed(new GrabberOpen());
//		driverX3.whenPressed(new GrabberEjectorIn());
//		driverY4.whenPressed(new GrabberEjectorOut());
		
		driverA1.whenPressed(new GrabberCubeControl());
		driverA1.whenReleased(new GrabberCubeUp());
		driverB2.whileHeld(new GrabberIntakeTilCube());
		driverX3.whenPressed(new GrabberOpen());
		driverY4.whileHeld(new GrabberIntakeOut());
		driverLB5.whenPressed(new GrabberCubeEject());
		driverLB5.whenReleased(new GrabberReset());
		driverRB6.whenPressed(new GrabberUp());
		driverRTrigger.whenPressed(new GrabberDown(true));
		driverLTrigger.whenPressed(new GrabberClose());
		
		coDriverDUp.whileHeld(new ClimberUp());
		coDriverDDown.whileHeld(new ClimberDown());
		coDriverDRight.whenPressed(new ClimberLockOut());
		coDriverDLeft.whenPressed(new ClimberLockIn());
		coDriverX3.whileHeld(new ClimberHold());
				
		coDriverA1.whenPressed(new ElevatorToBottom());
		coDriverB2.whenPressed(new ElevatorToSwitch());
		coDriverY4.whenPressed(new ElevatorToScale());
		coDriverLS9.whileHeld(new ElevatorManualControl());
		
		bothSelects.whenPressed(new BuddyExtend());
		bothSelects.whenReleased(new BuddyRetract());
	}
	
	public boolean getDriverAButton() {
		return driverA1.get();
	}
	
	public boolean getDriverLB() {
		return driverLB5.get();
	}
	
	public double getCoDriverSumTriggers(){
		return coDriver.getTriggerAxis(Hand.kRight) - coDriver.getTriggerAxis(Hand.kLeft);
	}
	
	public boolean getCoDriverLeftStickButton() {
		return coDriver.getStickButton(Hand.kLeft);
	}
	
	public double getDriverLeftStickY() {
		if(Math.abs(driver.getY(Hand.kLeft)) > 0.05) {
			return -driver.getY(Hand.kLeft);
		}else {
			return 0;
		}
	}
	
	public double getCoDriverLeftStickY() {
		if(Math.abs(coDriver.getY(Hand.kLeft)) > 0.05) {
			return -coDriver.getY(Hand.kLeft);
		}else {
			return 0;
		}
	}
	
	public double getDriverRightStickX() {
		return driver.getX(Hand.kRight);
	}
	
	public double getDriverLeftStickX() {
		return driver.getX(Hand.kLeft);
	}
	
	/**
	 * Rumble driver's joypad
	 * In the case of Xbox joypads, this is a stronger rumble
	 * @param value Strength of rumble
	 */
	public void driverRumble(double value) {
		driver.setRumble(RumbleType.kRightRumble, value);
		driver.setRumble(RumbleType.kLeftRumble, value);
	}
	
	/**
	 * Rumble codriver's joypad
	 * In the case of Xbox joypads, this is a softer rumble
	 * @param value Strength of rumble
	 */
	public void coDriverRumble(double value) {
		coDriver.setRumble(RumbleType.kRightRumble, value);
		coDriver.setRumble(RumbleType.kLeftRumble, value);
	}
}
