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
    private static final float GRAVITY = 500f;
    private float inertiaX = 0f; // x 방향 관성
    private float velocityY = 0f; // y방향 중력 가속도

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

        // 조이스틱 방향을 '단위 벡터'로 변경
        float dirX = (float)Math.cos(joyStick.angle_radian);
        float dirY = (float)Math.sin(joyStick.angle_radian);

        // x축 방향의 '현재 관성 속도' 저장
        // 조이스틱 입력에 따라 "방향 + 세기"를 반영한 x축 이동 속도를 기억
        inertiaX = SPEED_X * joyStick.power * dirX;  // 관성 갱신
        velocityY = SPEED_Y * joyStick.power * dirY; // y속도 초기화

        float distance_x = SPEED_X * joyStick.power * GameView.frameTime;
        float distance_y = SPEED_Y * joyStick.power * GameView.frameTime;

        float newX = x + distance_x * dirX;
        float newY = y + distance_y * dirY;

        // 새 위치가 화면 안에 있을 때만 갱신
        if (minX <= newX && newX <= maxX && minY <= newY && newY <= maxY) {
            x = newX;
            y = newY;
            setPosition(x, y, RADIUS);
        }
    }

    private void applyGravity() {
        // 중력 가속도 더하기
        velocityY += GRAVITY * GameView.frameTime;

        float newX = x + inertiaX * GameView.frameTime;
        float newY = y + velocityY * GameView.frameTime;

        // x축 경계 조건 체크 -> 캐릭터가 좌우 경계를 벗어나면 위치를 고정하고 x 방향 이동 멈춤 (관성 제거).
        if (newX < minX) {
            x = minX;
            inertiaX = 0;
            // 왼쪽 벽에 닿았으면 멈춤

        } else if (newX > maxX) {
            x = maxX;
            inertiaX = 0;
            // 오른쪽 벽에 닿았으면 멈춤

        } else {
            x = newX;
            // 그 외에는 그대로 이동
        }

        // y축 경계 조건 체크
        if (newY < minY) {
            y = minY;
            velocityY = 0;
            // 천장에 닿았으면 위로 가는 힘 제거

        } else if (newY >= maxY) {
            y = maxY;
            velocityY = 0; // 땅에 닿았으니까 y속도 제거 -> 땅에 닿았으면 낙하 멈춤
            inertiaX = 0;  // 땅에 닿았을 때 x축 관성도 제거! -> 추가로 좌우 이동도 멈춤

        } else {
            y = newY;
            // 그 외에는 그대로 이동
        }

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
