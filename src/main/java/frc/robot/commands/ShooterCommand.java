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
        shooter.firstMotor.set(ControlMode.PercentOutput, 0.5);
        double testEncoder = shooter.firstMotor.getSelectedSensorVelocity(0);
        System.out.println("Velocity during initialize = " + testEncoder);
    }
    // most likely issue: getSelectedSensoryVelocity method is only compatible with TalonFX (does not show up in the TalonSRX simcollection docs, only in TalonFX) should figure out
    // isReal method/boolean/??? to create 2 separate situations for the simulator and for reality


    // Encoder Fix:
    // 1) https://www.ctr-electronics.com/downloads/api/cpp/html/classctre_1_1phoenix_1_1motorcontrol_1_1_talon_s_r_x_sim_collection.html
    // 2) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/13 
    // 3) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/19 
  

    @Override
    public void execute() { 
      shooter.PIDControl(shooter.firstMotor, 1.0);
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
      shooter.firstMotor.set(ControlMode.PercentOutput, 0.0);
      System.out.println("Ending percent output =" + Shooter.currentPercentOutput);
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
