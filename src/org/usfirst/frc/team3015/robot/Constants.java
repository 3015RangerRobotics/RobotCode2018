package org.usfirst.frc.team3015.robot;

public class Constants {
	public static final double kPeriod = 0.01;
	
	//Drive
	public static final int rightDriveMotor = 0;
	public static final int leftDriveMotor = 1;
	public static final int leftDriveEncoder1 = 0;
	public static final int leftDriveEncoder2 = 1;
	public static final int rightDriveEncoder1 = 2;
	public static final int rightDriveEncoder2 = 3;
	
	//Grabber
	public static final int intakeMotor = 2;
	public static final int grabberSolenoid1 = 0;
	public static final int grabberSolenoid2 = 1;
	public static final int grabberLimitSwitch1 = 5;
	public static final int grabberLimitSwitch2 = 6;
	
	//Compressor
	public static final int pressureSensor = 0;
	
	//Climber
	public static final int climberChildVictorSPX= 0;
	public static final int climberParentTalonSRX = 1;
	public static final int climberDigitalInputBottom = 2;
	public static final int climberDigitalInputMiddle = 3;
	public static final int climberDigitalInputTop = 4;
	
	//Buddy Climb
	public static final int buddySolenoid = 0;
	
	//Elevator
	public static final int elevatorTalonSRX = 1;
	public static final int elevatorPotentiometer = 6;
	public static final double elevatorMaxV = 0;
	public static final double elevatorAcc = 0;
	public static final double elevatorHeightBottom = 0;
	public static final double elevatorHeightSwitch = 0;
	public static final double elevatorHeightScale = 0;
	
}
