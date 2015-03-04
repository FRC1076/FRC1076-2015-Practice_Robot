package org.pihisamurai;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Drivetrain {

	private Jaguar backLeftMotor;
	private Jaguar frontLeftMotor;
	private Jaguar backRightMotor;
	private Jaguar frontRightMotor;
	private Jaguar strafeMotor;

	private Robot robot;
 
	public Drivetrain(Robot r) {
		robot = r;

		backLeftMotor = new Jaguar(0);
		frontLeftMotor = new Jaguar(1);
		backRightMotor = new Jaguar(2);
		frontRightMotor = new Jaguar(3);
		strafeMotor = new Jaguar(4);
	}

	public void setStrafe(double power) {
		strafeMotor.set(power);
	}
	
	public void move(double power)
	{
		setLeftMotorPower(power);
		setRightMotorPower(power);
	}

	public void setLeftMotorPower(double power)
	{
		backLeftMotor.set(-power);
		frontLeftMotor.set(-power);
	}
	
	public void setRightMotorPower(double power)
	{
		backRightMotor.set(power);
		frontRightMotor.set(power);
	}
}
