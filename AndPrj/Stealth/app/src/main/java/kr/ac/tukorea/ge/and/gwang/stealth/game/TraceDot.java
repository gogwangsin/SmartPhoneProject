package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.os.Trace;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class TraceDot extends Sprite {
    private static final float DOT_WIDTH = 50;
    private static final float DOT_HEIGHT = 20;
    private static final float LIFE_TIME = 0.25f;
    // private static final float OFFSET_AMPLITUDE = 15f; // 좌우로 흔들리는 크기
    // private final float baseX, baseY;
    private float elapsedTime = 0;

    public TraceDot(float x, float y ){
        super(R.mipmap.obj_vfx_bullet);
        // this.baseX = x;
        // this.baseY = y;
        setPosition(x, y, DOT_WIDTH, DOT_HEIGHT);
    }

    @Override
    public void update() {
        elapsedTime += GameView.frameTime;

        // 흔들리는 x 오프셋 (사인 곡선 사용)
        //        float offsetY = (float)(Math.sin(elapsedTime * 20) * OFFSET_AMPLITUDE); // 20은 흔들림 속도
        //        float currentY = baseY + offsetY;
        //
        //        setPosition(baseX, currentY, DOT_WIDTH, DOT_HEIGHT);

        if (elapsedTime > LIFE_TIME) {
            // 삭제
            Scene.top().remove(this);
        }
    }

}
