package frc.System;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Enc {
    private static Encoder left,right;
    private static double R_Distance,L_Distance;
    private static byte Port_left_b=0,Port_left_y=1,Port_right_b=8,Port_right_y=9;
    public static void Init(){
        left = new Encoder(Port_left_b,Port_left_y,false);
        right = new Encoder(Port_right_b,Port_right_y,false);
        left.setDistancePerPulse((Math.PI*15.24)/360.0);
        right.setDistancePerPulse((Math.PI*15.24)/360.0);
        SmartDashboard.setDefaultNumber("RightDistance", 0);
        SmartDashboard.setDefaultNumber("LeftDistance", 0);
    }
    public static void teleop(){
        R_Distance = right.getDistance();
        L_Distance = left.getDistance();
        SmartDashboard.putNumber("RightDistancee",R_Distance);
        SmartDashboard.putNumber("LeftDistance", L_Distance);
    }
}