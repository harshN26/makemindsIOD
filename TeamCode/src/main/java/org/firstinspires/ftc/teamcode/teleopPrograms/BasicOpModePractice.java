package org.firstinspires.ftc.teamcode.teleopPrograms;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Globals;
import org.firstinspires.ftc.teamcode.Robot;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.subsystems.Lift;

@TeleOp
//@Disabled
public class BasicOpModePractice extends LinearOpMode {

//    SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
    Pose2d poseUpdate=new Pose2d();
    Lift lift;

//    GamepadEx gamepad1Ex=new GamepadEx(gamepad1);
//    GamepadEx gamepad2Ex=new GamepadEx(gamepad2);


    Globals.driverState driveState= Globals.driverState.DRIVER_CONTROLLED;


    public void runOpMode() throws InterruptedException {
        if(opModeInInit()) {
            if(Globals.gamePeriod==Globals.gameState.TELEOP) {
//                poseUpdate = Globals.autoPoseSave;
//                drive.setPoseEstimate(Globals.autoPoseSave);
            }else{
                poseUpdate = new Pose2d(0,0);
//                drive.setPoseEstimate(poseUpdate);
            }
            lift= new Lift(hardwareMap, Globals.liftLeftMName, Globals.liftRightMName, Globals.slidekP,Globals.slidekI,Globals.slidekD,Globals.slidekF, 5.0);
            lift.set_new_target_pos(20);

        }
        waitForStart();

        //TODO: Idea: use dont blinks anti-push rr stuff instead of actual paths
        while(opModeIsActive()){
            switch(driveState){
                case ROADRUNNER_CONTROLLED:
                    //TODO: add code to move robot to specific positions. Remember to update relevant states
                    switch(Globals.rrPathSelected){
                        case PATH1:
                            break;
                        case PATH2:
                            break;
                        default:
                            driveState= Globals.driverState.DRIVER_CONTROLLED;
                            break;
                    }
                    //TODO: add roadrunner path breaking stuff and mecanum drive cancelable stuff
                    break;
                case DRIVER_CONTROLLED:
                    //I know it will tell you to switch the x and y, DO NOT DO SO
                    double gamepad1LeftStickY = -gamepad1.left_stick_y;
                    double gamepad1LeftStickX = -gamepad1.left_stick_x;
                    double turnPower = -gamepad1.right_stick_x;
//                    drive.setWeightedDrivePower(
//                            new Pose2d(
//                                    gamepad1LeftStickY,
//                                    gamepad1LeftStickX,
//                                    turnPower
//                            )
//                    );
                    //TODO: add all roadrunner pathing stuff. Remember to update relevant states
                    break;
                default: driveState= Globals.driverState.DRIVER_CONTROLLED;
//                    break;
            }

            //NOTE: USE GAMEPAD1EX and GAMEPAD2EX FOR BUTTON MAPPING
            switch(Globals.robotState){
                case INTAKING:
                    //TODO: add intake Controls here and a way to transfer or Specimen stuff
                    break;
                case TRANSFERING:
                    //TODO: add transfer controls here and a way to go to sample outtaking
                    break;
                case SAMPLE_OUTTAKING:
                    //TODO: add outtaking binds and a way to go to intaking
                    break;
                case SPECIMEN_STUFF:
                    //TODO: add specimen stuff binds and a way to go to intaking
                    break;
                default:Globals.robotState= Globals.RobotState.INTAKING;
//                    break;
            }



            lift.periodic();
//            drive.update();
//            poseUpdate=drive.getPoseEstimate();
        }


    }
}
