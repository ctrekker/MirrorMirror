package com.ctrekker.mirrormirror;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;

import com.ctrekker.mirrormirror.math.Slope;

/**
 * Created by ctrek on 1/13/2017.
 */
public class Mirror {
    public static final int DEFAULT_SIZE = 100;
    private static final int REFLECT_COLOR = Color.WHITE;
    private static final int ABSORB_COLOR = Color.RED;
    private static final int COMPLETE_COLOR = Color.GREEN;
    private Type mType;
    private int mX;
    private int mY;
    private int mSize;
    private double mAngle=0;
    private Slope mSlope;
    private Path mPath;
    private Paint mPaint;
    private float mP1X;
    private float mP1Y;
    private float mP2X;
    private float mP2Y;

    public Mirror(int x, int y, int size) {
        mX=x;
        mY=y;
        mSize=size;
        mType=Type.REFLECT;

        init();
    }
    public Mirror(int x, int y, int size, double angle) {
        mX=x;
        mY=y;
        mSize=size;
        mAngle=angle;
        mType=Type.REFLECT;

        init();
    }
    public Mirror(int x, int y, int size, Type type) {
        mX=x;
        mY=y;
        mSize=size;
        mType=type;

        init();
    }
    public Mirror(int x, int y, int size, double angle, Type type) {
        mX=x;
        mY=y;
        mSize=size;
        mAngle=angle;
        mType=type;

        init();
    }
    private void init() {

        mPaint=new Paint();
        mPaint.setAntiAlias(true);
        switch(mType) {
            case REFLECT:
                mPaint.setColor(REFLECT_COLOR);
                break;
            case ABSORB:
                mPaint.setColor(ABSORB_COLOR);
                break;
            case COMPLETE:
                mPaint.setColor(COMPLETE_COLOR);
                break;
            default:
                mPaint.setColor(REFLECT_COLOR);
                break;
        }
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10f);

        reCalc();
    }
    private void reCalc() {
        mSlope=Slope.fromAngle(mAngle);

        double slopeFactorX=mSize/2;
        double slopeFactorY=0;
        if(mAngle<90) {
            slopeFactorX = (mSize / 2) * Math.cos(Math.toRadians(mAngle));
            slopeFactorY = (mSize / 2) * Math.sin(Math.toRadians(mAngle));
        }
        else if(mAngle>=90) {
            slopeFactorX=-((mSize/2)*Math.cos(Math.toRadians(mAngle-90)));
            slopeFactorY=(mSize/2)*Math.sin(Math.toRadians(mAngle-90));
        }
        Log.d("Mirror", "slopeFactorX:"+slopeFactorX+"; slopeFactorY:"+slopeFactorY);

        mP1X=mX+(float)slopeFactorX;
        mP1Y=mY-(float)slopeFactorY;
        mP2X=mX-(float)slopeFactorX;
        mP2Y=mY+(float)slopeFactorY;

        mPath=new Path();
        mPath.moveTo(mP1X, mP1Y);
        mPath.lineTo(mP2X, mP2Y);
    }

    public float getP1X() {
        return mP1X;
    }

    public float getP1Y() {
        return mP1Y;
    }

    public float getP2X() {
        return mP2X;
    }

    public float getP2Y() {
        return mP2Y;
    }

    public enum Type {
        REFLECT, ABSORB, COMPLETE
    }

    public Path getPath() {
        return mPath;
    }

    public void setPath(Path path) {
        mPath = path;
    }

    public Paint getPaint() {
        return mPaint;
    }

    public void setPaint(Paint paint) {
        mPaint = paint;
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

    public int getSize() {
        return mSize;
    }

    public void setSize(int size) {
        mSize = size;
    }

    public double getAngle() {
        return mAngle;
    }

    public void setAngle(double angle) {
        mAngle = angle;
        reCalc();
    }
}
