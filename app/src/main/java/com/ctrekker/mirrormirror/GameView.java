package com.ctrekker.mirrormirror;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by ctrek on 1/11/2017.
 */

public class GameView extends View {
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private ArrayList<Mirror> mMirrors=new ArrayList<>();

    public GameView(Context c, AttributeSet attrs) {
        super(c, attrs);

        Mirror mirror=new Mirror(100, 100, Mirror.DEFAULT_SIZE, 0, Mirror.Type.REFLECT);
        mMirrors.add(mirror);
        mirror=new Mirror(100, 200, Mirror.DEFAULT_SIZE, 0, Mirror.Type.ABSORB);
        mMirrors.add(mirror);
        mirror=new Mirror(100, 300, Mirror.DEFAULT_SIZE, 0, Mirror.Type.COMPLETE);
        mMirrors.add(mirror);
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

}
