package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.Canvas;
import android.graphics.RectF;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.util.Gauge;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class Enemy extends Sprite implements IRecyclable, IBoxCollidable {
    private static final float SPEED = 300f;
    private static final float RADIUS = 50f;
    protected RectF collisionRect = new RectF();
//    public static final int MAX_LEVEL = resIds.length - 1;
    public static final int MAX_LEVEL = 20;
    private int level;
    private int life, maxLife;
    protected Gauge gauge = new Gauge(0.1f, R.color.enemy_gauge_bg, R.color.enemy_gauge_fg);

    private Enemy(int level, int index) {
        super(R.mipmap.obj_blue_monster);
        setPosition(Metrics.width + RADIUS, Metrics.height / 20 * (2 * index + 1), RADIUS);
        // index가 0~9일 때 (2 * index + 1)의 결과는 1, 3, 5, ..., 19 → 총 10개
        // 화면을 20등분하고 홀수 칸마다 1개씩 배치하는 구조
        // width / 20 * 0 → 0 (경계선)
        // (width / 20 * 1) → 첫 번째 칸의 가운데
        // => 홀수칸만 사용하면 항상 칸의 중앙에 위치
        updateCollisionRect();
        dx = -SPEED;
        this.level = level;
        this.life = this.maxLife = (level + 1) * 10;
    }

    public static Enemy get(int level, int index) {
        Enemy enemy = (Enemy) Scene.top().getRecyclable(Enemy.class);
        if(enemy == null) {
            enemy = new Enemy(level, index);
        }
        else {
            enemy.level = level;
            enemy.life = enemy.maxLife = (level + 1) * 10;
            enemy.setPosition(Metrics.width + RADIUS, Metrics.height / 20 * (2 * index + 1), RADIUS);
            enemy.updateCollisionRect();
        }
        return enemy;
    }

    public int getScore() {
        return (level + 1) * 100;
    }

    public boolean decreaseLife(int power) {
        life -= power;
        return life <= 0;
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

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float gauge_width = width * 0.7f;
        float gauge_x = x - gauge_width / 2;
        float gauge_y = dstRect.bottom;
        gauge.draw(canvas,gauge_x, gauge_y, gauge_width, (float)life / maxLife);
    }

    private void updateCollisionRect(){
        collisionRect.set(dstRect);
        collisionRect.inset(15f, 15f);
    }

    public RectF getCollisionRect() {
        return collisionRect;
    }
    @Override
    public void onRecycle() {
    }
}
