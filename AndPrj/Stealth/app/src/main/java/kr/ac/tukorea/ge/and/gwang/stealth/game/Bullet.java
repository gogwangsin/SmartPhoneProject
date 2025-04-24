package kr.ac.tukorea.ge.and.gwang.stealth.game;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;

public class Bullet extends Sprite {
    private static final float BULLET_WIDTH = 100;
    private static final float BULLET_HEIGHT = 100;
    private static final float SPEED = 1000f;

    public Bullet(float x, float y) {
        super(R.mipmap.obj_default_bullet);

        setPosition(x, y, BULLET_WIDTH, BULLET_HEIGHT);
        dx = SPEED;
    }
}
