package org.usfirst.frc.team3015.robot;

public class DriveHelper {
	private static final double kDeadband = 0.02;
	private static final double kTurnSensitivity = 1.0;
	
	private static double quickStopAccumulator = 0.0;
	
	/**
	 * Tank drive helper
	 * @param left The signal for the left motors
	 * @param right The signal to the right motors
	 * @return Outputs for left and right motors
	 */
	public static DriveSignal tankDrive(double left, double right) {
		return new DriveSignal(left, right);
    }
    
	/**
	 * Arcade drive
	 * @param moveValue Forward/Reverse throttle
	 * @param rotateValue The rate of rotation
	 * @param squaredInputs Square inputs to increase fine control
	 * @return The left and right motor outputs
	 */
    public static DriveSignal arcadeDrive(double moveValue, double rotateValue, boolean squaredInputs) {
        // local variables to hold the computed PWM values for the motors
        double leftMotorSpeed;
        double rightMotorSpeed;
        moveValue = handleDeadzone(moveValue, kDeadband);
        rotateValue = -handleDeadzone(rotateValue, kDeadband);
        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
        	if (moveValue >= 0.0) {
        		moveValue = moveValue * moveValue;
        	} else {
	            moveValue = -(moveValue * moveValue);
	        }
	        if (rotateValue >= 0.0) {
	        	rotateValue = rotateValue * rotateValue;
	        } else {
	        	rotateValue = -(rotateValue * rotateValue);
	        }
        }

        if (moveValue > 0.0) {
        	if (rotateValue > 0.0) {
        		leftMotorSpeed = moveValue - rotateValue;
        		rightMotorSpeed = Math.max(moveValue, rotateValue);
	        } else {
	        	leftMotorSpeed = Math.max(moveValue, -rotateValue);
	        	rightMotorSpeed = moveValue + rotateValue;
	        }
	    } else {
	    	if (rotateValue > 0.0) {
	    		leftMotorSpeed = -Math.max(-moveValue, rotateValue);
	    		rightMotorSpeed = moveValue + rotateValue;
	        } else {
	        	leftMotorSpeed = moveValue - rotateValue;
	        	rightMotorSpeed = -Math.max(-moveValue, -rotateValue);
	        }
	    }	
	    return new DriveSignal(leftMotorSpeed, rightMotorSpeed);
    }
    
    /**
     * Curvature drive helper (Stolen from 254)
     * @param throttle Forward/Reverse throttle
     * @param turn Curvature of robot
     * @param isQuickTurn Quick turn
     * @return The left and right motor outputs
     */
    public static DriveSignal curvatureDrive(double throttle, double turn, boolean isQuickTurn, boolean squaredInputs) {
    	throttle = handleDeadzone(throttle, kDeadband);
    	turn = handleDeadzone(turn, kDeadband);
    	
    	double overPower;
    	double angularPower;
    	
    	if (squaredInputs) {
            // square the inputs (while preserving the sign) to increase fine control
            // while permitting full power
          	if (throttle >= 0.0) {
          		throttle = throttle * throttle;
          	} else {
          		throttle = -(throttle * throttle);
  	        }
          }
    	
    	if(isQuickTurn) {
    		if(Math.abs(throttle) < 0.2) {
    			double alpha = 0.1;
    			quickStopAccumulator = (1 - alpha) * quickStopAccumulator + alpha * limit(turn, 1.0) * 2;
    		}
    		overPower = 1.0;
    		angularPower = turn;
    	}else {
    		overPower = 0.0;
    		angularPower = Math.abs(throttle) * turn * kTurnSensitivity - quickStopAccumulator;
    		if(quickStopAccumulator > 1) {
    			quickStopAccumulator -= 1;
    		}else if(quickStopAccumulator < -1) {
    			quickStopAccumulator += 1;
    		}else {
    			quickStopAccumulator = 0.0;
    		}
    	}
    	
    	double rightPwm = throttle - angularPower;
    	double leftPwm = throttle + angularPower;
    	if (leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if (rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if (leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if (rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }
    	
    	return new DriveSignal(leftPwm, rightPwm);
    }
    
    /**
     * Handles a deadzone
     * @param value The value to handle
     * @param deadzone The deadzone
     * @return The handled value
     */
    protected static double handleDeadzone(double value, double deadzone) {
    	return (Math.abs(value) > Math.abs(deadzone)) ? limit(value, 1.0) : 0.0;
    }
    
    /**
     * Limits a number between a given range
     * @param value The value to limit
     * @param max The absolute value of the maximum value
     * @return The limited value
     */
    protected static double limit(double value, double max) {
        if (value > max) {
        	return max;
        }
        if (value < -max) {
        	return -max;
        }
        return value;
    }
}
