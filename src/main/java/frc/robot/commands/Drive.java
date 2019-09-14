package frc.robot.commands;

import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Drive extends Command {
    private static double slowify = 0.5;

    public Drive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // Robot.m_drivetrain.driveRobotTank(Robot.m_oi.getMove() * slowify, Robot.m_oi.getTurn() * slowify);
    	Robot.m_drivetrain.driveRobotArcade(Robot.m_oi.getMove() * slowify, Robot.m_oi.getTurn() * slowify);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_drivetrain.driveRobotArcade(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

    public static void setSlowify(double value) {
        slowify = value;
    }
}
