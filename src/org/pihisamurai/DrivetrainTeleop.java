package org.pihisamurai;

import edu.wpi.first.wpilibj.Jaguar;

public class DrivetrainTeleop implements Drivetrain{

	private Jaguar backLeftMotor;
	private Jaguar frontLeftMotor;
	private Jaguar backRightMotor;
	private Jaguar frontRightMotor;
 
	public DrivetrainTeleop() {
		backLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		backRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(3);
	}

	public void setLeft(double power)
	{
		backLeftMotor.set(-power);
		frontLeftMotor.set(-power);
	}
	
	public void setRight(double power)
	{
		backRightMotor.set(power);
		frontRightMotor.set(power);
	}

	public void disable() {
		backLeftMotor.set(0);
		frontLeftMotor.set(0);
	}
}
