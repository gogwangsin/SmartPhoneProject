package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.database.MergeCursor;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import com.google.android.material.motion.MaterialBackHandler;

import java.util.Currency;
import java.util.HashMap;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.Gauge;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends AnimSprite implements IBoxCollidable {
//public class Player extends Sprite {
    private static final String TAG = Player.class.getSimpleName();

    // 캐릭터용 리소스 ID 배열 (캐릭터 선택용)
    public static final int[] CHARACTER_IDS = {
            R.mipmap.sp_shoot_purple,
            R.mipmap.sp_shoot_white,
            R.mipmap.yellow_shoot,
            R.mipmap.brown_shoot,
            // 추가 캐릭터가 있다면 여기에 넣기
    };
//
//    // 캐릭터 이름 및 기타 정보 담는 클래스
//    public static class CharacterInfo {
//        public final String name;
//        public CharacterInfo(String name) {
//            this.name = name;
//        }
//    }
//
//    // 캐릭터 ID와 정보 맵
//    public static final HashMap<Integer, CharacterInfo> CharacterInfoMap = new HashMap<>();
//    static {
//        CharacterInfoMap.put(R.mipmap.sp_shoot_purple, new CharacterInfo("Purple Cookie"));
//        CharacterInfoMap.put(R.mipmap.sp_shoot_white, new CharacterInfo("White Cookie"));
//        CharacterInfoMap.put(R.mipmap.yellow_shoot, new CharacterInfo("Yellow Cookie"));
//        CharacterInfoMap.put(R.mipmap.brown_shoot, new CharacterInfo("Brown Cookie"));
//    }

    private static float SPEED_X = 700.f;
    private static float SPEED_Y = 500.f;
    private static final float RADIUS = 80f;
    private static final float GRAVITY = 500f;
    private static int MAX_LIFE = 5;
    private static int CURRENT_LIFE = 5;
    private float inertiaX = 0f; // x 방향 관성
    private float velocityY = 0f; // y방향 중력 가속도

    private float minX, maxX, minY, maxY;
    // 이 경계면을 나가면 안움직인다

    private final JoyStick joyStick;
    private float angle;
    private Sprite AirPlane;
    private AnimSprite PlaneEffect;

    private static float FIRE_INTERVAL = 0.25f;
    private float fireCoolTime = FIRE_INTERVAL;
    private static final float BULLET_OFFSET_X = 100f;
    private static final float BULLET_OFFSET_Y = 8f;
    private Gauge gauge = new Gauge(0.1f, R.color.enemy_gauge_bg, R.color.enemy_gauge_fg);
    private Player player;
    protected RectF collisionRect = new RectF();
    private long lastHitTime = 0;
    private static final long INVINCIBILITY_DURATION = 1000; // 무적 시간 1초

    public Player (JoyStick joyStick, int mipmapID){
//        super(R.mipmap.obj_purple_side);
//        super(R.mipmap.sp_shoot_purple, 20, 5);

        super(mipmapID, Math.round(5 / FIRE_INTERVAL), 5);
        Log.d(TAG, "Creating Player with resId=" + mipmapID);
        // - 애니메이션 프레임이 5개이고, 0.25초 동안 애니메이션이 끝나는 경우( 발사 간격 )
        // == 5프레임을 0.25초 안에 보여줘야 한다.
        // fps = (총 프레임 수)/(애니메이션 시간) = (5)/(0.25) = 20
        // -> 총알 발사 간격이 0.25초 동안 5프레임 애니메이션 적용하려면 fps는 20

        this.player = this;

        createAirplane(mipmapID);

        this.joyStick = joyStick;

        setPosition(Metrics.width / 2 - 500, Metrics.height - 600, RADIUS);

        // 화면 경계값 계산
        minX = RADIUS;
        maxX = Metrics.width - RADIUS / 2f;
        minY = RADIUS / 1.25f;
        maxY = Metrics.height - RADIUS;
//        minX = RADIUS / 2f;
//        maxY = Metrics.height - RADIUS / 1.25f;
        // -> 지금은 AnimSprite 기준으로 막는 범위 정한거
        // -> 총 쏘는 애니메이션 그림이 살짝 왼쪽으로 쏠려있어서 그럼
        // -> 그냥 Sprite로 그릴 땐 위의 조건 사용
        updateCollisionRect();

    }

    public Player getPlayer() {
        return this.player;
    }
    public int GetMaxLife() { return MAX_LIFE; }
    private void createAirplane(int mipmapID) {
        if (mipmapID == R.mipmap.sp_shoot_purple) {
            AirPlane = new Sprite(R.mipmap.obj_purple_plane, x, y, 100, 100);
            MAX_LIFE = 5;
            SPEED_X = 700f;
            SPEED_Y = 500f;
            FIRE_INTERVAL = 0.25f;
        }
        else if (mipmapID == R.mipmap.sp_shoot_white){
            AirPlane = new Sprite(R.mipmap.obj_white_plane, x, y, 100, 100);
            MAX_LIFE = 3;
            SPEED_X = 900f;
            SPEED_Y = 600f;
            FIRE_INTERVAL = 0.15f;
        }
        else if (mipmapID == R.mipmap.yellow_shoot) {
            AirPlane = new Sprite(R.mipmap.obj_yellow_plane, x, y, 100, 100);
            MAX_LIFE = 4;
            SPEED_X = 1000f;
            SPEED_Y = 300f;
            FIRE_INTERVAL = 0.4f;
        }
        else if (mipmapID == R.mipmap.brown_shoot){
            AirPlane = new Sprite(R.mipmap.obj_brown_plane, x, y, 100, 100);
            MAX_LIFE = 7;
            SPEED_X = 500f;
            SPEED_Y = 400f;
            FIRE_INTERVAL = 0.35f;
        }
        else {
            AirPlane = new Sprite(R.mipmap.obj_purple_plane, x, y, 100, 100);
            MAX_LIFE = 5;
            SPEED_X = 700f;
            SPEED_Y = 500f;
            FIRE_INTERVAL = 0.25f;
        }
        CURRENT_LIFE = MAX_LIFE;
        AnimSprite Effect = new AnimSprite(R.mipmap.vfx_4, 10f, 7);
        Effect.setPosition(x, y, 100);
        PlaneEffect = Effect;
    }

    public void SetCurrentLIFE(int damage) {
        CURRENT_LIFE -= damage;
        if ( CURRENT_LIFE < 0) {
            CURRENT_LIFE = 0;
        }
    }

    int GetCurrentLIFE() { return CURRENT_LIFE; }

    public void update(){

        fireCoolTime -= GameView.frameTime;
        if( fireCoolTime <= 0 ){
            fireBullet();
            fireCoolTime = FIRE_INTERVAL;
        }

        if(joyStick.power <= 0 ) {
            // 중력 적용
            applyGravity();
            AirPlane.setPosition(x- 30, y + 70, 100);
            PlaneEffect.setPosition(x - 200, y + 80, 100);
            updateCollisionRect();

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
            AirPlane.setPosition(x - 30, y + 70, 100);
            PlaneEffect.setPosition(x - 200, y + 80, 100);
            updateCollisionRect();

        }
    }

    public boolean canBeHit() {
        long now = System.currentTimeMillis();
        return now - lastHitTime > INVINCIBILITY_DURATION;
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

    public void fireBullet() {
        MainScene scene = (MainScene) Scene.top();
        if (scene == null) return;

        int score = scene.getScore();
        int power = 10 + score / 1000;

        Bullet bullet = Bullet.get(x + BULLET_OFFSET_X, y + BULLET_OFFSET_Y, power);
//        scene.add(bullet);

        Scene.top().add(bullet);
        Sound.playEffect(R.raw.jelly);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        AirPlane.draw(canvas);
        PlaneEffect.draw(canvas);
        gauge.draw(canvas, x - RADIUS, y - RADIUS - 20.f, width, fireCoolTime / FIRE_INTERVAL);
    }

    private void updateCollisionRect(){
        collisionRect.set(dstRect);
        collisionRect.inset(15f, 15f);
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }

    public void onHit() {
        if (canBeHit()) {
            CURRENT_LIFE--;
            lastHitTime = System.currentTimeMillis();
        }
    }
}
