package org.pihisamurai;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class GamepadReplay implements Gamepad {

	// For the change of buttons and the D-pad:
	private boolean[] lastPress;
	private int lastPOV;

	// Button constants on the gamepad
	private static final byte BUTTON_A = 1;
	private static final byte BUTTON_B = 2;
	private static final byte BUTTON_X = 3;
	private static final byte BUTTON_Y = 4;
	private static final byte BUTTON_LB = 5;
	private static final byte BUTTON_RB = 6;
	private static final byte BUTTON_BACK = 7;
	private static final byte BUTTON_START = 8;
	private static final byte BUTTON_LEFT_STICK_PUSH = 9;
	private static final byte BUTTON_RIGHT_STICK_PUSH = 10;

	// Axis constants
	private static final byte AXIS_LEFT_X = 0;
	private static final byte AXIS_LEFT_Y = 1;
	private static final byte AXIS_LEFT_TRIGGER = 2;
	private static final byte AXIS_RIGHT_TRIGGER = 3;
	private static final byte AXIS_RIGHT_X = 4;
	private static final byte AXIS_RIGHT_Y = 5;

	private Scanner scanner;
	private ArrayList<String[]> controlList = new ArrayList<String[]>();
	private int pointer = 0;

	// File format, saved periodicly, each save seperated by newline
	// miliseconds_into_round POV_ANGLE BUTTON1 BUTTON2 BUTTON3... AXIS0 AXIS1 AXIS2... AXIS5

	GamepadReplay(String fileName) {
		// checking for file
		try {
			scanner = new Scanner(new File(System.getProperty("user.home") + "/" + fileName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		lastPress = new boolean[11];
		for (byte i = 0; i < 11; i++) {
			lastPress[i] = false;
		}
		lastPOV = -1;

		controlList = readFile();
	}

	private ArrayList<String[]> readFile() {
		ArrayList<String[]> data = new ArrayList<String[]>();
		while (scanner.hasNextLine()) {
			data.add(scanner.nextLine().split("\\s+"));
		}
		return data;
	}

	private String[] getCurrentData() {
		if (Long.parseLong(controlList.get(pointer + 1)[0]) <= Robot.getInstance().modeTime()) {
			return controlList.get(pointer++);
		}
		return controlList.get(pointer);
	}

	public int getPOV() {
		return Integer.parseInt(getCurrentData()[1]);
	}

	private double getRawAxis(int axis) {
		assert(axis >= 0 && axis <= 5);
		return Double.parseDouble(getCurrentData()[axis + 12]);
	}

	private boolean getNumberedButton(byte button) {
		assert(button >= 1 && button <= 10);
		return Boolean.parseBoolean(getCurrentData()[button + 1]);
	}

	public double getLeftX() {
		return getRawAxis(AXIS_LEFT_X);
	}

	public double getLeftY() {
		return getRawAxis(AXIS_LEFT_Y);
	}

	public double getRightX() {
		return getRawAxis(AXIS_RIGHT_X);
	}

	public double getRightY() {
		return getRawAxis(AXIS_RIGHT_Y);
	}

	public double getRightTrigger() {
		return getRawAxis(AXIS_RIGHT_TRIGGER);
	}

	public double getLeftTrigger() {
		return getRawAxis(AXIS_LEFT_TRIGGER);
	}

	public boolean getButtonA() {
		return getNumberedButton(BUTTON_A);
	}

	public boolean getButtonB() {
		return getNumberedButton(BUTTON_B);
	}

	public boolean getButtonX() {
		return getNumberedButton(BUTTON_X);
	}

	public boolean getButtonY() {
		return getNumberedButton(BUTTON_Y);
	}

	public boolean getButtonBack() {
		return getNumberedButton(BUTTON_BACK);
	}

	public boolean getButtonStart() {
		return getNumberedButton(BUTTON_START);
	}

	public boolean getButtonLeftBack() {
		return getNumberedButton(BUTTON_LB);
	}

	public boolean getButtonRightBack() {
		return getNumberedButton(BUTTON_RB);
	}

	public boolean getButtonLeftStick() {
		return getNumberedButton(BUTTON_LEFT_STICK_PUSH);
	}

	public boolean getButtonRightStick() {
		return getNumberedButton(BUTTON_RIGHT_STICK_PUSH);
	}

	// Functions to check if the input has changed since last update.

	public boolean ifButtonAChange() {
		return getNumberedButton(BUTTON_A) != lastPress[BUTTON_A];
	}

	public boolean ifButtonBChange() {
		return getNumberedButton(BUTTON_B) != lastPress[BUTTON_B];
	}

	public boolean ifButtonXChange() {
		return getNumberedButton(BUTTON_X) != lastPress[BUTTON_X];
	}

	public boolean ifButtonYChange() {
		return getNumberedButton(BUTTON_Y) != lastPress[BUTTON_Y];
	}

	public boolean ifLeftBackChange() {
		return getNumberedButton(BUTTON_LB) != lastPress[BUTTON_LB];
	}

	public boolean ifRightBackChange() {
		return getNumberedButton(BUTTON_RB) != lastPress[BUTTON_RB];
	}

	public boolean ifButtonBackChange() {
		return getNumberedButton(BUTTON_BACK) != lastPress[BUTTON_BACK];
	}

	public boolean ifButtonLeftStickChange() {
		return getNumberedButton(BUTTON_LEFT_STICK_PUSH) != lastPress[BUTTON_LEFT_STICK_PUSH];
	}

	public boolean ifButtonRightStickChange() {
		return getNumberedButton(BUTTON_RIGHT_STICK_PUSH) != lastPress[BUTTON_RIGHT_STICK_PUSH];
	}

	public boolean ifPOVChange() {
		return this.getPOV() != lastPOV;
	}

	// Updates the array for the gamepad:
	public void update() {
		for (byte i = 1; i < 11; i++) {
			lastPress[i] = getNumberedButton(i);
		}
		lastPOV = this.getPOV();
	}
}
