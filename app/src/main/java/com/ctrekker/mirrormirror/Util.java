package com.ctrekker.mirrormirror;

import android.content.res.Resources;

/**
 * Created by ctrek on 1/14/2017.
 */

public class Util {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

    public static boolean linesIntersect(final int X1, final int Y1, final int X2, final int Y2,
                                         final int X3, final int Y3, final int X4, final int Y4) {
        return ((relativeCCW(X1, Y1, X2, Y2, X3, Y3)
                * relativeCCW(X1, Y1, X2, Y2, X4, Y4) <= 0) && (relativeCCW(X3,
                Y3, X4, Y4, X1, Y1)
                * relativeCCW(X3, Y3, X4, Y4, X2, Y2) <= 0));
    }

    private static int relativeCCW(final int X1, final int Y1, int X2, int Y2, int PX,
                                   int PY) {
        X2 -= X1;
        Y2 -= Y1;
        PX -= X1;
        PY -= Y1;
        int ccw = PX * Y2 - PY * X2;
        if (ccw == 0) {
            ccw = PX * X2 + PY * Y2;
            if (ccw > 0) {
                PX -= X2;
                PY -= Y2;
                ccw = PX * X2 + PY * Y2;
                if (ccw < 0) {
                    ccw = 0;
                }
            }
        }
        return (ccw < 0) ? -1 : ((ccw > 0) ? 1 : 0);
    }
}
