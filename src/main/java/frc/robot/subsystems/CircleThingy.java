package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class CircleThingy {
    public static VictorSPX circleMoVictorSPX = new VictorSPX(11);

    public static void spinUp(double percentage) {
        circleMoVictorSPX.set(ControlMode.PercentOutput, percentage);
    }
}
