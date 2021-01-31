package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
    public Talon firstMotor = new Talon(Constants.talonFirstChannel);
    public Talon secondMotor = new Talon(Constants.talonSecondChannel);
    
    public double firstRPM;
    public double firstCurrentVoltage;
    public double secondRPM;
    public double secondCurrentVoltage;
    public static boolean isFirstRPMGood = false;
    public static boolean isSecondRPMGood = false;

    public final Encoder firstEncoder = new Encoder(Constants.firstEncoderPort1, Constants.firstEncoderPort2);
    public final Encoder secondEncoder = new Encoder(Constants.secondEncoderPort1, Constants.secondEncoderPort2);
  
    public Shooter() {
      firstEncoder.setDistancePerPulse(1./512.);
      firstEncoder.setMaxPeriod(1);
      firstEncoder.setMinRate(1);
      firstEncoder.setSamplesToAverage(5);
      secondEncoder.setDistancePerPulse(1./512.);
      secondEncoder.setMaxPeriod(1);
      secondEncoder.setMinRate(1);
      secondEncoder.setSamplesToAverage(5);
  }
  
//setRPM is successfully cycled
  public void setVoltage(double firstVoltage, double secondVoltage) {
      firstMotor.set(firstVoltage);
      secondMotor.set(secondVoltage);
    }
}