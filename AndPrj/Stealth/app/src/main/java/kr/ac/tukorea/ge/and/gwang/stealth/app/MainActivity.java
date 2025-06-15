package kr.ac.tukorea.ge.and.gwang.stealth.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import kr.ac.tukorea.ge.and.gwang.stealth.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
//            startActivity(new Intent(this, StealthActivity.class));
//            startActivity(new Intent(this, CharacterSelectActivity.class));
            startActivity(new Intent(this, ChooseActivity.class));
        }

        return super.onTouchEvent(event);
    }
}