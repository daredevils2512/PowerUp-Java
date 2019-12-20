package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {

private final WPI_TalonSRX leftFrontmotor;
private final WPI_TalonSRX rightFrontmotor;
private final WPI_TalonSRX leftBackmotor;
private final WPI_TalonSRX rightBackmotor;
private final SpeedControllerGroup leftGroup;
private final SpeedControllerGroup rightGroup;
private final DifferentialDrive drivetrain;


public Drivetrain () {

    leftFrontmotor = new WPI_TalonSRX(1);
    rightFrontmotor = new WPI_TalonSRX(4);
    leftBackmotor = new WPI_TalonSRX(2);
    rightBackmotor = new WPI_TalonSRX(6);
    leftGroup = new SpeedControllerGroup(leftFrontmotor, leftBackmotor);
    rightGroup = new SpeedControllerGroup(rightFrontmotor, rightBackmotor);
    drivetrain = new DifferentialDrive(leftGroup, rightGroup);

}

@Override
public void initDefaultCommand() {
    setDefaultCommand(new Drive());
}
public void arcadeDrive(double move,double turn) {
    drivetrain.arcadeDrive(move,turn);
    SmartDashboard.putNumber("move", move);
    SmartDashboard.putNumber("turn", turn);
}
  
}