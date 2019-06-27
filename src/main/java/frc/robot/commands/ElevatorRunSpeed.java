package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorRunSpeed extends Command {
    private double m_speed;

    public ElevatorRunSpeed(double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
      requires(Robot.m_elevator);
      this.m_speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//unless we're using the joystick, just apply a constant 10% power
    	//Doing this to correct backdriving of lift motor
    	if ((Robot.m_elevator.getLiftHeight() >= 7.2) && (this.m_speed < 0)) {
        Robot.m_elevator.setSpeed(this.m_speed);
        
    	}else if (Robot.m_elevator.getLimitSwitchValue()) {
    		if (this.m_speed > 0) {
    			Robot.m_elevator.setSpeed(this.m_speed);
    		} else {
    			Robot.m_elevator.setSpeed(0.0);
        }
    	//run lift off of joystick
    	} else if (this.m_speed != 0) {
    		Robot.m_elevator.setSpeed(this.m_speed);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
