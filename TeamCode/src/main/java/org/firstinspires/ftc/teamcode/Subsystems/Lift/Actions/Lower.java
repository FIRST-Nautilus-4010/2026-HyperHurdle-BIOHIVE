package org.firstinspires.ftc.teamcode.Subsystems.Lift.Actions;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;

import org.firstinspires.ftc.teamcode.Constants;
import org.firstinspires.ftc.teamcode.Subsystems.Lift.LiftIO;

public class Lower implements Action {

    private final LiftIO liftIO;

    private boolean initialized = false;

    public Lower(LiftIO liftIO) {
        this.liftIO = liftIO;
    }

    @Override
    public boolean run(@NonNull TelemetryPacket packet) {

        if (!initialized) {
            liftIO.setLiftPosition((int)Constants.LIFT_DOWN);
            initialized = true;

        }

        boolean isFinished = Math.abs(liftIO.getLiftPosition() - Constants.LIFT_DOWN) < 0.01;

        if (isFinished) {
            onEnd();
            return false;
        }

        return true;
    }

    public void onEnd(){}

}
