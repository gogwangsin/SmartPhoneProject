package kr.ac.tukorea.ge.and.gwang.stealth.game;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class MainScene extends Scene {

    private final Player player;

    public MainScene() {
        Metrics.setGameSize(1600, 900);

        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        // 디버그 빌드일 때만 나오게 한다

        this.player = new Player();
        add(player);
    }
}
