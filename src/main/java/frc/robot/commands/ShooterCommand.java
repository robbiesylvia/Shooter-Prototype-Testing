package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.Constants;

//manually change the targetVoltage in Constants.java

public class ShooterCommand extends CommandBase {
  public static Shooter shooter;
  public int timesExecuted = 0;
  public int targetRPM = 1000;
  public long startTime;
  public int threeSecondCount;
  public int increaseRPM;

  public ShooterCommand(Shooter shooter) {
    ShooterCommand.shooter = shooter;
      addRequirements(shooter);
    }

    @Override
    public void initialize() {
        
        startTime = System.currentTimeMillis();
        Constants.timesExecuted = 0;
       //shooter.hoodMotor.set(0.1);

       shooter.setRPM(1000);
    }
    // most likely issue: getSelectedSensoryVelocity method is only compatible with TalonFX (does not show up in the TalonSRX simcollection docs, only in TalonFX) should figure out
    // isReal method/boolean/??? to create 2 separate situations for the simulator and for reality


    // Encoder Fix:
    // 1) https://www.ctr-electronics.com/downloads/api/cpp/html/classctre_1_1phoenix_1_1motorcontrol_1_1_talon_s_r_x_sim_collection.html
    // 2) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/13 
    // 3) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/19 
  

    @Override
    public void execute() { 
    
     // shooter.setAngle(50);
      //System.out.println("Potentiometer Angle:" + shooter.getPotentiometerAngle());
    

    
    // System.out.println(encoderRate);


    
    /*shooter.setRPM(1000 + increaseRPM);
    timesExecuted += 1;
    threeSecondCount = (int)(timesExecuted / 15);
    increaseRPM = threeSecondCount * 100;*/
    
    
    



    // }
     

   // shooter.firstMotor.set(ControlMode.PercentOutput, 0.5);
  // shooter.secondMotor.set(ControlMode.PercentOutput, 0.5);

System.out.println(shooter.firstMotor.getSelectedSensorVelocity() * (1.0/2048.0) * 600.0);
    }
  
    @Override
    public void end(boolean interrupted) {
      //System.out.println("Time to reach RPM: " + (System.currentTimeMillis() - startTime));
    shooter.firstMotor.set(ControlMode.Disabled, 0);
    }
  
    @Override
    public boolean isFinished() {
     
        return false;
    }
  }
