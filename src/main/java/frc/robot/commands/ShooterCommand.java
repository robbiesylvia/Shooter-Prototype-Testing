package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

//manually change the targetVoltage in Constants.java

public class ShooterCommand extends CommandBase {
  public final Shooter shooter;
    public long startTime;
    public double targetPercentOutput = 0.5;

    public ShooterCommand(Shooter shooter) {
      this.shooter = shooter;
      addRequirements(shooter);
    }
  
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        Constants.timesExecuted = 0;
        shooter.firstMotor.set(ControlMode.PercentOutput, 0.5);

    }
  
    @Override
    public void execute(
      shooter.PIDControl()
    ) 
  }

  
    @Override
    public void end(boolean interrupted) {
      System.out.println("Time to reach RPM: " + (System.currentTimeMillis() - startTime));
      shooter.firstMotor.set(ControlMode.PercentOutput, 0);
    }
  
    @Override
    public boolean isFinished() {
        return false;
    }
  }

