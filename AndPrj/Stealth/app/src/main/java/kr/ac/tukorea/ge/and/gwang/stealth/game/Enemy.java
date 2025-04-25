package kr.ac.tukorea.ge.and.gwang.stealth.game;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class Enemy extends Sprite {
    private static final float SPEED = 300f;
    private static final float RADIUS = 100f;

    public Enemy(int index) {
        super(R.mipmap.obj_blue_monster);

        // 오른쪽 바깥에서 아래쪽 중간 높이로 등장
        setPosition(Metrics.width + RADIUS, Metrics.height * 0.6f, RADIUS);

        dx = -SPEED;
    }

    @Override
    public void update() {
        super.update();
        if (dstRect.right < 0) {
            Scene.top().remove(this);
        }
    }
}
