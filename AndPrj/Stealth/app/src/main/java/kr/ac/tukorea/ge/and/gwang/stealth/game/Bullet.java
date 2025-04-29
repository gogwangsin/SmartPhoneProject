package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.RectF;
import android.health.connect.datatypes.Metadata;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Bullet extends Sprite implements IRecyclable, IBoxCollidable {
    private static final float BULLET_WIDTH = 100;
    private static final float BULLET_HEIGHT = 100;
    private static final float SPEED = 1000f;

    private static final float TRACE_INTERVAL = 0.05f;
    private float traceTime = 0f;
    private static final float TRACE_OFFSET = 80;
    protected RectF collisionRect = new RectF();

    private Bullet(float x, float y) {
        super(R.mipmap.obj_default_bullet);
        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        updateCollisionRect();
        dx = SPEED;
    }

    public static Bullet get(float x, float y) {
        // 씬에서 관리하는 재활용 객체 리스트의 원소를 얻었을 때 존재하면 초기위치랑 충돌체 영역 설정하고 반환
        Bullet bullet = (Bullet) Scene.top().getRecyclable(Bullet.class);

        if(bullet == null){
            bullet = new Bullet(x, y);
        }
        else {
            bullet.setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
            bullet.updateCollisionRect();
        }
        return bullet;
    }

    @Override
    public void update() {
        super.update();
        if (dstRect.left > Metrics.width) {
            Scene.top().remove(this);
            return;
        }
        else {
            updateCollisionRect();
        }

        traceTime += GameView.frameTime;
        if(traceTime >= TRACE_INTERVAL) {
            traceTime = 0;
            Scene.top().add(new TraceDot(x - TRACE_OFFSET, y));
        }
    }

    private void updateCollisionRect(){
        collisionRect.set(dstRect);
        collisionRect.inset(15f, 25f);
        // inset()은 RectF의 사각형 영역을 안쪽으로 줄이는 메서드
        // 왼쪽과 오른쪽에서 각각 15f만큼 안쪽으로 줄임
        // 위쪽과 아래쪽에서 각각 25f만큼 안쪽으로 줄임
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }
    @Override
    public void onRecycle() {
    }
}
