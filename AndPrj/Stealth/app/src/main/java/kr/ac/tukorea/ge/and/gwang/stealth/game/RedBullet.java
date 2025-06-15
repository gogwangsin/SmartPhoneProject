package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.RectF;

import java.util.ArrayList;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.res.Sound;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.CollisionHelper;

public class RedBullet extends Sprite implements IBoxCollidable {
    private static final float SPEED = 200f; // 천천히 이동
    private static final float WIDTH = Metrics.width / 2.5f;
    private static final float HEIGHT = 1000f;
    protected RectF collisionRect = new RectF();


    public RedBullet(float y) {
        super(R.mipmap.obj_red_bullet, -WIDTH / 2, y, WIDTH, HEIGHT); // 왼쪽 바깥에서 시작
        updateCollisionRect();
    }

    @Override
    public void update() {
        x += SPEED * GameView.frameTime;
        setPosition(x, y, WIDTH, HEIGHT);

        updateCollisionRect();

        if (x - width / 2 > Metrics.width) {
            Scene.top().remove(this); // 화면 밖으로 나가면 제거
            return;
        }

        // 충돌 검사: RedBullet이 적과 충돌하면 적 제거 및 효과
        Scene scene = Scene.top();
        ArrayList<IGameObject> objects = scene.GetGameObjects();

        // 역순 순회로 안전하게 삭제
        for (int i = objects.size() - 1; i >= 0; i--) {
            IGameObject obj = objects.get(i);
            if (!(obj instanceof Enemy)) continue;

            Enemy enemy = (Enemy) obj;
            if (CollisionHelper.collides(this, enemy)) {
                scene.remove(enemy);

                // 효과 출력
                EffectVFX effect = new EffectVFX(R.mipmap.vfx_7, 5, 6);
                effect.setPosition(enemy.GetX(), enemy.GetY(), 100);
                scene.add(effect);

                // 점수 및 사운드
                MainScene.getInstance().addScore(enemy.getScore());
                Sound.playEffect(R.raw.jump1);
            }
        }
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }

    private void updateCollisionRect(){
        collisionRect.set(dstRect);
        collisionRect.inset(WIDTH * 0.2f, 350f);
        // inset()은 RectF의 사각형 영역을 안쪽으로 줄이는 메서드
        // 왼쪽과 오른쪽에서 각각 15f만큼 안쪽으로 줄임
        // 위쪽과 아래쪽에서 각각 25f만큼 안쪽으로 줄임
    }
}
