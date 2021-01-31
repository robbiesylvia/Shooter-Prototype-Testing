package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

    
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
        shooter.firstEncoder.reset();
        shooter.secondEncoder.reset();
    }
  
    @Override
    public void execute() { 
      Constants.timesExecuted += 1;
      shooter.setVoltage(shooter.firstCurrentVoltage, shooter.secondCurrentVoltage);
      double firstError = shooter.firstRPM - (shooter.firstEncoder.getRate() * 60);
      shooter.firstCurrentVoltage += firstError * Constants.kP;
      double secondError = shooter.secondRPM - (shooter.secondEncoder.getRate() * 60);
      shooter.secondCurrentVoltage += secondError * Constants.kP;
      System.out.println(shooter.firstEncoder.getRate() * 60);
      System.out.println(shooter.secondEncoder.getRate() * 60);
        if(Math.abs(firstError) < 20){
            Shooter.isFirstRPMGood = true;
        }   
        if(Math.abs(secondError) < 20){
            Shooter.isSecondRPMGood = true;
        }
        System.out.println("Executed " + Constants.timesExecuted + " times.");
    }
  
    @Override
    public void end(boolean interrupted) {
      System.out.println("Time to reach RPM: " + (System.currentTimeMillis() - startTime));
    }
  
    @Override
    public boolean isFinished() {
      if(Shooter.isFirstRPMGood && Shooter.isSecondRPMGood){
        return true;
      }else{
        return false;
      }
    }
  }
