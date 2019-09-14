/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive;
import frc.robot.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
	public static Limelight m_limelight;
	public static NavX m_navX;
	public static Drivetrain m_drivetrain;
	public static Elevator m_elevator;
	public static Intake m_intake;
	public static OI m_oi;

	Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	SendableChooser<Double> m_slowifyChooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		//chooser.addObject("My Auto", new MyAutoCommand());
		m_slowifyChooser.addOption("100%", 1.0);
		m_slowifyChooser.addOption("90%", 0.9);
		m_slowifyChooser.addOption("80%", 0.8);
		m_slowifyChooser.addOption("70%", 0.7);
		m_slowifyChooser.addOption("60%", 0.6);
		m_slowifyChooser.addOption("50%", 0.5);
		m_slowifyChooser.setDefaultOption("100%", 1.0);
		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Slowify", m_slowifyChooser);
		RobotMap.Init();
		m_limelight = new Limelight();
		m_navX = new NavX();
		m_drivetrain = new Drivetrain();
		m_elevator = new Elevator();
		m_intake = new Intake();
		m_oi = new OI();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		m_drivetrain.resetEncoders();
		m_elevator.resetEncoder();
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		m_autonomousCommand = m_chooser.getSelected();
		m_drivetrain.resetEncoders();
		m_elevator.resetEncoder();

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		m_drivetrain.resetEncoders();
		m_elevator.resetEncoder();
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Left Encoder", m_drivetrain.getLeftEncoderDistance());
		SmartDashboard.putNumber("Right Encoder", m_drivetrain.getRightEncoderDistance());
		SmartDashboard.putNumber("Elevator Encoder", m_elevator.getLiftHeight());
		SmartDashboard.putBoolean("Intake Limit", m_intake.getLimitSwitchValue());
		SmartDashboard.putBoolean("Elevator Limit", m_elevator.getLimitSwitchValue());
		SmartDashboard.putNumber("NavX Yaw", RobotMap.navX.getYaw());
		SmartDashboard.putNumber("NavX Roll", RobotMap.navX.getRoll());
		SmartDashboard.putNumber("NavX Pitch", RobotMap.navX.getPitch());
		SmartDashboard.putBoolean("limit switch", m_elevator.getLimitSwitchValue());
		SmartDashboard.putBoolean("Limelight Has Target", m_limelight.hasTarget());

		SmartDashboard.putNumber("DFL", RobotMap.frontLeftMotor.get());
		SmartDashboard.putNumber("DBL", RobotMap.rearLeftMotor.get());
		SmartDashboard.putNumber("DFR", RobotMap.frontRightMotor.get());
		SmartDashboard.putNumber("DBR", RobotMap.rearRightMotor.get());

		double slowify = m_slowifyChooser.getSelected() == null ? 1.0 : m_slowifyChooser.getSelected();
		Drive.setSlowify(slowify);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
