package kr.ac.tukorea.ge.spgp2025.a2dg.framework.scene;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Color;
import android.graphics.RectF;
import android.view.MotionEvent;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IBoxCollidable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IRecyclable;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.GameView;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces.IGameObject;
import kr.ac.tukorea.ge.spgp2025.a2dg.framework.view.Metrics;

public class Scene {
    private static final String TAG = Scene.class.getSimpleName();
    protected final ArrayList<IGameObject> gameObjects = new ArrayList<>();

    //////////////////////////////////////////////////
    // Game Object Management
    public void add(IGameObject gameObject) {
        gameObjects.add(gameObject);
        //Log.d(TAG, gameObjects.size() + " objects in " + this);
    }

    public void remove(IGameObject gobj) {
        gameObjects.remove(gobj);
        if(gobj instanceof IRecyclable){
            collectRecyclable((IRecyclable) gobj);
            ((IRecyclable) gobj).onRecycle();
        }
    }

    public int count() {
        return gameObjects.size();
    }

    //////////////////////////////////////////////////
    // Object Recycling
    protected HashMap<Class, ArrayList<IRecyclable>> recycleBin = new HashMap<>();
    // Class → key: 클래스 타입 (Bullet.class, Enemy.class 등)
    // ArrayList<IRecyclable> → value: 그 클래스 타입의 재활용 가능한 객체 리스트
    //✅ 목적: 지우지 말고 나중에 다시 써서 GC(가비지 컬렉션) 부담 줄이기.

    public void collectRecyclable(IRecyclable object) {
        Class clazz = object.getClass();
        // 해당 객체의 클래스 정보 획득

        ArrayList<IRecyclable> bin = recycleBin.get(clazz);
        // 그 클래스에 해당하는 재활용 리스트(bin)를 조회.

        if (bin == null){
            // 없다면 새 리스트 만들어 recycleBin에 등록.
            bin = new ArrayList<>();
            recycleBin.put(clazz, bin);
        }

        //object.onRecycle();
        // 객체가 재활용통에 들어가기 전에 정리해야 할 것이 있다면 여기서 한다
        // 버그 추적이 어려운 "재활용된 객체가 이상하게 작동함" 같은 문제에 대비
        bin.add(object);
    }

    public IRecyclable getRecyclable(Class clazz) {
        ArrayList<IRecyclable> bin = recycleBin.get(clazz);
        if (bin == null) return null;
        if (bin.size() == 0) return null;

        // 있다면 bin.remove(0) → 제일 앞에 있는 객체 꺼냄.
        return bin.remove(0);
    }

    //////////////////////////////////////////////////
    // Game Loop Functions

    public void update() {
        int count = gameObjects.size();
        for (int i = count - 1; i >= 0; i--) {
            IGameObject gobj = gameObjects.get(i);
            gobj.update();
        }
    }
    public void draw(Canvas canvas) {
        if (this.clipRect()){
            canvas.clipRect(0,0, Metrics.width, Metrics.height);
        }

        for (IGameObject gobj : gameObjects) {
            gobj.draw(canvas);
        }

        if(GameView.drawsDebugStuffs){
            // bboxPaint가 없으면 새로 만든다 (한 번만 생성됨)
            if(bboxPaint == null) {
                bboxPaint = new Paint();
                bboxPaint.setStyle(Paint.Style.STROKE); // 테두리만 그리도록 설정
                bboxPaint.setColor(Color.RED);
                bboxPaint.setStrokeWidth(4);
            }

            // 모든 게임 오브젝트 순회
            for(IGameObject gobj : gameObjects) {
                // 충돌 영역을 가진 오브젝트라면
                if (gobj instanceof IBoxCollidable){
                    RectF rect = ((IBoxCollidable) gobj).getCollisionRect();
                    canvas.drawRect(rect, bboxPaint);
                }
            }
        }
    }

    protected static Paint bboxPaint;

    //////////////////////////////////////////////////
    // Scene Stack Functions

    public void push() {
        GameView.view.pushScene(this);
    }
    public static Scene pop() {
        return GameView.view.popScene();
    }
    public static Scene top() {
        return GameView.view.getTopScene();
    }

    //////////////////////////////////////////////////
    // Overridables
    public boolean onTouchEvent(MotionEvent event) {
        return false;
    }

    public void onEnter() {
        Log.v(TAG, "onEnter: " + getClass().getSimpleName());
    }
    public void onPause() {
        Log.v(TAG, "onPause: " + getClass().getSimpleName());
    }
    public void onResume() {
        Log.v(TAG, "onResume: " + getClass().getSimpleName());
    }
    public void onExit() {
        Log.v(TAG, "onExit: " + getClass().getSimpleName());
    }

    public boolean onBackPressed() {
        return false;
    }

    public boolean clipRect() { return true; }
}

/*
🔍 GC의 기본 원리
    GC의 핵심 목표는 다음 두 가지야:
    쓸모없는 객체(더 이상 접근할 수 없는 객체)를 찾는다.
    그 객체가 점유하던 메모리를 회수한다.

📌 1. 어떤 객체가 "쓸모없는지"는 어떻게 판단할까?
    GC는 루트(Root) 객체들에서 출발해서 참조 그래프를 탐색해.
    루트에서 도달할 수 없는 객체는 "더 이상 사용되지 않음"으로 간주됨.

루트(Root)의 예:
    지역 변수
    정적(static) 변수
    스레드 스택에서 참조하는 객체

    📌 이걸 Reachability 분석이라고 해.

🔁 GC의 동작 방식 (대표적인 방식 기준)
    1. Mark and Sweep (표시 & 제거 방식)
        Mark 단계: 루트 객체부터 시작해 참조된 객체를 전부 '도달 가능(marked)' 표시
        Sweep 단계: 도달 표시가 안 된 객체를 메모리에서 제거

    2. Generational GC (세대별 GC)
        객체는 세대별로 나뉘어서 관리됨:
            Young Generation: 새로 생성된 객체
            Old Generation: 오래 살아남은 객체

        대부분의 객체는 짧게 살기 때문에, Young 세대를 더 자주 수집해 효율을 높임.

🔄 Java에서 GC는 언제 일어날까?
    명확한 조건 없이 JVM이 판단해서 자동 실행
    메모리가 부족할 때
    또는 System.gc()를 호출하면 GC 요청은 가능하지만 즉시 보장되지 않음

🔧 GC가 없는 경우엔?
C, C++처럼 GC가 없는 언어는:
    malloc과 free 또는 new와 delete를 직접 관리해야 해.
    실수하면 메모리 누수, 더블 해제, 사용 후 해제(Use-after-free) 등의 버그 발생 가능.

🔥 왜 게임에서는 GC를 피하려고 할까?
    GC는 실행 중에 **일시 정지(stop-the-world)**가 발생할 수 있어.
    빠른 프레임 레이트를 유지해야 하는 게임에서는 GC로 인한 렉이나 끊김이 큰 문제.
    그래서 아까처럼 객체 풀(재활용) 방식으로 미리 만들어놓고 계속 재사용함.

 */