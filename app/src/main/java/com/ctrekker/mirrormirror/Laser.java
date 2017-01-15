package com.ctrekker.mirrormirror;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ctrek on 1/14/2017.
 */

public class Laser {
    private Paint mPaint;
    private int mX;
    private int mY;
    private double mAngle;
    private ArrayList<Mirror> mMirrors;
    public Laser(int x, int y, double angle, ArrayList<Mirror> mirrors) {
        mX=x;
        mY=y;
        mAngle=angle;
        mMirrors=mirrors;
        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(3f);
    }

    public ArrayList<Point> getPoints() {
        ArrayList<Point> points=new ArrayList<>();
        for(Mirror m : mMirrors) {
            double screenHyp=Math.sqrt(Math.pow(Util.getScreenWidth(), 2)+Math.pow(Util.getScreenHeight(), 2));
            int offsetX=(int)(screenHyp*Math.cos(Math.toRadians(mAngle)));
            int offsetY=(int)(screenHyp*Math.sin(Math.toRadians(mAngle)));
            if(Util.linesIntersect(mX, mY, mX+offsetX, mY+offsetY, (int)m.getP1X(), (int)m.getP1Y(), (int)m.getP2X(), (int)m.getP2Y())) {
                Log.i("Laser", "Intersected...");
            }
            points.add(new Point(mX, mY));
            points.add(new Point(mX+offsetX, mY+offsetY));
            return points;
        }
        return null;
    }

    public ArrayList<Mirror> getMirrors() {
        return mMirrors;
    }

    public void setMirrors(ArrayList<Mirror> mirrors) {
        mMirrors = mirrors;
    }

    public int getX() {
        return mX;
    }

    public void setX(int x) {
        mX = x;
    }

    public int getY() {
        return mY;
    }

    public void setY(int y) {
        mY = y;
    }

    public double getAngle() {
        return mAngle;
    }

    public void setAngle(double angle) {
        mAngle = angle;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
    }
}
