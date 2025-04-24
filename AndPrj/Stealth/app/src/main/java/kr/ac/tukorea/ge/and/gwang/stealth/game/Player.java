package kr.ac.tukorea.ge.and.gwang.stealth.game;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends Sprite {
    private static final float RADIUS = 100f;

    public Player (){
        super(R.mipmap.obj_purple_side);
        setPosition(Metrics.width / 2, Metrics.height - 200, RADIUS);
    }
}
