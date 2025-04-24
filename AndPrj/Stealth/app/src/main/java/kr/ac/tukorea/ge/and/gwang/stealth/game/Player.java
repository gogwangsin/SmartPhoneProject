package kr.ac.tukorea.ge.and.gwang.stealth.game;

import android.graphics.Canvas;

import kr.ac.tukorea.ge.and.gwang.stealth.R;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.JoyStick;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects.Sprite;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Player extends Sprite {
    private static final String TAG = Player.class.getSimpleName();

    private static final float SPEED_X = 700.f;
    private static final float SPEED_Y = 500.f;

    private static final float RADIUS = 100f;
    private final JoyStick joyStick;
    private float angle;

    public Player (JoyStick joyStick){
        super(R.mipmap.obj_purple_side);

        this.joyStick = joyStick;

        setPosition(Metrics.width / 2, Metrics.height - 200, RADIUS);
    }

    public void update(){

        if(joyStick.power <= 0 ) {
            return;
        }

        float distance_x = SPEED_X * joyStick.power * GameView.frameTime;
        float distance_y = SPEED_Y * joyStick.power * GameView.frameTime;

        x += (float) ( distance_x * Math.cos(joyStick.angle_radian));
        y += (float) ( distance_y * Math.sin(joyStick.angle_radian));

        setPosition(x, y, RADIUS);

        angle = (float)Math.toDegrees(joyStick.angle_radian);
    }

    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
