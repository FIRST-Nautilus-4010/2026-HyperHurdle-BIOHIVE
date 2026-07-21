package org.firstinspires.ftc.teamcode.Telemetry;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;

public class FyleHyperHurdle {


    public static void drawField(TelemetryPacket packet, double robotX, double robotY) {

        packet.fieldOverlay()
                .drawImage("TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Telemetry/HyperHurdle_Field.png", 0, 0, 228, 228);

        packet.fieldOverlay().setStrokeWidth(1);
        packet.fieldOverlay().setStroke("rgba(200, 200, 200, 0.6)");

        for (int y = -114; y <= 114; y += 18) {
            packet.fieldOverlay().strokeLine(-72, y, 72, y);
        }

        for (int x = -72; x <= 72; x += 18) {
            packet.fieldOverlay().strokeLine(x, -114, x, 114);
        }

        packet.fieldOverlay()
                .setFill("yellow")
                .fillRect(robotX, robotY, 16, 18);
    }
}