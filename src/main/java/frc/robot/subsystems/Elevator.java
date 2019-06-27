package frc.robot.subsystems;

import frc.robot.*;
import frc.robot.RobotMap;
import frc.robot.commands.*; //dad
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
    private SpeedModifier m_SpeedModifier = SpeedModifier.FAST;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	static double pulseToFeet = 1 / 3944;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new ElevatorManualRun());
    }
    
    public boolean getLimitSwitchValue() {
    	return RobotMap.elevatorLimitSwitch.get();
    }
    
    public void setSpeed(double speed) {
    	RobotMap.frontElevator.set(speed * m_SpeedModifier.getValue());
    	RobotMap.rearElevator.set(speed * m_SpeedModifier.getValue());
    }
    
    public double getLiftHeight() {
    	return RobotMap.frontElevator.getSelectedSensorVelocity(0) * pulseToFeet; //dividing it by that number to return in feet;
    }
    
    public void resetEncoder() {
    	RobotMap.frontElevator.setSelectedSensorPosition(0, 0, 0);
    }
    
    public boolean doubleInTolerance(double itemOne, double itemTwo, double tolerance) {
		double tol = tolerance / 2;
		return (itemOne - tol <= itemTwo && itemTwo <= itemOne + tol) &&
				(itemTwo - tol <= itemOne && itemOne <= itemTwo + tol);
    }
    
    public void setSpeedModifier(SpeedModifier speedModifier) {
        m_SpeedModifier = speedModifier;
    }
}

