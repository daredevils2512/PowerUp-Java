package frc.robot.subsystems;

import frc.robot.RobotMap;
import frc.robot.SpeedModifier;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {
    SpeedModifier m_speedModifier = SpeedModifier.FAST;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public boolean getLimitSwitchValue() {
    	return RobotMap.intakeLimitSwitch.get();
    }
    
    public void actuateArms(DoubleSolenoid.Value actuateDir) {
    	RobotMap.intakeArmsSolenoid.set(actuateDir);
    }
    
    public void setIntakeSpeed(double speed) {
    	RobotMap.intakeLeftMotor.set(speed * m_speedModifier.getValue());
    	RobotMap.intakeRightMotor.set(-speed * m_speedModifier.getValue());
    }
    
    public void actuateDeploy(DoubleSolenoid.Value actuateDir) {
    	RobotMap.intakeDeploySolenoid.set(actuateDir);
    }

    public void setSpeedModifier(SpeedModifier speedModifier) {
        m_speedModifier = speedModifier;
    }
}

