package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.Canvas;

import java.util.Random;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class EnemyGenerator implements IGameObject {
    private static final String TAG = EnemyGenerator.class.getSimpleName();
    private final Random random = new Random();
    private float enemyTime = 0;
    private int wave;


    @Override
    public void update() {
        enemyTime -= GameView.frameTime;
        if ( enemyTime < 0 ){
//            int index = random.nextInt(10);
            generate();
//            Scene.top().add(Enemy.get(index));
            enemyTime = random.nextFloat() + 0.5f;
        }
    }

    private void generate() {
        wave++;
//        StringBuilder enemies = new StringBuilder(); // for debug

        int level = (wave + 8) / 10 - random.nextInt(3);
        if (level < 0) level = 0;
        if (level > Enemy.MAX_LEVEL) level = Enemy.MAX_LEVEL;

        int index = random.nextInt(10);

        Scene.top().add(Enemy.get(level, index));

        // scene.add(Enemy.get(level, i));
//         enemies.append(level); // for debug
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
