package kr.ac.tukorea.ge.and.gwang.stealth.app;

import android.os.Bundle;
import android.util.Log;

import kr.ac.tukorea.ge.and.gwang.stealth.BuildConfig;
import kr.ac.tukorea.ge.and.gwang.stealth.game.MainScene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class StealthActivity extends GameActivity {
    private static final String TAG = "StealthActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int characterIndex = getIntent().getIntExtra("CHARACTER_INDEX", 0);
        new MainScene(characterIndex).push();
    }
}