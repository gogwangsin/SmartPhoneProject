package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.and.gwang.stealth.app.ChooseActivity;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Score;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseScene extends Scene {
    private int characterIndex;

    public PauseScene(int characterIndex) {
        float w = Metrics.width, h = Metrics.height;
        add(new Sprite(R.mipmap.bg_result, w/2, h/2, w, h));

        //        add(new Button(R.mipmap.btn_restart, w/2, h/2, 200f, 75f, new Button.OnTouchListener() {
//            @Override
//            public boolean onTouch(boolean pressed) {
//                pop();
//                return false;
//            }
//        }));

        add(new Sprite(R.mipmap.score, w/2 - 180, h/2 - 95, 270, 150));

        // ✅ MainScene에서 점수 가져오기
        int currentScore = MainScene.getInstance().getScore();

        // ✅ Score 객체 새로 만들어서 PauseScene에 표시
        Score score = new Score(R.mipmap.number_24x32, w / 2 + 300, h / 2 - 145, 80);  // 위치는 조정 가능
        score.setScore(currentScore);
        add(score);


        add(new Button(R.mipmap.btn_restart, w/2 - 200, h/2 + 205, 270f, 155, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                if (!pressed) return false;

                // 일시정지 씬 닫기
                pop();

                // 새 게임 씬 시작 (캐릭터 선택 없이 기본 캐릭터 인덱스 0으로)
                new MainScene(characterIndex).push();

                return true;
            }
        }));

        add(new Button(R.mipmap.btn_lobby, w/2 + 200, h/2 + 205, 270f, 155f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                if (!pressed) return false;

                Context context = GameView.view.getContext();
                if (context instanceof Activity) {
                    Activity activity = (Activity) context;

                    // 모든 Scene 제거
                    Scene.popAll();

                    // ChooseActivity로 바로 이동
                    Intent intent = new Intent(activity, ChooseActivity.class);
                    activity.startActivity(intent);
                    activity.finish();  // 현재 StealthActivity 종료
                }

                return true;
            }
        }));

        add(new Button(R.mipmap.btn_end, 1350f, 150f, 267f, 100f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                new AlertDialog.Builder(GameView.view.getContext())
                        .setTitle("Confirm")
                        .setMessage("Do you really want to exit the game?")
                        .setNegativeButton("No", null)
                        .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                popAll();
                            }
                        })
                        .create()
                        .show();
                return false;
            }
        }));
    }
}
