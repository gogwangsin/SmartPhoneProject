# ✈️ 2025년 스마트폰 게임 프로그래밍 프로젝트

---

## 🎮 1. 게임 컨셉

### High Concept  
**횡스크롤 플라잉 슈팅 게임 (Horizontal Scrolling Flying Shooter)**  
비행기를 탄 주인공이 오른쪽을 향해 비행하며, 상하좌우로 자유롭게 움직이고, 다가오는 적을 총으로 처치하는 액션 게임입니다.

### 핵심 메카닉

- **Game Loop 구성**: `postDelayed()` 또는 `Choreographer`를 활용한 프레임 기반 루프
- **Touch Event Handling**: 상하좌우 이동, 스킬 발동 등 사용자 입력 처리
- **Frame Animation**: 캐릭터/비행기 등의 애니메이션 표현
- **GameObject 클래스 설계**: `GameObject`, `Sprite`, `MainGame` 구성
- **Enemy Generator**: 일정 주기로 등장하는 적 생성 시스템 (`DragonFlight` 참고)
- **Object Lifecycle Management**: 재활용 가능한 오브젝트 관리
- **GameObject Layering**: 배경, 캐릭터, UI 등의 계층별 렌더링
- **점수 / 폰트 출력**: `drawText()` 활용
- **Background Scrolling**: Parallax 방식 횡스크롤
- **Collision Detection**: `Rect` 충돌 판정
- **탄도 구현**: `Bezier Curve`를 이용한 궤적 처리
- **Joystick UI**: 원형 조작 인터페이스 (Virtual Joystick)

---

## 📦 2. 개발 범위

- **플레이어 캐릭터**: 4종  
- **비행기 종류**: 4종 (속도, 이동감 다름)  
- **총알 타입**: 3종 (기본/미사일/범위)  
- **적 몬스터**: 다양한 이동패턴  
- **UI 흐름**: 메뉴 → 캐릭터 선택 → 인게임 → 결과  
- **배경 스크롤**: 멀티 레이어 횡스크롤  
- **사운드**: 효과음, 배경음  
- **충돌 판정**: Rect 기반 구현  

---

## 🖼️ 3. 예상 게임 실행 흐름

### 📍 메인 메뉴  
![main screen](https://github.com/user-attachments/assets/d7d30364-6e86-466b-91ae-b3e4eb99c2aa)

---

### 📍 캐릭터 선택  
캐릭터마다 전용 비행기가 있음  
![character screen](https://github.com/user-attachments/assets/e5f55dea-0409-459b-af33-7ff4c03e4300)

---

### 📍 인게임  
배경은 횡으로 이동, 캐릭터는 상하좌우 이동하며 적과 전투  
![ingame](https://github.com/user-attachments/assets/87791fd3-4f27-4c91-879a-a0464365da36)

---

### 📍 결과 화면  
![result screen](https://github.com/user-attachments/assets/c5e49892-c8db-465a-b0a2-4edb1a17caeb)

---

## 🗓️ 4. 개발 일정 (4월 8일 시작 기준, 8주간)

| 주차 | 주요 개발 내용 |
|------|----------------|
| **1주차** | - 프로젝트 구조 설계 (`MainGame`, `GameObject`)<br>- 기본 View, Canvas, `postDelayed()` 루프 구축<br>- 기본 캐릭터 이미지, 이동 구현 |
| **2주차** | - 터치 이벤트 처리, 조이스틱 구현<br>- `Frame Animation`, 점수 텍스트 출력<br>- 플레이어 기본 공격 및 총알 생성 |
| **3주차** | - 적 생성기 (`EnemyGenerator`) 구현<br>- 적 움직임/패턴/충돌 처리<br>- 오브젝트 재활용 구조 |
| **4주차** | - `Parallax` 배경 스크롤 구현<br>- 충돌 시 적 제거/폭발 처리<br>- 점수 시스템 완성 |
| **5주차** | - `Bezier Curve` 기반 탄 궤도 구현<br>- 다양한 총알 및 스킬 이펙트 구현<br>- 피격 상태 처리 |
| **6주차** | - 캐릭터/비행기 4종 추가<br>- 조작감 및 속도 차이 반영<br>- 캐릭터 선택 UI 구현 |
| **7주차** | - 메뉴/결과 화면 UI 구축<br>- 효과음 및 배경음 추가<br>- 전체 흐름 연결 |
| **8주차** | - 전반적인 버그 수정 및 최적화<br>- 스타일링 마무리 (폰트, 애니메이션 등)<br>- 발표 준비 및 문서화 |

---

## 🛠️ 사용 기술 및 언어

- Android SDK / Java
- Custom View 기반 게임 구조
- Canvas 기반 그리기 / FPS 조절
- Game Framework 설계
- Frame Animation, Collision Logic, Bezier Path

---

## ✨ 기타 리소스

### 🔫 비행기 및 총알 이미지  
![화면 캡처 1](https://github.com/user-attachments/assets/715ffb57-fcd0-4629-bb8b-192d0be047a6)  
![화면 캡처 2](https://github.com/user-attachments/assets/33a6d9b6-4c71-4fd8-9b52-7d6bdc0392ef)

---

