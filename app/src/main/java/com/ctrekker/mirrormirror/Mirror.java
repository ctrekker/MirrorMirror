package com.ctrekker.mirrormirror;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;

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
    private float mAngle;
    private Path mPath;
    private Paint mPaint;

    public Mirror(int x, int y, int size) {
        mX=x;
        mY=y;
        mSize=size;
        mType=Type.REFLECT;

        init();
    }
    public Mirror(int x, int y, int size, float angle) {
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
    public Mirror(int x, int y, int size, float angle, Type type) {
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

        mPath=new Path();
        mPath.moveTo(mX-mSize/2, mY);
        mPath.lineTo(mX+mSize/2, mY);
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

    public float getAngle() {
        return mAngle;
    }

    public void setAngle(float angle) {
        mAngle = angle;
    }
}
