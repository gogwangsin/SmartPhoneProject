package kr.ac.tukorea.ge.spgp2025.a2dg.framework.interfaces;


// 목적: "이 객체는 재사용될 수 있으며, 재사용 전에 정리 작업을 수행할 수 있다"는 의미를 부여.
//사용 이유: 메모리를 아끼고 객체 생성을 줄이기 위해 **객체 풀링(Object Pooling)**을 할 때 사용.
//onRecycle() 역할: 객체가 재활용되기 전에 내부 상태를 초기화하는 메서드.
public interface IRecyclable {
    public void onRecycle();
}
