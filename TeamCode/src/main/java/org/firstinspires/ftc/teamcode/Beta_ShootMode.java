
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp(name = "Beta_shootMode")
public class Beta_ShootMode extends LinearOpMode {

    @Override
    public void runOpMode() {

        // Initialize shooter motor
        DcMotor motor_shooter =
                hardwareMap.get(DcMotor.class, "motor_shooter");

        // Encoder specifications
        double ticksPerRevolution = 28.0;
        double maxRPM = 6000.0;

        telemetry.addLine("Shooter Speed Reader Ready");
        telemetry.addLine("Press PLAY to begin");
        telemetry.update();

        waitForStart();

        // Initial encoder and time readings
        int lastPosition = motor_shooter.getCurrentPosition();
        long lastTime = System.nanoTime();

        while (opModeIsActive()) {

            double shoot;

            if (gamepad1.a) {
                shoot = (.5);
            } else {
                shoot = (0);
            }

            motor_shooter.setPower(shoot);

            // Read encoder
            int currentPosition = motor_shooter.getCurrentPosition();

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
        }
    }
}
