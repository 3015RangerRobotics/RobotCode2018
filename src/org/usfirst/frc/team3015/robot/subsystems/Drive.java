package org.usfirst.frc.team3015.robot.subsystems;

import org.usfirst.frc.team3015.lib.android.TargetInfo;
import org.usfirst.frc.team3015.lib.android.TargetUpdate;
import org.usfirst.frc.team3015.lib.android.messages.TargetUpdateReceiver;
import org.usfirst.frc.team3015.robot.Constants;
import org.usfirst.frc.team3015.robot.DriveHelper;
import org.usfirst.frc.team3015.robot.DriveSignal;
import org.usfirst.frc.team3015.robot.commands.DriveWithGamepad;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drive extends Subsystem implements TargetUpdateReceiver{
	public final double kDriveP = 2.0;
	public final double kDriveD = 0.02;
	
	public final double kTurnPEncoder = 4.0;
	public final double kTurnIEncoder = 0.0;
	public final double kTurnDEncoder = 0.02;
	
	//TODO: Tune these
	public final double kTurnP = 0.018;
	public final double kTurnI = 0.0;
	public final double kTurnD = 0.06;
	
	public final double kV = 0.067;
	public final double kA = 0.023;
	
	public final double kDistancePerPulse = 0.00904774;
	
	private VictorSP leftDrive;
	private VictorSP rightDrive;
	private Encoder leftEncoder;
	private Encoder rightEncoder;
	public AHRS imu;
	
	public TargetInfo bestTarget = null;
	
	public Drive() {
		leftDrive = new VictorSP(Constants.leftDriveMotor);
		leftEncoder = new Encoder(Constants.leftDriveEncoder1, Constants.leftDriveEncoder2);
		leftDrive.setInverted(false);
		leftEncoder.setReverseDirection(false);
		leftEncoder.setDistancePerPulse(kDistancePerPulse);
		rightDrive = new VictorSP(Constants.rightDriveMotor);
		rightDrive.setInverted(true);
		rightEncoder = new Encoder(Constants.rightDriveEncoder1, Constants.rightDriveEncoder2);
		rightEncoder.setReverseDirection(true);
		rightEncoder.setDistancePerPulse(kDistancePerPulse);
		imu = new AHRS(I2C.Port.kOnboard);
		
		SmartDashboard.putData("Gyro", imu);
		SmartDashboard.putData("Left Encoder", leftEncoder);
		SmartDashboard.putData("Right Encoder", rightEncoder);
	}
	
	public void initDefaultCommand() {
        setDefaultCommand(new DriveWithGamepad());
    }
	
	@Override
	public void periodic() {
		
	}
	
	/**
	 * Reset the drive encoders
	 */
	public void resetEncoders() {
		leftEncoder.reset();
		rightEncoder.reset();
	}
    
	/**
	 * Set the drive motor outputs
	 * @param left Left motor output
	 * @param right Right motor output
	 */
    public void setMotorOutputs(double left, double right) {
    	leftDrive.set(left * 13.0 / RobotController.getBatteryVoltage());
    	rightDrive.set(right * 13.0 / RobotController.getBatteryVoltage());
    }
    
    /**
     * Drive with an arcade drive control
     * @param moveValue Forward/Reverse throttle
     * @param rotateValue Rate of rotation
     * @param squaredInputs Square the inputs for fine control
     */
    public void arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        DriveSignal ds = DriveHelper.arcadeDrive(moveValue, rotateValue, squaredInputs);
        setMotorOutputs(ds.leftSignal, ds.rightSignal);
    }
    
    /**
     * Drive with a curvature drive control
     * @param throttle Forward/Reverse throttle
     * @param turn Turn value
     * @param isQuickTurn Quick turn
     */
    public void curvatureDrive(double throttle, double turn, boolean isQuickTurn) {
    	DriveSignal ds = DriveHelper.curvatureDrive(throttle, turn, isQuickTurn);
    	setMotorOutputs(ds.leftSignal, ds.rightSignal);
    }
    
    /**
     * @return Left encoder distance
     */
    public double getLeftDistance() {
    	return leftEncoder.getDistance();
    }
    
    /**
     * @return Right encoder distance
     */
    public double getRightDistance() {
    	return rightEncoder.getDistance();
    }
    
    /**
     * @return Left encoder velocity
     */
    public double getLeftVelocity() {
    	return leftEncoder.getRate();
    }
    
    /**
     * @return Right encoder velocity
     */
    public double getRightVelocity() {
    	return rightEncoder.getRate();
    }
    
    /**
     * @return The angle of rotation from the imu
     */
    public double getAngle() {
    	return imu.getYaw();
    }
    
    /**
     * Reset the imu angle
     */
    public void resetGyro() {
    	imu.zeroYaw();
    }
    
	@Override
	public void onUpdateReceived(TargetUpdate update) {
		TargetInfo shortestDistance = null;
		for(TargetInfo target:update.getTargets()) {
			if(shortestDistance == null) {
				shortestDistance = target;
			}else if(target.getDistance() < shortestDistance.getDistance()){
				shortestDistance = target;
			}
		}
		if(shortestDistance != null)
			bestTarget = shortestDistance;
	}
}

