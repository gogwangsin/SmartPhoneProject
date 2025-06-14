package kr.ac.tukorea.ge.spgp2025.a2dg.framework.objects;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;

public class AnimSprite extends Sprite{
    protected final float fps;
    // 1초에 몇 프레임 보여줄지( 프레임 속도 ) -> frameCount보다 작아도 된다.
    protected final int frameCount;
    // 전체 프레임 수 ( 그림이 몇 칸 나뉘어 있는지 )
    protected final int frameWidth, frameHeight;
    // 한 프레임의 가로/세로 크기
    protected final long createOn;
    // 생성된 시간 (애니메이션이 언제 시작했는지)

    public AnimSprite(int mipmapId, float fps) {
        this(mipmapId, fps, 0);
        // frameCount를 0으로 주면 자동 계산
    }

    public AnimSprite(int mipmapId, float fps, int frameCount) {
        super(mipmapId);

        this.fps = fps;
        int imageWidth = bitmap.getWidth();
        int imageHeight = bitmap.getHeight();

        if (frameCount == 0) {
            this.frameCount = imageWidth / imageHeight;
            this.frameWidth = imageWidth / this.frameCount;
            this.frameHeight = imageHeight;
            // => 가로로 몇 칸 잇는지 -> 정사각형 크기로 잘라서 계산
        }
        else {
            // 가로 길이를 프레임 수로 나눠서 한 프레임 너비 계산
          this.frameWidth = imageWidth / frameCount;
          this.frameHeight = imageHeight;
          this.frameCount = frameCount;
        }

        // 비트맵에서 잘라낼 영역
        srcRect = new Rect();
        createOn = System.currentTimeMillis();
    }

    @Override
    public void draw(Canvas canvas) {
        long now = System.currentTimeMillis();
        float time = (now - createOn) / 1000.0f;
        // (now - createOn)는 millsec(밀리초) 단위 -> 1초 = 1000밀리초이니 -> time을 초 단위로 수정
        // ex) 1초에 10프레임 같이 표현하기 위함

        int frameIndex = Math.round(time * fps) % frameCount;
        // time: 지금까지 흐른 초
        // fps: 1초에 몇 프레임 보여줄지
        // time * fps: 지금까지 몇 프레임만큼 시간이 흘렀는지
        // round( ) 소수점을 반올림해서 정수로 만드는 것
        // % frameCount: 0~9번 프레임 범위로 그리기

        srcRect.set(frameIndex * frameWidth, 0, (frameIndex + 1) * frameWidth, frameHeight);
        canvas.drawBitmap(bitmap, srcRect, dstRect, null);

        // 디버깅용 빨간 테두리 네모
//        if (GameView.drawsDebugStuffs) {
//            Paint paint = new Paint();
//            paint.setStyle(Paint.Style.STROKE); // 테두리만
//            paint.setColor(Color.RED);
//            paint.setStrokeWidth(4);
//
//            canvas.drawRect(dstRect, paint);
//        }
    }
}



















