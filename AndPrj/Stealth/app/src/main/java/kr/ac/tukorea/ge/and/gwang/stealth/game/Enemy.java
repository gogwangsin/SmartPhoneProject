package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.RectF;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class Enemy extends Sprite implements IRecyclable, IBoxCollidable {
    private static final float SPEED = 300f;
    private static final float RADIUS = 50f;
    protected RectF collisionRect = new RectF();

    private Enemy(int index) {
        super(R.mipmap.obj_blue_monster);

        setPosition(Metrics.width + RADIUS, Metrics.height / 20 * (2 * index + 1), RADIUS);
        // index가 0~9일 때 (2 * index + 1)의 결과는 1, 3, 5, ..., 19 → 총 10개
        // 화면을 20등분하고 홀수 칸마다 1개씩 배치하는 구조
        // width / 20 * 0 → 0 (경계선)
        // (width / 20 * 1) → 첫 번째 칸의 가운데
        // => 홀수칸만 사용하면 항상 칸의 중앙에 위치

        updateCollisionRect();
        dx = -SPEED;
    }

    public static Enemy get(int index) {
        return new Enemy(index);
    }

    @Override
    public void update() {
        super.update();
        if (dstRect.right < 0) {
            Scene.top().remove(this);
        } else {
            updateCollisionRect();
        }
    }

    private void updateCollisionRect(){
        collisionRect.set(dstRect);
        collisionRect.inset(11f, 11f);
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }
    @Override
    public void onRecycle() {
    }
}
