package org.firstinspires.ftc.teamcode.autoPrograms;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Globals;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

@Autonomous
//@Disabled
public class practiceAuto extends OpMode {
    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

    Robot robot=new Robot(hardwareMap);

    public void init(){
        Globals.autoPoseSave=Globals.blueLeftPoseAuto;
        drive.setPoseEstimate(Globals.blueLeftPoseAuto);
        robot.init();

        Globals.gamePeriod=Globals.gameState.AUTONOMOUS;

        //TODO: call the start of async rr sequence here
    }
    public void loop() {





        drive.update();
        Globals.autoPoseSave=drive.getPoseEstimate();
        Globals.gamePeriod= Globals.gameState.TELEOP;
        //lift.periodic();

    }
}
