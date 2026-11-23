
package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.pedropathing.follower.ManualDrive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.pedro.Constants;

@TeleOp(name = "Pedro_DriveMode")
public class Pedro_DriveMode extends OpMode {
    private DcMotor motor_frontl;
    private DcMotor motor_frontr;
    private DcMotor motor_backl;
    private DcMotor motor_backr;
    private DcMotor motor_intake;
    private DcMotor motor_shooter;
    private CRServo intake_servo_l;
    private CRServo intake_servo_r;
    private Follower follower;

    @Override
    public void init() {
        follower = Constants.create(hardwareMap);
    }

    int lastPosition = motor_shooter.getCurrentPosition();
    long lastTime = System.nanoTime();

    @Override
    public void loop() {
        motor_shooter = hardwareMap.get(DcMotor.class, "motor_shooter");
        motor_frontl = hardwareMap.get(DcMotor.class, "motor_frontl");
        motor_backl = hardwareMap.get(DcMotor.class, "motor_backl");
        motor_frontr = hardwareMap.get(DcMotor.class, "motor_frontr");
        motor_backr = hardwareMap.get(DcMotor.class, "motor_backr");
        motor_intake = hardwareMap.get(DcMotor.class, "motor_intake");
        intake_servo_l = hardwareMap.get(CRServo.class, "intake_servo_l");
        intake_servo_r = hardwareMap.get(CRServo.class, "intake_servo_r");

        if (gamepad1.right_bumper) {
            ManualDrive.driveOrHold(
                    follower,
                    gamepad1.left_stick_y,
                    gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
        } else {
            ManualDrive.driveOrHold(
                    follower,
                    -gamepad1.left_stick_y,
                    -gamepad1.left_stick_x,
                    -gamepad1.right_stick_x
            );
        }
        double shoot;

        if (gamepad1.a) {
            shoot = .75;
        } else if (gamepad1.x) {
            shoot = 0.50;
        } else if (gamepad1.b) {
            shoot = 1 - gamepad1.right_trigger;
        } else {
            shoot = 0;
        }

        int currentPosition = motor_shooter.getCurrentPosition();

        double ticksPerRevolution = 28.0;
        double maxRPM = 6000.0;

        // Measure elapsed time
        long currentTime = System.nanoTime();
        double elapsedTime =
                (currentTime - lastTime) / 1_000_000_000.0;

        if (elapsedTime > 0) {

            // Calculate encoder ticks per second
            double ticksPerSecond =
                    (currentPosition - lastPosition) / elapsedTime;

            // Convert to RPM
            double rpm = (ticksPerSecond / ticksPerRevolution) * 60.0;

            // Convert RPM to normalized speed (-1 to 1)
            double speed = rpm / maxRPM;

            speed = Math.round(speed * 10.0) / 10.0;

            // Keep value within range
            speed = Math.max(-1.0, Math.min(1.0, speed));

            // Display results
            telemetry.addData("Encoder Position", currentPosition);
            telemetry.addData("RPM", "%.1f", rpm);
            telemetry.addData("Shooter Speed", "%.3f", speed);

            telemetry.addLine();

            if (Math.abs(speed - shoot) <= 0.1 && shoot > 0) {
                telemetry.addLine("READY TO FIRE");
            } else {
                telemetry.addLine("DO NOT FIRE");
            }

            telemetry.update();
        }

        // Save readings for next loop
        lastPosition = currentPosition;
        lastTime = currentTime;

        double intake = gamepad1.left_trigger;

        telemetry.addLine();
        
        telemetry.addData("Shooter Power", shoot);

        telemetry.update();

        motor_shooter.setPower(shoot);

        motor_intake.setPower(intake);

        intake_servo_l.setPower(-intake);
        intake_servo_r.setPower(intake);

        follower.update();
    }
}