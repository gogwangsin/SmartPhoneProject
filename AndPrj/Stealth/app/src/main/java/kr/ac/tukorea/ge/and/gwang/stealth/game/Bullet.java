package kr.ac.tukorea.ge.and.gwang.stealth.game;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class Bullet extends Sprite {
    private static final float BULLET_WIDTH = 100;
    private static final float BULLET_HEIGHT = 100;
    private static final float SPEED = 1000f;

    private static final float TRACE_INTERVAL = 0.05f;
    private float traceTime = 0f;
    private static final float TRACE_OFFSET = 80;

    public Bullet(float x, float y) {
        super(R.mipmap.obj_default_bullet);

        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        dx = SPEED;
    }

    @Override
    public void update() {
        super.update();

        traceTime += GameView.frameTime;
        if(traceTime >= TRACE_INTERVAL) {
            traceTime = 0;
            Scene.top().add(new TraceDot(x - TRACE_OFFSET, y));
        }
    }
}
