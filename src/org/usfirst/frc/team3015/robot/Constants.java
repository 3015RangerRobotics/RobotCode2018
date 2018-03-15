package org.usfirst.frc.team3015.robot;

public class Constants {
	public static final double kPeriod = 0.01;
	public static final double wheelBaseWidth = 2;
	
	//PWM Motor Controllers
	public static final int rightDriveMotor = 0;
	public static final int leftDriveMotor = 1;
	public static final int intakeMotor1 = 2;
	public static final int intakeMotor2 = 3;
	public static final int intakeAngler = 4;
	
	//CAN Motor Controllers
	public static final int climberParentTalonSRX = 1;
	public static final int climberChildVictorSPX = 2;
	public static final int elevatorTalonSRX = 3;
	
	//Encoders
	public static final int rightDriveEncoder1 = 0;
	public static final int rightDriveEncoder2 = 1;
	public static final int leftDriveEncoder1 = 2;
	public static final int leftDriveEncoder2 = 3;
	
	//Solenoids
	public static final int grabberSolenoid1 = 0;
	public static final int grabberEjectorSolenoid1 = 1;
	public static final int buddySolenoid1 = 2;
	public static final int climberLock1 = 3;
	public static final int climberLock2 = 4;
	public static final int buddySolenoid2 = 5;
	public static final int grabberEjectorSolenoid2 = 6;
	public static final int grabberSolenoid2 = 7;
	
	//DIO
	public static final int elevatorBottomLimit = 4;
	public static final int anglerPosDown = 6;
	public static final int anglerPosUp = 7;
	
	//AIO
	public static final int pressureSensor = 0;
	public static final int cubeDetector1 = 1;
	public static final int cubeDetector2 = 2;
	
	//Auto Modes
	public enum AutoMode{
		kScaleOnly,
		kBoth,
		kBaseline,
		kNone
	}
}
