package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command {
    public Drive () {
        requires(Robot.m_drivetrain);

    }

    protected void execute() {
        Robot.m_drivetrain.arcadeDrive(Robot.m_oi.getMove(),Robot.m_oi.getTurn());
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

   

}
    