package org.usfirst.frc.team3015.lib.android;

public class TargetInfo {
    private double xAngle;
    private double yAngle;
    private double distance;

    public TargetInfo(double xAngle, double yAngle, double distance){
        this.xAngle = xAngle;
        this.yAngle = yAngle;
        this.distance = distance;
    }

    public double getXAngle(){
        return xAngle;
    }

    public double getYAngle(){
        return yAngle;
    }

    public double getDistance(){
        return distance;
    }
}
