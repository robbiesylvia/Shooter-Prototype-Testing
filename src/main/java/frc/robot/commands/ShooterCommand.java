package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

    
public class ShooterCommand extends CommandBase {
    public final Shooter shooter;
    public long startTime;

    public ShooterCommand(Shooter shooter) {
      this.shooter = shooter;
      addRequirements(shooter);
    }
  
    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }
  
    @Override
    public void execute() {
        if(Shooter.isFirstRPMGood && Shooter.isSecondRPMGood){
        System.out.println(System.currentTimeMillis() - startTime);
        }
    }
  
    @Override
    public void end(boolean interrupted) {}
  
    @Override
    public boolean isFinished() {
      return false;
    }
  }
