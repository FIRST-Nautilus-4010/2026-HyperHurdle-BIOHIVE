package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.SubsystemManager;
import org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftSubsystem;

public class TelemetryGlobal {
    private TelemetryPacket packet;
    private final FtcDashboard dashboard;
    private final Telemetry dsTelemetry;

    public TelemetryGlobal(Telemetry dsTelemetry) {
        this.dsTelemetry = dsTelemetry;
        this.dashboard = FtcDashboard.getInstance();
        this.packet = new TelemetryPacket();


    }

    public void telemetryForDriverStation (
            SubsystemManager manager,
            IntakeSubsystem intake,
            LiftSubsystem lift,
            DriveSubsystem drive){

        dsTelemetry.addLine("=== STATE MACHINE ===");
        dsTelemetry.addData("Robot State", manager.getCurrentState());
        dsTelemetry.addData("Centro de Gravedad Alto", manager.centerOfGravityHigh());

        dsTelemetry.addLine("=== FIELD POSE ===");
        dsTelemetry.addData("Pos X (pulgadas)", drive.mecanumDrive.localizer.getPose().position.x);
        dsTelemetry.addData("Pos Y (pulgadas)", drive.mecanumDrive.localizer.getPose().position.y);
        dsTelemetry.addData("Yaw (grados)", Math.toDegrees(drive.mecanumDrive.localizer.getPose().heading.toDouble()));

        dsTelemetry.addLine("=== MECANISMOS ===");
        dsTelemetry.addData("Lift Position (rad)", lift.getLiftPositionRadians());
        dsTelemetry.addData("Intake Vel (RPM)", intake.getIntakeVelRpm());

    }

    public void telemetryForDashboard (
            SubsystemManager manager,
            IntakeSubsystem intake,
            LiftSubsystem lift,
            DriveSubsystem drive){

        packet.put("Lift Desired Position", lift.getTargetPositionRadians());
        packet.put("Lift Detected Position", lift.getLiftPositionRadians());
        packet.put("Lift Error", lift.getLiftPositionRadians() - lift.getTargetPositionRadians());

        packet.put("Intake Desired Velocity", intake.getTargetVelocityRpm());
        packet.put("Intake Detected Velocity", intake.getIntakeVelRpm());
        packet.put("Intake Error", intake.getIntakeVelRpm() - intake.getTargetVelocityRpm());

        packet.put("Desired Speed", drive.getDesiredSpeed());
        packet.put("Detecdeted Speed", drive.getDetectedSpeed());
        packet.put("Error", drive.getDesiredSpeed() - drive.getDetectedSpeed());

        packet.put("Desired Accel", drive.getDesiredAccel());
        packet.put("Detected Accel", drive.getDetectedAccel());
        packet.put("Error", drive.getDesiredAccel() - drive.getDetectedAccel());

        packet.put("Pos X (pulgadas)", drive.mecanumDrive.localizer.getPose().position.x);
        packet.put("Pos Y (pulgadas)", drive.mecanumDrive.localizer.getPose().position.y);

        packet.put("Center of Gravity High", manager.centerOfGravityHigh());
    }

    public void telemetryForAdvantage(
            SubsystemManager manager,
            IntakeSubsystem intake,
            LiftSubsystem lift,
            DriveSubsystem drive
    ){
        packet.put("Lift/Desired Position", lift.getTargetPositionRadians());
        packet.put("Lift/Detected Position", lift.getLiftPositionRadians());
        packet.put("Lift/Error", lift.getLiftPositionRadians() - lift.getTargetPositionRadians());

        packet.put("Intake/Desired Velocity", intake.getTargetVelocityRpm());
        packet.put("Intake/Detected Velocity", intake.getIntakeVelRpm());
        packet.put("Intake/Error", intake.getIntakeVelRpm() - intake.getTargetVelocityRpm());

        packet.put("Target/Speed", drive.getDesiredSpeed());

    }

    public void update() {
        dsTelemetry.update();
        dashboard.sendTelemetryPacket(packet);

        packet = new TelemetryPacket();
    }

    public TelemetryPacket getPacket() {
        return packet;
    }

}
