
# ✈️ 스마트폰 게임 프로그래밍 프로젝트 - "Stealth"
---

## 🎮 1. 게임 컨셉

### High Concept  
**횡스크롤 플라잉 슈팅 게임 (Horizontal Scrolling Flying Shooter)**  
비행기를 탄 주인공이 오른쪽을 향해 비행하며, 상하좌우로 자유롭게 움직이고, 다가오는 적을 총으로 처치하는 액션 게임입니다.


## 🖼️ 예상 게임 실행 흐름

### 📍 인게임  
배경은 횡으로 이동, 캐릭터는 상하좌우 이동하며 적과 전투  

![ingame](https://github.com/user-attachments/assets/87791fd3-4f27-4c91-879a-a0464365da36)

---

## 🗓️ 2. 현재까지 진행 상황 및 개발 일정

| 주차                   | 주요 개발 내용                                                                                       | 진척도 (%)  |
| -------------------- | ---------------------------------------------------------------------------------------------- | -------- |
| **1주차 (4/8\~4/14)**  | - 사용 리소스 정리, Sprite 이미지 생성 <br>- 클래스 설계                                                        | **95%**      |
| **2주차 (4/15\~4/21)** | - 게임 프레임워크 구축<br>- Game Loop 구성 (`postDelayed()` / `Choreographer`)<br>- 캐릭터 이미지 출력 및 기본 이동 구현 | **95%**      |
| **3주차 (4/22\~4/28)** | - 조이스틱 조작으로 상하좌우 이동 구현<br>- 총알 자동 발사 및 발사 애니메이션<br>- 몬스터 등장 및 처치 구현                            | **100%**      |
| **4주차 (4/29\~5/5)**  | - 스킬 아이템 획득 및 스킬 총알 구현<br>- 인게임 UI (체력, 점수 등) 구성                                               | **0%**      |
| **5주차 (5/6\~5/12)**  | - 배경 Parallax 스크롤 구현<br>- 캐릭터 선택 로비 및 다시하기 기능 구현                                               | **50%**      |
| **6주차 (5/13\~5/19)** | - 비행기 4종 추가 및 성능 차별화<br>- Bezier Curve 기반 곡선 총알 궤도 구현                                          | 0%      |
| **7주차 (5/20\~5/26)** | - 메뉴 및 결과 화면 UI 구축<br>- 효과음 및 배경 음악 삽입                                                         | 0%      |
| **8주차 (5/27\~6/2)**  | - 전체 디버깅 및 최적화<br>- 플레이 테스트 및 최종 수정                                                            | 0%      |

---

## 🗓️ 3. Git Commit 자료 ( 총 69번 )

| 주차      | 기간           | 커밋 수 |
| ------- | ------------ | ---- |
| **1주차** | 4/8 \~ 4/14  | 1    |
| **2주차** | 4/15 \~ 4/21 | 12   |
| **3주차** | 4/22 \~ 4/28 | 27   |
| **4주차** | 4/29 \~ 5/5  | 15   |
| **5주차** | 5/6 \~ 5/12  | 0    |

![스크린샷 2025-05-11 012653](https://github.com/user-attachments/assets/39d66358-30af-4871-ac74-9320259cef99)


![스크린샷 2025-05-11 011856](https://github.com/user-attachments/assets/923808b7-c2f2-477e-b6ec-f40fecdeeba0)

---

## 🎮 4. MainScene에 등장하는 Game Object들
<br>

**1. Scene을 상속받은 MainScene -> Object Recycling**

![화면 캡처 2025-05-11 134421](https://github.com/user-attachments/assets/7d05f3a7-3148-4f36-8ff3-3a5fc0fb64d4)
<br>

**2. HorzScrollBackground : 수평 스크롤링 배경**

![화면 캡처 2025-05-11 135215](https://github.com/user-attachments/assets/706c9572-2db8-4b5a-908e-5a6cf93c58fd)
<br> 

**3. joyStick**
**4. Player**

![화면 캡처 2025-05-11 134041](https://github.com/user-attachments/assets/6fe7793a-8a81-49a7-8307-03c5f9e41533)
![화면 캡처 2025-05-11 134056](https://github.com/user-attachments/assets/642eb4f7-8713-4c94-af3e-e2c6ea788b4e)
<br>

**5. EnemyGenerator**
**6. Enemy**

![화면 캡처 2025-05-11 135754](https://github.com/user-attachments/assets/3d47c2a9-b6ad-4a6d-b448-34119f115a3c)
![화면 캡처 2025-05-11 135925](https://github.com/user-attachments/assets/cd0606d7-eecf-429b-8c98-55f9d66f51be)
<br>

**7. Bullet**
**8. TraceDot**

![화면 캡처 2025-05-11 140238](https://github.com/user-attachments/assets/9bf349b9-0e6e-4709-9d30-12c3b77085bd)
![화면 캡처 2025-05-11 140328](https://github.com/user-attachments/assets/f0d0b408-f5df-4e77-b9e4-b647f6c3dc1f)


## 🎮 5. 인게임 화면

![화면 캡처 2025-05-11 113232532](https://github.com/user-attachments/assets/83ad1f77-e57b-4e76-832c-1be48b6b150e)





