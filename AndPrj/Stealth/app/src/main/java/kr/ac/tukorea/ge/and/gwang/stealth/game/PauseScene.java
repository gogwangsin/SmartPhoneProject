package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.app.AlertDialog;
import android.content.DialogInterface;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Button;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene.Scene;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class PauseScene extends Scene {

    public PauseScene() {
        float w = Metrics.width, h = Metrics.height;
        add(new Sprite(R.mipmap.bg_result, w/2, h/2, 1200f, 800f));
        add(new Button(R.mipmap.btn_resume_n, w/2, h/2, 200f, 75f, new Button.OnTouchListener() {
            @Override
            public boolean onTouch(boolean pressed) {
                pop();
                return false;
            }
        }));

        add(new Button(R.mipmap.btn_exit_n, 800f, 550f, 267f, 100f, new Button.OnTouchListener() {
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
