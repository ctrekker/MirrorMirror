package com.ctrekker.mirrormirror;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by ctrek on 1/11/2017.
 */

public class GameView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ArrayList<Mirror> mMirrors=new ArrayList<>();
    private Laser mLaser;

    private Handler handler=new Handler();

    public GameView(Context c, AttributeSet attrs) {
        super(c, attrs);

        Mirror mirror=new Mirror(200, 200, Mirror.DEFAULT_SIZE, 135, Mirror.Type.REFLECT);
        mMirrors.add(mirror);
        mirror=new Mirror(200, Util.getScreenHeight()-10, Mirror.DEFAULT_SIZE, 0, Mirror.Type.COMPLETE);
        mMirrors.add(mirror);

        mLaser=new Laser(0, 200, 0, mMirrors);

        startAnimation();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.BLACK);

        ArrayList<Point> points=mLaser.getPoints();
        canvas.drawLine(points.get(0).x, points.get(0).y, points.get(1).x, points.get(1).y, mLaser.getPaint());

        for(Mirror mirror : mMirrors) {
            canvas.drawPath(mirror.getPath(), mirror.getPaint());
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x=event.getX();
        float y=event.getY();
        switch(event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:

                invalidate();
                break;
            case MotionEvent.ACTION_UP:

                invalidate();
                break;
        }
        return true;
    }

    private void startAnimation() {
        Timer timer = new Timer();
        TimerTask frameUpdate = new TimerTask() {
            @Override
            public void run() {

                //Updates View to display new bitmap
                handler.post(new Runnable() {
                    public void run() {
                        invalidate();
                    }
                });
            }
        };
        timer.schedule(frameUpdate, 0, 20);
    }
}
