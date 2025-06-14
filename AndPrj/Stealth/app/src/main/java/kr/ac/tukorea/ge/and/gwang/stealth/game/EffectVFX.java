package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.Canvas;
import android.util.Log;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.AnimSprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;

public class EffectVFX extends AnimSprite {

    private int max_framecount;
    private int current_framecount = 0;

    public EffectVFX(int mipmapId, float fps, int frameCount) {
        super(mipmapId, fps, frameCount);
        this.max_framecount = frameCount;
        this.current_framecount = 0;
    }

//    public static EffectVFX get(int mipmapId, float fps, int frameCount) {
//        EffectVFX effect = (EffectVFX) Scene.top().getRecyclable(EffectVFX.class);
//
//        if (effect == null) {
//            effect = new EffectVFX(mipmapId, fps, frameCount);
//        } else {
//            effect.max_framecount = frameCount;
//            effect.current_framecount = 0;
//        }
//        return effect;
//    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }

    public void update() {
        current_framecount = GetFrameIndex();
        if (current_framecount >= max_framecount - 1) {
            Scene.top().remove(this);
        }
    }
}
