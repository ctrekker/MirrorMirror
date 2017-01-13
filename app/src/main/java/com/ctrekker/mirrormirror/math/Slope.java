package com.ctrekker.mirrormirror.math;

/**
 * Created by ctrek on 1/13/2017.
 */

public class Slope {
    private double mRise;
    private double mRun;
    private double mDistance;

    public Slope(double rise, double run) {
        mRise=rise;
        mRun=run;
        mDistance=(double)Math.sqrt(Math.pow(rise, 2)+Math.pow(run, 2));
    }
    public static Slope fromAngle(double angle) {
        double rise;
        double run;
        if(angle==0) {
            rise=0;
            run=1;
        }
        else if(angle==90) {
            rise=1;
            run=0;
        }
        else if(angle<=45) {
            rise = 1;
            run = 90 / angle;
        }
        else if(angle>45) {
            rise = 90 / angle;
            run = 1;
        }
        else {
            return null;
        }
        return new Slope(rise, run);
    }

    public double toAngle() {
        return 90*(mRise/(mRun+mRise));
    }

    public double getRise() {
        return mRise;
    }

    public void setRise(double rise) {
        mRise = rise;
    }

    public double getRun() {
        return mRun;
    }

    public void setRun(double run) {
        mRun = run;
    }

    @Override
    public String toString() {
        return "[Rise:"+mRise+";Run:"+mRun+";Distance:"+mDistance+"]";
    }
}
