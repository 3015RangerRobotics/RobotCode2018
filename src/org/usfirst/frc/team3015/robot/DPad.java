package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPad extends Button{
	private int dPadDegree;
	private XboxController controller;
	
	public enum Value{
		kDPadRight,
		kDPadUpRight,
		kDPadUp,
		kDPadUpLeft,
		kDPadLeft,
		kDPadDownLeft,
		kDPadDown,
		kDPadDownRight
	}
	
	public DPad(XboxController controller, Value value) {
		this.controller = controller;
		switch (value){
			case kDPadRight:
				this.dPadDegree = 90;
				break;
			case kDPadUpRight:
				this.dPadDegree = 45;
				break;
			case kDPadUp:
				this.dPadDegree = 0;
				break;
			case kDPadUpLeft:
				this.dPadDegree = 315;
				break;
			case kDPadLeft:
				this.dPadDegree = 270;
				break;
			case kDPadDownLeft:
				this.dPadDegree = 225;
				break;
			case kDPadDown:
				this.dPadDegree = 180;
				break;
			case kDPadDownRight:
				this.dPadDegree = 135;
				break;
			default:
				throw new AssertionError("Illegal value: " + value);
		}
	}

	@Override
	public boolean get() {
		return controller.getPOV() == dPadDegree;
	}

}
