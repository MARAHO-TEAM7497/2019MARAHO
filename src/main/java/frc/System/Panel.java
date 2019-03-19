package frc.System;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Panel{

    private static boolean out=false,back= false,three ;
    
    private static byte state = 0,restore=0,count;

    private static byte Y_Ramp_Encoder=2,B_Ramp_Encoder=3;
    //Panel 馬達的ID
    private static final byte Port_PanelMotor = 5;
    private static VictorSPX PanelMotor;
    //Panel 氣壓的東西
    private static Solenoid left,right;
    private static final byte Port_LeftSolenoid = 0,Port_RightSolenoid=1;
    private static Encoder RampCoder;
    private static double value,lastvalue,rate;
    //初始化

    public static void Init(){
        
        RampCoder = new Encoder(Y_Ramp_Encoder,B_Ramp_Encoder,true);
        left = new Solenoid(Port_LeftSolenoid);
        right = new Solenoid(Port_RightSolenoid);
        PanelMotor = new VictorSPX(Port_PanelMotor);
        PanelMotor.setInverted(false);
        PanelMotor.setNeutralMode(NeutralMode.Brake);

    }
    //Auto

    public static void autoInit(){
        SmartDashboard.setDefaultNumber("PanelCoder", 0);
        SmartDashboard.setDefaultString("Cyclinder", "in");
        
        restore=0;
        state = 0;
        restore = 0;
        count=0;

        back = true;
        three = true;

        RampCoder.reset();  
    }

    public static void autoPeriodic()  {    
        value =RampCoder.getDistance();
        SmartDashboard.putNumber("PanelCoder", value);
        rate = value - lastvalue;
        if(Stick.lb){
            state++;
            back = true;
            restore = 0;
            count++;
        }
        else if (Stick.rb){
            state = 0;
            restore++;
            back = false;
        }
        switch(restore){
            case 0:
                break;
            case 1:
                PanelMotor.set(ControlMode.PercentOutput,-0.18);
                break;
   
            case 2:
                PanelMotor.set(ControlMode.PercentOutput,0);

                break;
            case 3: 
                restore = 0;
                break;
        }
        if(count <=2){
            switch (state){
                case 0:
                    break;
                case 1:
                    if (18-Math.abs(value) > 1 ){
                        PanelMotor.set(ControlMode.PercentOutput, 0.2);
                    }
                    else {
                        state++;
                    }
                    break;
                case 2:
                    PanelMotor.set(ControlMode.PercentOutput, (20-value)*0.05);
                    break;
                case 3:
                    if(45-Math.abs(value) > 1){
                        PanelMotor.set(ControlMode.PercentOutput, 0.2);
                    }
                    else {
                        state ++;
                    }
                    break;
                case 4:
                    PanelMotor.set(ControlMode.PercentOutput, (45-value)*0.05);
                    break;
                }
        }
        else if (count == 3&& three ){
            state = 1;
            three =! three;
        }
        else {
           switch (state){
            case 0: 
            if(back){
                PanelMotor.set(ControlMode.PercentOutput, 0);  
            }
            break;

        case 1:
            if (value > 20){
                PanelMotor.set(ControlMode.PercentOutput,-0.4);
                SmartDashboard.putString("Panel", "up");
            }
            else{
                state++;
            } 
            break;
        case 2:
            if (rate != 0){
                PanelMotor.set(ControlMode.PercentOutput, -(value-13)*0.03);
                SmartDashboard.putString("Panel", "up");
            }
            break; 
        case 3:
            if (value < 35){
                PanelMotor.set(ControlMode.PercentOutput,0.25);
                SmartDashboard.putString("Panel", "down");

            }else  {
                state++;
        }
            break;
        case 4:
            if (rate!=0 ){  
                PanelMotor.set(ControlMode.PercentOutput, (43-value)*0.05);
                SmartDashboard.putString("Panel", "down");
            }
            break;   
        case 5:
            state =1;
            break;
           } 
        }    
        if (state == 4){
            if(Stick.x ){
                out =! out;
            }
            if(out){
                SmartDashboard.putString("Cylinder", "OUT");
                left.set(true);
                right.set(true);
            }
            else {
                SmartDashboard.putString("Cylinder", "IN");
                right.set(false);
                left.set(false);
            }
        }
        System.out.print(state);
        System.out.println(value);
        lastvalue = value;
    }
    
    //手動

    public static void teleopInit(){
        SmartDashboard.setDefaultNumber("PanelCoder", value);
    
        state = (byte) Math.abs(6 - state);
        restore = 0;
    
    }

    public static void teleoPeriodic()  {    
            value =RampCoder.getDistance();
            SmartDashboard.putNumber("PanelCoder", value);
            rate = value - lastvalue;

            if(Stick.lb){
                state++;
                back = true;
                restore = 0;
            }

            else if (Stick.rb){
                state = 0;
                restore++;
                back = false;
                PanelMotor.set(ControlMode.PercentOutput,-0.2);
            }



            switch(restore){
                case 0:
                    break;
                case 1:
                    PanelMotor.set(ControlMode.PercentOutput,-0.2);
                    break;
                case 2 :
                    PanelMotor.set(ControlMode.PercentOutput,0);
                    break;
            }


            switch(state){
                case 0: 
                    if(back){
                        PanelMotor.set(ControlMode.PercentOutput, 0);  
                    }
                    break;
                case 1:
                    if (value < 40){
                        PanelMotor.set(ControlMode.PercentOutput,0.25);
                        SmartDashboard.putString("Panel", "down");

                    }else  {
                        state++;
                    }
                    break;
                case 2:
                    if (rate!=0 ){
                        PanelMotor.set(ControlMode.PercentOutput, (57-value)*0.05);
                        SmartDashboard.putString("Panel", "down");
                    }
                    break;
                case 3:
                    if (value > 20){
                        PanelMotor.set(ControlMode.PercentOutput,-0.4);
                        SmartDashboard.putString("Panel", "up");
                    }
                    else{
                        state++;
                    } 
                    break;
                case 4:
                    if (rate != 0){
                        PanelMotor.set(ControlMode.PercentOutput, -(value-13)*0.03);
                        SmartDashboard.putString("Panel", "up");
                    }
                    break;    
                case 5:
                    state =1;
                    break;
            }



            if (state == 2){
                if(Stick.x ){
                    out =! out;
                }
                if(out){
                    left.set(true);
                    right.set(true);
                    SmartDashboard.putString("Cylinder","OUT");
                }
                else {
                    right.set(false);
                    left.set(false);
                    SmartDashboard.putString("Cylinder","IN");

                }
            }


            
            System.out.println(restore);
            lastvalue = value;
        }
}
