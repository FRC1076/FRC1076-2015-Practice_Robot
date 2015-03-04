package org.pihisamurai;

public class Teleoperated {

	// A "robot" variable to access methods therein
	private Robot robot;
	// For detecting button change on the D-pad of gamepad 1
	private int lastPOV = Gamepad.POV_OFF;

	public Teleoperated(Robot r) {
		// Initialization of variable values:
		this.robot = r;
	}

	public void init() {
	}

	public void run() {
		robot.drivetrain.setLeftMotorPower(robot.gamepad.getLeftY());
		robot.drivetrain.setRightMotorPower(0.75*robot.gamepad.getRightY());
		robot.drivetrain.setStrafe(robot.gamepad.getRightX());
		// Decides which gamepad is pressing the triggers the hardest;
		// Said gamepad controls the lift.
		if (Math.abs(robot.gamepad.getLeftTrigger() - robot.gamepad.getRightTrigger()) > Math.abs(robot.gamepad2
				.getLeftTrigger() - robot.gamepad2.getRightTrigger())) {
			// For gamepad 1:
			robot.manipulator.liftPower((robot.gamepad.getLeftTrigger() - robot.gamepad.getRightTrigger()) * 2);
		} else {
			// For gamepad 2:
			robot.manipulator.liftPower((robot.gamepad2.getLeftTrigger() - robot.gamepad2.getRightTrigger()) * 2);
		}

		
	}
}
