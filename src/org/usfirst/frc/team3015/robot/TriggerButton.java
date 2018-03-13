package org.usfirst.frc.team3015.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class TriggerButton extends Button{
	private XboxController controller;
	private Hand hand;
	
	/**
	 * Creates a button using a trigger on a controller
	 * @param controller The controller to use
	 * @param hand The left/right side of the controller
	 */
	public TriggerButton(XboxController controller, Hand hand) {
		this.controller = controller;
		this.hand = hand;
	}

	@Override
	public boolean get() {
		return controller.getTriggerAxis(hand) >= 0.5;
	}

}
