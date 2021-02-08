package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

//manually change the targetVoltage in Constants.java

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
        Constants.timesExecuted = 0;
    }
  
    @Override
    public void execute() { 
      shooter.firstMotor.set(ControlMode.PercentOutput, 0.5);
      //double encoderRate = Shooter.getRate();
     // System.out.println(encoderRate);
      
      /* Constants.timesExecuted += 1;
      shooter.setVoltage();
      double firstError = shooter.firstRPM - (shooter.firstEncoder.getRate() * 60);
      System.out.println(shooter.firstRPM);
      shooter.firstCurrentVoltage += firstError * Constants.kP;
      double secondError = shooter.secondRPM - (shooter.secondEncoder.getRate() * 60);
      shooter.secondCurrentVoltage += secondError * Constants.kP;
      System.out.println(shooter.firstEncoder.getRate() * 60);
      System.out.println(shooter.secondEncoder.getRate() * 60); *
        if(Math.abs(firstError) < 20){
            Shooter.isFirstRPMGood = true;
        }   
        if(Math.abs(secondError) < 20){
            Shooter.isSecondRPMGood = true;
        }
        System.out.println("Executed " + Constants.timesExecuted + " times."); */
    }
  
    @Override
    public void end(boolean interrupted) {
      //System.out.println("Time to reach RPM: " + (System.currentTimeMillis() - startTime));
      shooter.firstMotor.set(ControlMode.PercentOutput, 0);
    }
  
    @Override
    public boolean isFinished() {
      /*if(Shooter.isFirstRPMGood && Shooter.isSecondRPMGood){
        return true;
      }else{
        return false;*/
        return false;
    }
  }
