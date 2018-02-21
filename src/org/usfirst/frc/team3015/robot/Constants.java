package org.usfirst.frc.team3015.robot;

public class Constants {
	public static final double kPeriod = 0.01;
	public static final double wheelBaseWidth = 2;
	
	//Drive
	public static final int rightDriveMotor = 0;
	public static final int leftDriveMotor = 1;
	public static final int leftDriveEncoder1 = 2;
	public static final int leftDriveEncoder2 = 3;
	public static final int rightDriveEncoder1 = 0;
	public static final int rightDriveEncoder2 = 1;
	
	//Grabber
	public static final int intakeMotor1 = 2;
	public static final int intakeMotor2 = 3;
	public static final int intakeAngler = 4;
	public static final int grabberSolenoid1 = 1;
	public static final int grabberSolenoid2 = 6;
	public static final int grabberEjectorSolenoid1 = 0;
	public static final int grabberEjectorSolenoid2 = 7;
	public static final int cubeDetector1 = 8;
	public static final int cubeDetector2 = 9;
	public static final int anglerPosUp = 7;
	public static final int anglerPosDown = 6;
	
	//Compressor
	public static final int pressureSensor = 0;
	
	//Climber
	public static final int climberParentTalonSRX = 1;
	public static final int climberChildVictorSPX = 2;
	public static final int climberLock = 5;
	
	//Buddy Climb
	public static final int buddySolenoid1 = 3;
	public static final int buddySolenoid2 = 4;
	
	//Elevator
	public static final int elevatorTalonSRX = 3;
	public static final double elevatorMaxV = 8.0;
	public static final double elevatorAcc = 8.0;
	public static final double elevatorHeightBottom = 0;
	public static final double elevatorHeightSwitch = 0;
	public static final double elevatorHeightScale = 0;
	
}
