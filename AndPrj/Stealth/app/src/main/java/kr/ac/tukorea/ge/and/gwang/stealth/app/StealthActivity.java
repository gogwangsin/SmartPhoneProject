package kr.ac.tukorea.ge.and.gwang.stealth.app;

import android.os.Bundle;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.and.gwang.stealth.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class StealthActivity extends GameActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        GameView.drawsDebugStuffs = BuildConfig.DEBUG;
        super.onCreate(savedInstanceState);
        new MainScene().push();
    }
}