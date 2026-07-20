package org.firstinspires.ftc.teamcode;


import com.acmerobotics.dashboard.config.Config;

@Config
public class Constants {


    public static double TAKE_VEL = 3000;

    public static double OUTAKE_VEL = -3000;


    public static double LIFT_DOWN = 0;

    public static double LIFT_UP = 0;


    public static double LIFT_P = 0;
    public static double LIFT_I = 0;
    public static double LIFT_D = 0;


    public static double DEFAULT_SPEED = 0;
    public static double DEFAULT_ACCEL = 0;
    public static double SLOW_SPEED = 0;
    public static double SLOW_ACCEL = 0;

    public static double LIFT_GEAR_RATIO = 64;
    public static double TICKS_PER_REV = 28 * LIFT_GEAR_RATIO;

    public static double LIFT_CONVERTION_TO_RAD = (2 * Math.PI) / TICKS_PER_REV;

}
