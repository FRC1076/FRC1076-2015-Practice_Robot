package org.pihisamurai;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public Drivetrain drivetrain;
	public Manipulator manipulator;

	public Teleoperated teleop;
	
	private static Robot robot;
	public Gamepad gamepad;
	public Gamepad gamepad2;

	CameraServer server;

	// int session;
	// Image frame;

	private long modeStart;

	// Returns time since mode was enabled
	public long modeTime() {
		return (System.nanoTime() - modeStart) / 1000000;
	}

	public void robotInit() {

		SmartDashboard.putString("Gamepad Read File", "file");
		robot = this;
		System.out.println("Robot Code Started");

		SmartDashboard.putNumber("Autonomous Mode", 0);

		/*
		 * frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		 * 
		 * // the camera name (ex "cam0") can be found through the roborio web interface
		 * session = NIVision.IMAQdxOpenCamera("cam0",
		 * NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		 * NIVision.IMAQdxConfigureGrab(session);
		 */

		drivetrain = new Drivetrain(this);
		manipulator = new Manipulator(this);
		teleop = new Teleoperated(this);

		// camGet.start();
	}

	/*
	 * Thread camGet = new Thread(new Runnable(){
	 * public void run() {
	 * NIVision.IMAQdxStartAcquisition(session);
	 * while(true){
	 * NIVision.IMAQdxGrab(session, frame, 1);
	 * CameraServer.getInstance().setImage(frame);
	 * try {
	 * Thread.sleep(200);
	 * } catch (InterruptedException e) {
	 * e.printStackTrace();
	 * }
	 * }
	 * //NIVision.IMAQdxStopAcquisition(session);
	 * }
	 * });
	 */

	// The initial function called on start of disabled mode

	public void disabledInit() {
		System.out.println("Robot Disabled");
	}

	// The function called roughly every twenty milliseconds during disabled mode

	public void disabledPeriodic() {
	}

	public static Robot getInstance() {
		return robot;
	}

	// The initial function called on start of autonomous mode

	public void autonomousInit() {
		System.out.println("Autonomous Mode");

		modeStart = System.nanoTime();
		gamepad = new GamepadReplay(SmartDashboard.getString("Gamepad Read File") + "-driver.gamepad");
		gamepad2 = new GamepadReplay(SmartDashboard.getString("Gamepad Read File") + "-operator.gamepad");

	}

	// The function called roughly every twenty milliseconds during disabled mode

	public void autonomousPeriodic() {
		teleop.run();

		gamepad.update();
		gamepad2.update();

	}

	// The initial function called on start of teleop

	public void teleopInit() {
		modeStart = System.nanoTime();

		gamepad = new GamepadReal(0, SmartDashboard.getString("Gamepad Read File") + "-driver.gamepad");
		gamepad2 = new GamepadReal(1, SmartDashboard.getString("Gamepad Read File") + "-operator.gamepad");

		System.out.println("Teleoperated Mode");
		teleop.init();
	}

	// The function called roughly every twenty milliseconds during teleop

	public void teleopPeriodic() {
		teleop.run();

		gamepad.update();
		gamepad2.update();

		// drivetrain.update();
	}

	public void testInit() {
		System.out.println("Test Mode");
	}

	public void testPeriodic() {
	}
}
