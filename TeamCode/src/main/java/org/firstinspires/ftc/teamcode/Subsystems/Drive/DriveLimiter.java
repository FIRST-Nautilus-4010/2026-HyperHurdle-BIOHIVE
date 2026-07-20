package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.util.ElapsedTime;

public class DriveLimiter {

    private double currentX = 0;
    private double currentY = 0;
    private double currentTurn = 0;
    private final ElapsedTime timer = new ElapsedTime();

    public PoseVelocity2d limit(PoseVelocity2d targetVel, double maxAccel) {
        double dt = timer.seconds();
        timer.reset();

        if (dt > 0.1) dt = 0.1;

        currentX = applyLimiter(currentX, targetVel.linearVel.x, maxAccel, dt);
        currentY = applyLimiter(currentY, targetVel.linearVel.y, maxAccel, dt);
        currentTurn = applyLimiter(currentTurn, targetVel.angVel, maxAccel, dt);

        return new PoseVelocity2d(new Vector2d(currentX, currentY), currentTurn);
    }

    private double applyLimiter(double current, double target, double maxAccel, double dt) {
        double maxChange = maxAccel * dt;
        double change = target - current;

        if (Math.abs(change) > maxChange) {
            return current + Math.copySign(maxChange, change);
        }

        return target;
    }
}
