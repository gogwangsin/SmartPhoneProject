
# ✈️ 스마트폰 게임 프로그래밍 프로젝트 - "Stealth"
---

## 🎮 1. 게임 컨셉

### High Concept  
**횡스크롤 플라잉 슈팅 게임 (Horizontal Scrolling Flying Shooter)**  
비행기를 탄 주인공이 오른쪽을 향해 비행하며, 상하좌우로 자유롭게 움직이고, 다가오는 적을 총으로 처치하는 액션 게임입니다.

### 핵심 메카닉

- **Game Loop 구성**: `postDelayed()` 또는 `Choreographer`를 활용한 프레임 기반 루프
- **Touch Event Handling**: 상하좌우 이동, 스킬 발동 등 사용자 입력 처리
- **Frame Animation**: 캐릭터/비행기 등의 애니메이션 표현
- **Enemy Generator**: 일정 주기로 등장하는 적 생성 시스템 (`DragonFlight` 참고)
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
| **1주차 (4/8~4/14)** | - 사용 리소스 정리, Sprite 이미지 생성 <br>- 클래스 설계 |
| **2주차 (4/15~4/21)** | - 게임 프레임워크 구축<br>- Game Loop 구성 (`postDelayed()` / `Choreographer`)<br>- 캐릭터 이미지 출력 및 기본 이동 구현 |
| **3주차 (4/22~4/28)** | - 조이스틱 조작으로 상하좌우 이동 구현<br>- 총알 자동 발사 및 발사 애니메이션<br>- 몬스터 등장 및 처치 구현 |
| **4주차 (4/29~5/5)** | - 스킬 아이템 획득 및 스킬 총알 구현<br>- 인게임 UI (체력, 점수 등) 구성 |
| **5주차 (5/6~5/12)** | - 배경 Parallax 스크롤 구현<br>- 캐릭터 선택 로비 및 다시하기 기능 구현 |
| **6주차 (5/13~5/19)** | - 비행기 4종 추가 및 성능 차별화<br>- Bezier Curve 기반 곡선 총알 궤도 구현 |
| **7주차 (5/20~5/26)** | - 메뉴 및 결과 화면 UI 구축<br>- 효과음 및 배경 음악 삽입 |
| **8주차 (5/27~6/2)** | - 전체 디버깅 및 최적화<br>- 플레이 테스트 및 최종 수정 |


---
