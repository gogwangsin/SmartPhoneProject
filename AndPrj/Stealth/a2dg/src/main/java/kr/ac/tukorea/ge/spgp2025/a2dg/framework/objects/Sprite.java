package kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.BitmapPool;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.RectUtil;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class Sprite implements IGameObject {
    protected Bitmap bitmap;
    // 실제로 화면에 그릴 이미지
    protected Rect srcRect = null;
    // 비트맵의 어느 부분을 그릴지 설정 (null이면 전체 사용)
    protected final RectF dstRect = new RectF();
    // 비트맵을 실제로 화면에 그릴 위치와 크기 (float로 정밀함)
    protected float x, y, dx, dy;
    protected float width, height, radius;

    public Sprite(int mipmapId) {
        if (mipmapId != 0) {
            bitmap = BitmapPool.get(mipmapId);
        }
    }

    public Sprite(int mipmapId, float x, float y, float width, float height) {
        this(mipmapId);
        setPosition(x, y, width, height);
    }

    public void setPosition(float x, float y, float radius) {
        this.x = x;
        this.y = y;
        this.width = this.height = 2 * radius;
        RectUtil.setRect(dstRect, x, y, radius);
        // → 실제 그릴 비트맵의 가로 세로 길이를 반지름 기준으로 정사각형으로 설정.
    }
    public void setPosition(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        radius = Math.min(width, height) / 2;
        RectUtil.setRect(dstRect, x, y, width, height);
    }
    @Override
    public void update() {
        float timedDx = dx * GameView.frameTime;
        float timedDy = dy * GameView.frameTime;
        x += timedDx;
        y += timedDy;
        dstRect.offset(timedDx, timedDy);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);

        // 디버깅용 빨간 테두리 네모
//        if (GameView.drawsDebugStuffs) {
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.STROKE); // 테두리만
//            paint.setColor(Color.RED);
//            paint.setStrokeWidth(4);
//
//            canvas.drawRect(dstRect, paint);
//        }
    }

    public float GetX() { return x;}
    public float GetY() { return y;}
}
