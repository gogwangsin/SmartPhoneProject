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
    @Override
    public void update() {
        enemyTime -= GameView.frameTime;
        if ( enemyTime < 0 ){
            int index = random.nextInt(10);
            Scene.top().add(new Enemy(index));
            enemyTime = random.nextFloat() + 0.5f;
        }
    }

    @Override
    public void draw(Canvas canvas) {

    }

}
