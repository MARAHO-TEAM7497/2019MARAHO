package frc.System;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SPI;

public class NavxGyro {
    private static AHRS Gyro;
    private static boolean initialized = false;
    private static float lastAngle = 0;
    
    public static void Init(){
        if(initialized){
            Gyro = new AHRS(SPI.Port.kMXP);
            SmartDashboard.setDefaultNumber("Navx", 0.0);
            System.out.println("initialized");
        }
        else {
            initialized =! initialized;
        }
    }
    /*可能會有正負的問題, 仍然不確定到底可不可行
       Roll
    */
    public static void resetRoll(){
        lastAngle = Gyro.getRoll();
    }
    
    public static float getRoll(){
        return  Gyro.getRoll() - lastAngle ;
    }
    
    public static double getAngle(){
        return Gyro.getAngle();
    }
  


}