package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.subsystems.Intake;
import org.firstinspires.ftc.teamcode.subsystems.Lift;
import org.firstinspires.ftc.teamcode.subsystems.OuttakeArm;

public class Robot {

    HardwareMap hardwareMap;








    public Lift lift;
    public Intake intake;
    public OuttakeArm outtakeArm;

    //public stuff
    public Robot(HardwareMap hardwareMap){
        this.hardwareMap=hardwareMap;
    }
    public void init(){
        DCMotorConfig();
        servoConfig();


        Globals.lift1TargetPos=Globals.lift1InitPos;
        lift=new Lift(hardwareMap,Globals.liftLeftMName,Globals.liftRightMName,Globals.slidekP,Globals.slidekI,Globals.slidekD,Globals.slidekF,10);
        lift.set_new_target_pos(Globals.lift1InitPos);

        intake=new Intake(hardwareMap,Globals.inSlideName,Globals.inWristName,Globals.inClawName);
        outtakeArm=new OuttakeArm(hardwareMap,Globals.outArmLName,Globals.outArmRName,Globals.outClawName);

        intake.initStuff();
        outtakeArm.initStuff();


    }
    public void update(){
        lift.periodic();
        intake.periodic();
        outtakeArm.periodic();
    }

    public void reset(){
        lift.reset();
        intake.reset();
        outtakeArm.reset();
    }





    //Config
    private void DCMotorConfig(){



    }
    private void servoConfig(){

    }
}
