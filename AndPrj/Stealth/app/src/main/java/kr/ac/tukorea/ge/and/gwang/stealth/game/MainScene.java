package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Score;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();

    private final Player player;
    private final Score score;
    private JoyStick joyStick;

    private Button button1;
    private Button button2;

    private int CharacterIndex = 0;
    private static MainScene instance;

//    private static final String TAG = MainScene.class.getSimpleName();

    public MainScene(int characterIndex) {
        Metrics.setGameSize(1600, 900);

        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        // 디버그 빌드일 때만 나오게 한다
        instance = this;

        add(new HorzScrollBackground(R.mipmap.bg_digital, 200));

        joyStick = new JoyStick(
                R.mipmap.bg_joystick, R.mipmap.thumb_joystick,
                1380, 680,  120, 50,
                100
        );

        // 캐릭터 인덱스에 따라 리소스 선택
        int[] playerResIds = {
                R.mipmap.sp_shoot_purple,
                R.mipmap.sp_shoot_white,
                R.mipmap.yellow_shoot,
                R.mipmap.brown_shoot,
        };
        CharacterIndex = characterIndex;
        this.player = new Player(joyStick, playerResIds[characterIndex]);
        add(player);
        add(new EnemyGenerator());

        this.score = new Score(R.mipmap.number_24x32, 1450f, 50f, 60f);
        score.setScore(0);
        add(score);

        Sprite s1 = new Sprite(R.mipmap.icon_01);
        s1.setPosition(150f, 800f, 85f);
        add(s1);
        Sprite s2 = new Sprite(R.mipmap.icon_01);
        s2.setPosition(330f, 800f, 85f);
        add(s2);

        button1 = new Button(R.mipmap.obj_missile_bullet,
                150f, 800f, 150f, 150f, new Button.OnTouchListener() {
            @Override            public boolean onTouch(boolean pressed) {
                Log.d("BUTTON", "Missile button pressed: " + pressed);
                if (pressed) player.FireRainMissiles(); // 눌릴 때만 발사
                return true; // 이벤트 처리 완료됨을 알림
            }
        });
        add(button1);

        button2 = new Button(R.mipmap.obj_red_bullet,
                330f, 800f, 150f, 150f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                Log.d("BUTTON", "Missile button pressed: " + pressed);
                if (pressed) player.FireREDBullet(); // 눌릴 때만 발사
                return true; // 이벤트 처리 완료됨을 알림
            }
        });
        add(button2);

        add(joyStick);
//
//        add(new Button(R.mipmap.btn_pause, 1500f, 100f, 100f, 100f, new Button.OnTouchListener() {
//            @Override
//            public boolean onTouch(boolean pressed) {
//                new PauseScene(characterIndex).push();
//                return false;
//            }
//        }));

        add(new HeartUI(player));
    }

    @Override
    public void update() {
        super.update();
        checkCollision();
    }

    public Player getPlayer() {
        return this.player;
    }
    public void addScore(int amount) {
        score.add(amount);
    }
    public int getScore() {
        return score.getScore();
    }


    public static MainScene getInstance() {
        return instance;
    }

    private void checkCollision() {
        int count = gameObjects.size();

        // 리스트를 역순으로 순회하는 이유는 remove()로 오브젝트를 삭제할 수 있기 때문
        //→ 삭제 시 인덱스가 밀리는 걸 방지하려고 뒤에서부터 순회하는 것.
        for (int i1 = count - 1; i1 >= 0; i1--){
            count = gameObjects.size();
            if(i1 >= count){
                i1 = count - 1;
                // remove로 크기가 5 -> 4인 경우에
                // i1이 [4] 인덱스를 가리킨다면 -> 존재하지 않는 인덱스를 건드림
                //
                // 대안 -> 현재 리스트 길이 다시 확인하고
                // 인덱스가 범위를 넘어가면 안전한 최대 인덱스(count - 1)로 보정
            }

            IGameObject o1 = gameObjects.get(i1);
            if(!(o1 instanceof Enemy)){
                continue;
            }
            Enemy enemy = (Enemy) o1;

            count = gameObjects.size();
            for(int i2 = count - 1; i2 >= 0; i2--){
                IGameObject o2 = gameObjects.get(i2);
                if(!(o2 instanceof Bullet)){
                    continue;
                }
                Bullet bullet = (Bullet) o2;

                if (CollisionHelper.collides(enemy, bullet)) {
//                    Log.d(TAG, "Collision !! : Bullet@" + System.identityHashCode(bullet) + " vs Enemy@" + System.identityHashCode(enemy));
                    remove(bullet);


                    boolean dead = enemy.decreaseLife(bullet.getPower());
                    if (dead){
                        Sound.playEffect(R.raw.jump1);
                        remove(enemy);
                        addScore(enemy.getScore());
                        EffectVFX effect = new EffectVFX(R.mipmap.vfx_7, 5,6);
                        effect.setPosition(enemy.GetX(), enemy.GetY(), 100);
                        Scene.top().add(effect);
                    }else {
                        EffectVFX effect = new EffectVFX(R.mipmap.vfx_10, 5,5);
                        effect.setPosition(enemy.GetX(), enemy.GetY(), 100);
                        Scene.top().add(effect);
                    }
//                    removed = true;
                    break;
                    // break: 한 번 충돌했으면 더 이상 이 적에 대해 검사하지 않음
                }
            }

            // Player vs Enemy 충돌 검사
            Player player = MainScene.getInstance().getPlayer(); // 또는 this.player
            for (int i = gameObjects.size() - 1; i >= 0; i--) {
                IGameObject obj = gameObjects.get(i);
                if (!(obj instanceof Enemy)) continue;
                Enemy enemy_2 = (Enemy) obj;

                if (CollisionHelper.collides(player, enemy_2)) {
                    // 플레이어와 적 둘 다 제거
                    remove(enemy_2);

                    // 이펙트 추가
                    EffectVFX effect = new EffectVFX(R.mipmap.vfx14, 10,5);
                    effect.setPosition(player.GetX(), player.GetY(), 300);
                    Scene.top().add(effect);

                    // 생명 감소는 무적 여부 확인 후 실행
                    Sound.playEffect(R.raw.hurt);
                    player.onHit();
                    if ( player.GetCurrentLIFE() <= 0 ) {
                        new PauseScene(CharacterIndex).push();
                    }

                    break;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
//        return joyStick.onTouch(event);

        boolean handled = false;

        List<Button> buttons = new ArrayList<>();
        for (IGameObject obj : gameObjects) {
            if (obj instanceof Button) {
                buttons.add((Button) obj);
            }
        }
        for (Button button : buttons) {
            handled |= button.onTouchEvent(event);
        }

        // 조이스틱에도 이벤트 전달
        handled |= joyStick.onTouch(event);

        return handled;

    }

    @Override
    public void onEnter() {
        Sound.playMusic(R.raw.main);
    }

    @Override
    public void onExit() {
        Sound.stopMusic();
    }

    @Override
    public void onPause() {
        Sound.pauseMusic();
    }

    @Override
    public void onResume() {
        Sound.resumeMusic();
    }

}
