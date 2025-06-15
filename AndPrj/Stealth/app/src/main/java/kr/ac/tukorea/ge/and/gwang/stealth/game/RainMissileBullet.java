package kr.ac.tukorea.ge.and.gwang.stealth.game;


import android.graphics.Canvas;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class RainMissileBullet implements IGameObject {
    private static final float SPAWN_INTERVAL = 0.3f; // 0.3초마다 생성
    private static final float DURATION = 5.0f; // 5초간 유지

    private float timeElapsed = 0f;
    private float spawnTimer = 0f;

    @Override
    public void update() {
        float delta = GameView.frameTime;
        timeElapsed += delta;
        spawnTimer += delta;

        if (timeElapsed >= DURATION) {
            Scene.top().remove(this);
            return;
        }

        if (spawnTimer >= SPAWN_INTERVAL) {
            spawnTimer -= SPAWN_INTERVAL;
            spawnMissile();
        }
    }
    @Override
    public void draw(Canvas canvas) {
    }

    private void spawnMissile() {
        float step = 50f; // 50픽셀 간격
        int maxSteps = (int)((Metrics.width / 3f) / step);
        float randX = step * (float)(Math.floor(Math.random() * maxSteps));
        Scene.top().add(new MissileBullet(randX));
    }
}
