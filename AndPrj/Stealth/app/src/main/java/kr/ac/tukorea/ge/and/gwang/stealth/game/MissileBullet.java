package kr.ac.tukorea.ge.and.gwang.stealth.game;


import android.graphics.Canvas;
import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MissileBullet extends Sprite implements IBoxCollidable {
    private static float SPEED_X = 200f;
    private static float SPEED_Y = 400f;
    private static final float WIDTH = 200f;
    private static final float HEIGHT = 200f;

    protected RectF collisionRect = new RectF();

    public MissileBullet(float startX) {
        super(R.mipmap.obj_missile_bullet, startX, -HEIGHT / 2, WIDTH, HEIGHT);
//        this.angle = 63.4f; // 이동 방향에 맞춰 회전

        // 랜덤 속도 설정
        this.SPEED_X = 400f + (float)(Math.random() * 200f);  // 400 ~ 600
        this.SPEED_Y = this.SPEED_X * (9f / 16f);            // x 속도에 비례해서 y 속도 결정

        updateCollisionRect();
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.save();

        // 캔버스를 미사일 중심으로 이동한 뒤 회전
        canvas.rotate(40.4f, x, y); // 회전 중심: 미사일 중심
        super.draw(canvas); // Sprite 기본 그리기 (회전 적용됨)
        canvas.restore();
    }

    @Override
    public void update() {
        x += SPEED_X * GameView.frameTime;
        y += SPEED_Y * GameView.frameTime;
        setPosition(x, y, WIDTH, HEIGHT);
        updateCollisionRect();

        if (x - width / 2 > Metrics.width || y - height / 2 > Metrics.height) {
            Scene.top().remove(this);
            return;
        }

        Scene scene = Scene.top();
        ArrayList<IGameObject> objects = scene.GetGameObjects();

        for (int i = objects.size() - 1; i >= 0; i--) {
            IGameObject obj = objects.get(i);
            if (!(obj instanceof Enemy)) continue;

            Enemy enemy = (Enemy) obj;
            if (CollisionHelper.collides(this, enemy)) {
                scene.remove(enemy);

                EffectVFX effect = new EffectVFX(R.mipmap.vfx_7, 5, 6);
                effect.setPosition(enemy.GetX(), enemy.GetY(), 100);
                scene.add(effect);

                MainScene.getInstance().addScore(enemy.getScore());
                Sound.playEffect(R.raw.jump1);

            }
        }
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }

    private void updateCollisionRect() {
        collisionRect.set(dstRect);
        collisionRect.inset(10f, 10f);
    }
}
