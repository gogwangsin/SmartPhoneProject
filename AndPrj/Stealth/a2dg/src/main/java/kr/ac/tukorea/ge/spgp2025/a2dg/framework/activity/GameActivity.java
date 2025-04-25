package kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowInsets;
import android.view.WindowInsetsController;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class GameActivity extends AppCompatActivity {
    private static final String TAG = GameActivity.class.getSimpleName();

    private GameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gameView = new GameView(this);
        setContentView(gameView);

        setFullScreen();

        gameView.setEmptyStackListener(new GameView.OnEmptyStackListener() {
            @Override
            public void onEmptyStack() {
                // 현재 Activity 종료 -> 현재 화면(Activity)를 닫고, 이전 화면으로 돌아가거나 앱 종료
                finish();
            }
        });
        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);
    }

    private final OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
        @Override
        public void handleOnBackPressed() {
            gameView.onBackPressed();
        }
    };

    // Activity 생명주기( LifeCycle )에서 자동 호출되는 콜백 함수들
    @Override
    protected void onPause() {
        gameView.pauseGame();
        // 다른 액티비티가 뜰 때
        // 홈 버튼으로 앱이 백그라운드로 갈 때
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameView.resumeGame();
        // 다시 앱으로 돌아왔을 때
        // 다른 액티비티 갔다가 finish()하고 돌아왔을 때
    }

    @Override
    protected void onDestroy(){
        Log.i(TAG, "onDestroy");
        gameView.destroyGame();
        // 사용자가 뒤로가기 눌러서 종료
        // 시스템이 메모리 회수 위해 강제종료
        super.onDestroy();
    }

    @SuppressWarnings("deprecation")
    public void setFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // API 30 이상: 최신 방식
            WindowInsetsController insetsController = getWindow().getInsetsController();
            if (insetsController != null) {
                insetsController.setSystemBarsBehavior(
                        WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
                );
                insetsController.hide(WindowInsets.Type.systemBars());
            }
        } else {
            // API 29 이하: 기존 방식
            int flags = View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY |
                    View.SYSTEM_UI_FLAG_FULLSCREEN |
                    View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
            gameView.setSystemUiVisibility(flags);
        }
    }
}