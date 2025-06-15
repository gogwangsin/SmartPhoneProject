package kr.ac.tukorea.ge.and.gwang.stealth.app;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import java.io.InputStream;

import kr.ac.tukorea.ge.and.gwang.stealth.R;

import kr.ac.tukorea.ge.and.gwang.stealth.databinding.ActivityCharacterSelectBinding;
import kr.ac.tukorea.ge.and.gwang.stealth.game.MainScene;
import kr.ac.tukorea.ge.and.gwang.stealth.game.Player;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.activity.GameActivity;

public class ChooseActivity extends GameActivity {

    private ActivityCharacterSelectBinding ui;

    private int characterIndex = 0;

    // 아이콘 파일 이름 배열
    private static final int[] ICON_RES_IDS  = {
            R.mipmap.icon_purple,
            R.mipmap.icon_white,
            R.mipmap.icon_yellow,
            R.mipmap.icon_brown,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ui = ActivityCharacterSelectBinding.inflate(getLayoutInflater());
        setContentView(ui.getRoot());

        loadCharacter(characterIndex);

        ui.prevCharacterButton.setOnClickListener(v -> {
            if (characterIndex > 0) {
                characterIndex--;
                loadCharacter(characterIndex);
            }
        });

        ui.nextCharacterButton.setOnClickListener(v -> {
            if (characterIndex < Player.CHARACTER_IDS.length - 1) {
                characterIndex++;
                loadCharacter(characterIndex);
            }
        });

        ui.startGameButton.setOnClickListener(v -> {
            Intent intent = new Intent(this, StealthActivity.class);
            intent.putExtra("CHARACTER_INDEX", characterIndex);
            startActivity(intent);
            finish();
        });
    }

    private void loadCharacter(int index) {
        // 이미지 설정
        ui.characterImageView.setImageResource(ICON_RES_IDS[index]);

        // 이름 설정
        String[] characterNames = {"Purple", "White", "Yellow", "Brown"};
        ui.characterNameTextView.setText(characterNames[index]);

        // 버튼 활성화/비활성화
        ui.prevCharacterButton.setEnabled(index > 0);
        ui.nextCharacterButton.setEnabled(index < ICON_RES_IDS.length - 1);
    }
}