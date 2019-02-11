package frc.System;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;

public class Stick{
    private static byte Port = 0;
    private static XboxController stick;
    public static boolean a, b, x, y, lb, rb;
    public static double lx,ly,rx,ry,lt,rt;
    public static void Init(){
        stick = new XboxController(Port);
    }
    public static void telop(){
        //buttom
        a = stick.getRawButton(1);
        b = stick.getRawButton(2);
        x = stick.getRawButton(3);
        y = stick.getRawButton(4);
        lb = stick.getRawButton(5);
        rb = stick.getRawButton(6);
        //AXIS
        lx = stick.getX(Hand.kLeft);
        rx = stick.getX(Hand.kRight);
        ly = stick.getY(Hand.kLeft);
        ry = stick.getY(Hand.kRight);
        lt = stick.getTriggerAxis(Hand.kLeft);
        rt = stick.getTriggerAxis(Hand.kRight);
    }
   
}