package org.firstinspires.ftc.teamcode.TeleOp;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.States;
import org.firstinspires.ftc.teamcode.SubsystemManager;
import org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftSubsystem;
import org.firstinspires.ftc.teamcode.Telemetry.TelemetryGlobal;

@TeleOp(name="TeleOpTesting", group="Testing")
public class TeleOpTesting extends OpMode {

    private DriveSubsystem drive;
    private SubsystemManager subsystemManager;
    private IntakeSubsystem intake;
    private LiftSubsystem lift;
    private TelemetryGlobal globalTelemetry;

    private boolean lastA = false;
    private boolean lastB = false;
    private boolean lastX = false;
    private boolean lastLB = false;
    private boolean lastRB = false;
    private boolean lastY2 = false;

    @Override
    public void init() {
        globalTelemetry = new TelemetryGlobal(telemetry);

        drive = new DriveSubsystem(hardwareMap, new Pose2d(0, 0, 0));

        subsystemManager = new SubsystemManager(hardwareMap);
        lift = subsystemManager.getLift();
        intake = subsystemManager.getIntake();

        telemetry.addData("Status", "Inicialización Completa");
        telemetry.update();
    }

    @Override
    public void loop() {
        subsystemManager.periodic(globalTelemetry.getPacket());

        double driveY = -gamepad1.left_stick_y;
        double driveX = gamepad1.left_stick_x;
        double turn   = gamepad1.right_stick_x;

        double heading = drive.mecanumDrive.localizer.getPose().heading.toDouble();

        double rotatedX = driveX * Math.cos(-heading) - driveY * Math.sin(-heading);
        double rotatedY = driveX * Math.sin(-heading) + driveY * Math.cos(-heading);

        boolean isHigh = subsystemManager.centerOfGravityHigh();

        drive.Drive(rotatedX, rotatedY, turn, isHigh);

        drive.update();

        if (gamepad1.dpad_down) {
            drive.mecanumDrive.localizer.setPose(new Pose2d(0, 0, 0));
        }

        boolean currentA = gamepad1.a;
        boolean currentB = gamepad1.b;
        boolean currentX = gamepad1.x;
        boolean currentLB = gamepad1.left_bumper;
        boolean currentRB = gamepad1.right_bumper;

        if (currentA && !lastA) subsystemManager.setStates(States.INTAKE);
        if (currentX && !lastX) subsystemManager.setStates(States.STOP);
        if (currentB && !lastB) subsystemManager.setStates(States.DROP);
        if (currentLB && !lastLB) subsystemManager.setStates(States.TRAVEL);
        if (currentRB && !lastRB) subsystemManager.setStates(States.RAISE);

        lastA = currentA;
        lastB = currentB;
        lastX = currentX;
        lastLB = currentLB;
        lastRB = currentRB;

        double manualLiftPower = -gamepad2.left_stick_y;
        double manualIntakeIn = gamepad2.left_trigger;
        double manualIntakeOut = gamepad2.right_trigger;
        boolean currentY2 = gamepad2.y;

        if (Math.abs(manualLiftPower) > 0.05 || manualIntakeIn > 0.05 || manualIntakeOut > 0.05) {

            subsystemManager.setStates(States.STOP);

            lift.io.setLiftPwr(manualLiftPower);

            if (manualIntakeIn > 0.05) {
                intake.io.setIntakePwr(manualIntakeIn);
            } else if (manualIntakeOut > 0.05) {
                intake.io.setIntakePwr(-manualIntakeOut);
            } else {
                intake.io.setIntakePwr(0);
            }
        }

        if (currentY2 && !lastY2) {

        }
        lastY2 = currentY2;

        globalTelemetry.telemetryForDriverStation(subsystemManager, intake, lift, drive);
        globalTelemetry.telemetryForDashboard(subsystemManager, intake, lift, drive);
        globalTelemetry.telemetryForAdvantage(subsystemManager, intake, lift, drive);

        globalTelemetry.update();
    }

    @Override
    public void stop() {
        drive.Drive(0,0,0, false);
    }
}