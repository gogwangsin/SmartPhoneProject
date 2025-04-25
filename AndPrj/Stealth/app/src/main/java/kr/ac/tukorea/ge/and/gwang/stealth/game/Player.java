package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.database.MergeCursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.google.android.material.motion.MaterialBackHandler;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends Sprite {
    private static final String TAG = Player.class.getSimpleName();

    private static final float SPEED_X = 700.f;
    private static final float SPEED_Y = 500.f;
    private static final float RADIUS = 100f;
    private static final float GRAVITY = 300f;
    private float lastDirX = 0f;
    private float lastDirY = 1;
    // 조이스틱 입력이 존재할 때 단위벡터 저장

    private float minX, maxX, minY, maxY;
    // 이 경계면을 나가면 안움직인다

    private final JoyStick joyStick;
    private float angle;

    private static final float FIRE_INTERVAL = 0.25f;
    private float fireCoolTime = FIRE_INTERVAL;
    private static final float BULLET_OFFSET_X = 100f;
    private static final float BULLET_OFFSET_Y = 8f;

    public Player (JoyStick joyStick){
        super(R.mipmap.obj_purple_side);

        this.joyStick = joyStick;

        setPosition(Metrics.width / 2 - 500, Metrics.height - 600, RADIUS);

        // 화면 경계값 계산
        minX = RADIUS / 2f;
        maxX = Metrics.width - RADIUS / 2f;
        minY = RADIUS / 1.25f;
        maxY = Metrics.height - RADIUS / 1.25f;
    }

    public void update(){

        fireCoolTime -= GameView.frameTime;
        if( fireCoolTime <= 0 ){
            fireBullet();
            fireCoolTime = FIRE_INTERVAL;
        }

        if(joyStick.power <= 0 ) {
            // 중력 적용
            applyGravity();
            return;
        }

        angle = (float)Math.toDegrees(joyStick.angle_radian);

        float distance_x = SPEED_X * joyStick.power * GameView.frameTime;
        float distance_y = SPEED_Y * joyStick.power * GameView.frameTime;

        lastDirX = (float)Math.cos(joyStick.angle_radian);
        lastDirY = (float)Math.sin(joyStick.angle_radian);

        float newX = x + distance_x * lastDirX;
        float newY = y + distance_y * lastDirY;

        // 새 위치가 화면 안에 있을 때만 갱신
        if (minX <= newX && newX <= maxX && minY <= newY && newY <= maxY) {
            x = newX;
            y = newY;
            setPosition(x, y, RADIUS);
        }
    }

    private void applyGravity() {
        float distance = GRAVITY * GameView.frameTime;

        float newX = x + distance * lastDirX;
        float newY = y + distance * lastDirY;

        // 경계 조건 체크
        if ( minX <= newX && newX <= maxX ) x = newX;
        if ( minY <= newY && newY <= maxY ) y = newY;

        setPosition(x, y, RADIUS);
    }

    private void fireBullet() {
        Scene.top().add(new Bullet(x + BULLET_OFFSET_X, y + BULLET_OFFSET_Y));
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);


    }
}
