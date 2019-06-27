package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.SpeedModifier;
import frc.robot.commands.Drive;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem implements PIDOutput {
    SpeedModifier m_SpeedModifier = SpeedModifier.FAST;
	PIDController controller;
	double pidOutput;
	
    public Drivetrain() {
    	controller = new PIDController(0, 0, 0, Robot.m_navX, this);
	}

	// Put methods for controlling this subsystem

	public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new Drive());
    }
    
    public void driveRobotTank(double leftSpeed, double rightSpeed) {
    	RobotMap.chassis.tankDrive(leftSpeed * m_SpeedModifier.getValue(), rightSpeed * m_SpeedModifier.getValue());
    }
    
    public void driveRobotPID(double speed) {
    	RobotMap.chassis.tankDrive((speed + (pidOutput / 4d)) * m_SpeedModifier.getValue(), (speed + (pidOutput / 4d)) * m_SpeedModifier.getValue());
    }
    
    public void turnRobotPID(double speed) {
    	RobotMap.chassis.tankDrive(pidOutput * speed *m_SpeedModifier.getValue(), pidOutput * speed *m_SpeedModifier.getValue());
    }
    
    public void driveRobotArcade(double move, double turn) {
    	RobotMap.chassis.arcadeDrive(move *m_SpeedModifier.getValue(), turn *m_SpeedModifier.getValue());
    }
    
    public double getLeftEncoderDistance() {
    	return RobotMap.leftEncoder.getDistance();
    }
    
    public double getRightEncoderDistance() {
    	return RobotMap.rightEncoder.getDistance();
    }
    
    public int getLeftEncoderValue() {
        return RobotMap.leftEncoder.get();
    }

    public int getRightEncoderValue() {
        return RobotMap.rightEncoder.get();
    }

    public void resetEncoders() {
    	RobotMap.leftEncoder.reset();
    	RobotMap.rightEncoder.reset();
    }
    
    public DoubleSolenoid.Value getShifterPos() {
    	return RobotMap.shifter.get();
    }
    
    public void shift(DoubleSolenoid.Value shiftPos) {
    	RobotMap.shifter.set(shiftPos);
    }

	@Override
	public void pidWrite(double output) {
		pidOutput = output;
    }
    
    public void setSpeedModifier(SpeedModifier speedModifier) {
        m_SpeedModifier = speedModifier;
    }
}

