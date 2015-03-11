package org.pihisamurai;

import edu.wpi.first.wpilibj.SerialPort;

public class LEDController {

	private byte leftDirection;
	private byte rightDirection;
	private byte liftDirection;
	private boolean boot;
	private boolean disable;

	private SerialPort serial;

	LEDController() {
		leftDirection = 0;
		rightDirection = 0;
		liftDirection = 0;
		boot = false;
	}

	public void setLeftLED(byte direction) {
		leftDirection = direction;
	}

	public void setRightLED(byte direction) {
		rightDirection = 0;
	}

	public void setLiftLED(byte direction) {
		liftDirection = 0;
	}

	public void update() {
		byte byteSend = 0;
		if (leftDirection > 0) {
			byteSend |= 1 << 0;
		}
		if (leftDirection < 0) {
			byteSend |= 1 << 1;
		}
		if (rightDirection > 0) {
			byteSend |= 1 << 2;
		}
		if (rightDirection < 0) {
			byteSend |= 1 << 3;
		}
		if (liftDirection > 0) {
			byteSend |= 1 << 4;
		}
		if (liftDirection < 0) {
			byteSend |= 1 << 5;
		}
		if (boot) {
			byteSend |= 1 << 6;
		}
		if (disable) {
			byteSend |= 1 << 7;
		}
		serial.write(new byte[] { byteSend }, 1);
	}
}
