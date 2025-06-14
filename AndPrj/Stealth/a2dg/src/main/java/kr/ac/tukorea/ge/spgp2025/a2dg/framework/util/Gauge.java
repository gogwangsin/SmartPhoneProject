package kr.ac.tukorea.ge.spgp2025.a2dg.framework.util;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import androidx.core.content.res.ResourcesCompat;
import kr.ac.tukorea.ge.spgp2025.a2dg.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class Gauge {
    private final Paint fgPaint = new Paint();
    private final Paint bgPaint = new Paint();
    public Gauge(float width, int bgColor, int fgColor) {
        Resources res = GameView.view.getResources();

        bgPaint.setStyle(Paint.Style.STROKE);
        bgPaint.setStrokeWidth(width);
//        bgPaint.setColor(bgColor);
        bgPaint.setStrokeCap(Paint.Cap.ROUND);
        bgPaint.setColor(ResourcesCompat.getColor(res, bgColor, null));

        fgPaint.setStyle(Paint.Style.STROKE);
        fgPaint.setStrokeWidth(width / 2);
//        fgPaint.setColor(fgColor);
        fgPaint.setColor(ResourcesCompat.getColor(res, fgColor, null));

        fgPaint.setStrokeCap(Paint.Cap.ROUND);
    }
    public void draw(Canvas canvas, float x, float y, float scale, float value) {
        canvas.save();
        canvas.translate(x, y);
        canvas.scale(scale, scale);
        draw(canvas, value);
        canvas.restore();
    }

    public void draw(Canvas canvas, float progress) {
        canvas.drawLine(0, 0, 1.0f, 0, bgPaint);
        if (progress > 0) {
            canvas.drawLine(0, 0, progress, 0, fgPaint);
        }
    }
}
