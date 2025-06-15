package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.Canvas;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.and.gwang.stealth.R;

public class HeartUI implements IGameObject {
    private final Player player;
    private final Sprite fullHeart;
    private final Sprite emptyHeart;

    private static final int MAX_LIFE = 5; // 최대 하트 수
    private static final float START_X = 100f;
    private static final float START_Y = 50f;
    private static final float SPACING = 60f;

    public HeartUI(Player player) {
        this.player = player;
        this.fullHeart = new Sprite(R.mipmap.ui_full_heart);
        this.emptyHeart = new Sprite(R.mipmap.ui_empty_heart);
    }

    @Override
    public void update() {
        // 생명 수는 Player에게서 실시간으로 받기 때문에 여기선 아무 것도 안 해도 됨
    }

    @Override
    public void draw(Canvas canvas) {
        int life = player.GetCurrentLIFE(); // 현재 생명 수

        for (int i = 0; i < MAX_LIFE; i++) {
            float x = START_X + i * SPACING;

            if (i < life) {
                fullHeart.setPosition(x, START_Y, 30);
                fullHeart.draw(canvas);
            } else {
                emptyHeart.setPosition(x, START_Y, 20);
                emptyHeart.draw(canvas);
            }
        }
    }
}
