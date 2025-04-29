package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {
    private static final String TAG = MainScene.class.getSimpleName();

    private final Player player;

    private JoyStick joyStick;

    public MainScene() {
        Metrics.setGameSize(1600, 900);

        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        // 디버그 빌드일 때만 나오게 한다

        joyStick = new JoyStick(
                R.mipmap.bg_joystick, R.mipmap.thumb_joystick,
                1380, 680,  120, 50,
                100
        );

        this.player = new Player(joyStick);
        add(player);
        add(new EnemyGenerator());
        add(joyStick);
    }

    @Override
    public void update() {
        super.update();
        checkCollision();
    }

    private void checkCollision() {
        for (IGameObject o1 : gameObjects) {
            if (!(o1 instanceof Enemy)) {
                continue;
            }
            Enemy enemy = (Enemy) o1;
//            boolean removed = false;

            for (IGameObject o2 : gameObjects) {
                if (!(o2 instanceof Bullet)) {
                    continue;
                }
                Bullet bullet = (Bullet) o2;
                
                if (CollisionHelper.collides(enemy, bullet)) {
                    Log.d(TAG, "Collision !!");
                    remove(bullet);
                    remove(enemy);
//                    removed = true;
                    break;
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return joyStick.onTouch(event);
    }

}
