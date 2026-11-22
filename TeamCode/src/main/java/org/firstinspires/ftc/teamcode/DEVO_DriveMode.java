package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


@TeleOp(name = "DEVO_DriveMode")
public class DEVO_DriveMode extends LinearOpMode {

    //private DcMotor motor_intake;
    private DcMotor motor_frontl;
    private DcMotor motor_frontr;
    private DcMotor motor_backl;
    private DcMotor motor_backr;
    private DcMotor motor_intake;
    private DcMotor motor_shooter;
    private CRServo intake_servo_l;
    private CRServo intake_servo_r;

    @Override
    public void runOpMode() {

        motor_shooter = hardwareMap.get(DcMotor.class, "motor_shooter");
        motor_frontl = hardwareMap.get(DcMotor.class, "motor_frontl");
        motor_backl = hardwareMap.get(DcMotor.class, "motor_backl");
        motor_frontr = hardwareMap.get(DcMotor.class, "motor_frontr");
        motor_backr = hardwareMap.get(DcMotor.class, "motor_backr");
        motor_intake = hardwareMap.get(DcMotor.class, "motor_intake");
        intake_servo_l = hardwareMap.get(CRServo.class, "intake_servo_l");
        intake_servo_r = hardwareMap.get(CRServo.class, "intake_servo_r");
        telemetry.addData("Status","Initialized");
        telemetry.update();



        waitForStart();

        while (opModeIsActive()) {

            double drive = -gamepad1.left_stick_y;

            double shoot;

            if (gamepad1.a) {
                shoot = .75;
            } else if (gamepad1.b) {
                shoot = 0.25;
            } else if (gamepad1.x) {
                shoot = 0.50;
            } else if (gamepad1.y) {
                shoot = 1 - gamepad1.right_trigger;
            } else {
                shoot = 0;
            }

            double strafe = gamepad1.left_stick_x;
            double twist = gamepad1.right_stick_x;
            double intake = gamepad1.left_trigger;

            double[] speeds = {
                    (-drive + -strafe + -twist),
                    (drive - strafe - twist),
                    (drive - strafe + twist),
                    (drive + strafe - twist)
            };
            telemetry.addData("Drive", drive);
            telemetry.addData("Strafe", strafe);
            telemetry.addData("Twist", twist);

            telemetry.addData("Front Left", speeds[0]);
            telemetry.addData("Front Right", speeds[1]);
            telemetry.addData("Back Left", speeds[2]);
            telemetry.addData("Back Right", speeds[3]);

            telemetry.addData("Shooter Power", shoot);

            telemetry.update();

            for (int i = 0; i < speeds.length; i++) {
                if (speeds[i] > 1 || speeds[i] < -1) {
                    speeds[i] /= 1.5;
                }

                speeds[i] = Math.max(-1, Math.min(1, speeds[i]));
            }


            motor_frontl.setPower(speeds[0]);
            motor_frontr.setPower(speeds[1]);
            motor_backl.setPower(speeds[2]);
            motor_backr.setPower(speeds[3]);

            motor_shooter.setPower(shoot);

            motor_intake.setPower(intake);

            intake_servo_l.setPower(-intake);
            intake_servo_r.setPower(intake);

        }
    }
}

