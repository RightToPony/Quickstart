package org.firstinspires.ftc.teamcode.pedro;

import com.pedropathing.algorithm.Foresight;
import com.pedropathing.algorithm.ForesightConfig;
import com.pedropathing.controllers.Controller;
import com.pedropathing.follower.Follower;
import com.pedropathing.math.Matrix;
import com.pedropathing.math.Vector2D;
import com.pedropathing.revhub.drivetrains.Mecanum;
import com.pedropathing.revhub.drivetrains.MecanumConfig;
import com.pedropathing.revhub.localizers.PinpointConfig;
import com.pedropathing.revhub.localizers.PinpointLocalizer;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static MecanumConfig drivetrainConfig = new MecanumConfig(c -> {
        c.frontLeftName.set("motor_frontl");
        c.frontRightName.set("motor_frontr");
        c.backLeftName.set("motor_backl");
        c.backRightName.set("motor_backr");
        c.frontLeftDirection.set(DcMotorSimple.Direction.REVERSE);
        c.frontRightDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backLeftDirection.set(DcMotorSimple.Direction.FORWARD);
        c.backRightDirection.set(DcMotorSimple.Direction.FORWARD);
    });

    public static PinpointConfig localizerConfig = new PinpointConfig(c -> {
        c.name.set("pinpoint");
        c.podType.set(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD);
        c.xPodOffset.set(-2.207964799535556);
        c.yPodOffset.set(-1.1210194535142795);
        c.xPodDirection.set(GoBildaPinpointDriver.EncoderDirection.FORWARD);
        c.yPodDirection.set(GoBildaPinpointDriver.EncoderDirection.REVERSED);
        c.globalDistanceUnit.set(DistanceUnit.INCH);
        c.offsetUnits.set(DistanceUnit.INCH);
    });

    public static ForesightConfig foresightConfig = new ForesightConfig(
            c -> {
                Controller primaryTranslationalForward = Controller.proportional(0.4147623238736741);
                Controller secondaryTranslationalForward = Controller.proportional(0.15324359601791657);
                Controller primaryTranslationalLateral = Controller.proportional(0.338328052942);
                Controller secondaryTranslationalLateral = Controller.proportional(0.12500317526999696);

                c.forwardTranslational.set(Controller.piecewise(secondaryTranslationalForward).put(2.5, primaryTranslationalForward));
                c.strafeTranslational.set(Controller.piecewise(secondaryTranslationalLateral).put(2.5, primaryTranslationalLateral));

                c.coast.set(Controller.proportionalFeedforward(0.018611718759444097));
                c.brake.set(Controller.proportionalFeedforward(0.015819960945527484));

                c.headingFeedback.set(Controller.proportional(5.377382026194691));
                c.headingBrakeCoefficients.set(Vector2D.cartesian(0.05240796102662562, 0.012521114848311775));

                c.linearBrakeCoefficients.set(Matrix.diag(0.052688232400357575, 0.05438457771065664));
                c.quadraticBrakeCoefficients.set(Matrix.diag(0.004004335574624812, 0.0034146659102688814));

                c.maxAchievableForwardVelocity.set(54.2058354716021);
                c.maxAchievableStrafeVelocity.set(48.82628354083022);
                c.naturalForwardDeceleration.set(26.66751143200306);
                c.naturalStrafeDeceleration.set(36.71605400972777);
            }
    );

    public static Follower create(HardwareMap h) {
        return new Follower(
                new PinpointLocalizer(h, localizerConfig),
                new Mecanum(h, drivetrainConfig),
                new Foresight(foresightConfig)
        );
    }
}