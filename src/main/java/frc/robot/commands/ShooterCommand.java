package frc.robot.commands;

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
        shooter.setRPM(60);
        startTime = System.currentTimeMillis();
        Constants.timesExecuted = 0;
    }
    // most likely issue: getSelectedSensoryVelocity method is only compatible with TalonFX (does not show up in the TalonSRX simcollection docs, only in TalonFX) should figure out
    // isReal method/boolean/??? to create 2 separate situations for the simulator and for reality


    // Encoder Fix:
    // 1) https://www.ctr-electronics.com/downloads/api/cpp/html/classctre_1_1phoenix_1_1motorcontrol_1_1_talon_s_r_x_sim_collection.html
    // 2) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/13 
    // 3) https://www.chiefdelphi.com/t/simulating-a-neo-ctre-mixed-robot-using-new-wpilib-simulator/390176/19 
  

    @Override
    public void execute() { 
      //shooter.hoodPIDController.calculate(shooter.getPotentiometerAngle());
     // System.out.println(encoderRate);

      
    
    }
  
    @Override
    public void end(boolean interrupted) {
      //System.out.println("Time to reach RPM: " + (System.currentTimeMillis() - startTime));
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
