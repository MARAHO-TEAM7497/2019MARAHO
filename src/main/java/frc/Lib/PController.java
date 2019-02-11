package frc.Lib;
public class PController{
    private static double output;
    private static double Kp,DeadBand = 0,target,input,lastError,Error;
    

    //設定P控制器 死區
    public PController(double Kp,double DeadBand){
        this.Kp = Kp;
        this.DeadBand = DeadBand;
    }
    //P控制器
    public PController(double Kp){
        this.Kp = Kp;
    }
    //P控制器+死區
    public static double P(double input){
        Error =target - input;
        if(Math.abs(Error) > DeadBand ){
            output =  Error* Kp;
            System.out.println("P output " + output);
            return  output;
        }
        else {
            output =  input* Kp;
            System.out.println(output);
            return output;
        }
    }
    //設定目標值,當目標值不為0時
    public static void setTarget(double input){
        target = input; 
        System.out.println("target "+target);
    }
    //得到目前的誤差值
    public static double get(){
        return Error;
    }
}