package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

public class Panel{
    //Panel 馬達的ID
    private static final byte Port_PanelMotor = 5;
    private static VictorSPX PanelMotor;
    //初始化
    public static void Init(){
        PanelMotor = new VictorSPX(Port_PanelMotor);
        PanelMotor.setInverted(false);
        PanelMotor.setNeutralMode(NeutralMode.Brake);
    }
    //手動
    public static void teleop(){
        boolean on = false;
        if(Stick.x){
            on =! on;
            if(on){
                PanelMotor.set(ControlMode.PercentOutput, 0.3);
            }
            
        }
    }
}
