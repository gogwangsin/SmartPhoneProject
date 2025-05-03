package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.HorzScrollBackground;
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

        add(new HorzScrollBackground(R.mipmap.bg_digital, 200));

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
                    Log.d(TAG, "Collision !! : Bullet@" + System.identityHashCode(bullet) + " vs Enemy@" + System.identityHashCode(enemy));
                    remove(bullet);
                    remove(enemy);
//                    removed = true;
                    break;
                    // break: 한 번 충돌했으면 더 이상 이 적에 대해 검사하지 않음
                }
            }

        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        return joyStick.onTouch(event);
    }

}
