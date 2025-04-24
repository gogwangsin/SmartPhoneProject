package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.database.MergeCursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends Sprite {
    private static final String TAG = Player.class.getSimpleName();

    private static final float SPEED_X = 700.f;
    private static final float SPEED_Y = 500.f;
    private static final float RADIUS = 100f;
    private float minX, maxX, minY, maxY;

    private final JoyStick joyStick;
    private float angle;

    public Player (JoyStick joyStick){
        super(R.mipmap.obj_purple_side);

        this.joyStick = joyStick;

        setPosition(Metrics.width / 2, Metrics.height - 200, RADIUS);

        // 화면 경계값 계산
        minX = RADIUS / 2f;
        maxX = Metrics.width - RADIUS / 2f;
        minY = RADIUS / 1.25f;
        maxY = Metrics.height - RADIUS / 1.25f;
    }

    public void update(){

        if(joyStick.power <= 0 ) {
            return;
        }

        angle = (float)Math.toDegrees(joyStick.angle_radian);

        float distance_x = SPEED_X * joyStick.power * GameView.frameTime;
        float distance_y = SPEED_Y * joyStick.power * GameView.frameTime;

        float newX = x + (float)(distance_x * Math.cos(joyStick.angle_radian));
        float newY = y + (float)(distance_y * Math.sin(joyStick.angle_radian));

        // 새 위치가 화면 안에 있을 때만 갱신
        if (minX <= newX && newX <= maxX && minY <= newY && newY <= maxY) {
            x = newX;
            y = newY;
            setPosition(x, y, RADIUS);
        }
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        // 디버깅용 빨간 테두리 네모
        if (GameView.drawsDebugStuffs) {
            Paint paint = new Paint();
            paint.setStyle(Paint.Style.STROKE); // 테두리만
            paint.setColor(Color.RED);
            paint.setStrokeWidth(4);

            canvas.drawRect(dstRect, paint);
        }
    }
}
