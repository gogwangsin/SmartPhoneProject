# ✈️ 스마트폰 게임 프로그래밍 프로젝트 - "Stealth"
---
## 🎮 0. 발표 동영상 링크
https://www.youtube.com/watch?v=zOb49e0ond4


## 🎮 1. 간단 게임 소개 

### High Concept  
**횡스크롤 플라잉 슈팅 게임 (Horizontal Scrolling Flying Shooter)**  
비행기를 탄 주인공이 오른쪽을 향해 비행하며, 상하좌우로 자유롭게 움직이고, 다가오는 적을 총으로 처치하는 액션 게임입니다.
![스크린샷 2025-06-16 023053](https://github.com/user-attachments/assets/227f0ef8-2f5a-46ac-8ec4-8b1f6c413f36)


## 🗓️ 2. 개발 계획 / 일정 / 실제 진행

| 주차                   | 주요 개발 내용                                                                                       | 진척도 (%)  |
| -------------------- | ---------------------------------------------------------------------------------------------- | -------- |
| **1주차 (4/8\~4/14)**  | - 사용 리소스 정리, Sprite 이미지 생성 <br>- 클래스 설계                                                        | **100%**      |
| **2주차 (4/15\~4/21)** | - 게임 프레임워크 구축<br>- Game Loop 구성 (`postDelayed()` / `Choreographer`)<br>- 캐릭터 이미지 출력 및 기본 이동 구현 | **100%**      |
| **3주차 (4/22\~4/28)** | - 조이스틱 조작으로 상하좌우 이동 구현<br>- 총알 자동 발사 및 발사 애니메이션<br>- 몬스터 등장 및 처치 구현                            | **100%**      |
| **4주차 (4/29\~5/5)**  | - 스킬 아이템 획득 및 스킬 총알 구현<br>- 인게임 UI (체력, 점수 등) 구성                                               | **90%**      |
| **5주차 (5/6\~5/12)**  | - 배경 Parallax 스크롤 구현<br>- 캐릭터 선택 로비 및 다시하기 기능 구현                                               | **95%**      |
| **6주차 (5/13\~5/19)** | - 비행기 4종 추가 및 성능 차별화<br>- Bezier Curve 기반 곡선 총알 궤도 구현                                          | **80%**      |
| **7주차 (5/20\~5/26)** | - 메뉴 및 결과 화면 UI 구축<br>- 효과음 및 배경 음악 삽입                                                         | **90%**      |
| **8주차 (5/27\~6/2)**  | - 전체 디버깅 및 최적화<br>- 플레이 테스트 및 최종 수정                                                            | **100%**      |

---

## 🗓️ 3. Git Commit 자료 ( 총 91번 )

| 주차      | 기간           | 커밋 수 |
| ------- | ------------ | ---- |
| **1주차** | 4/8 \~ 4/14  | 1    |
| **2주차** | 4/15 \~ 4/21 | 12   |
| **3주차** | 4/22 \~ 4/28 | 27   |
| **4주차** | 4/29 \~ 5/5  | 15   |
| **5주차** | 5/6 \~ 5/12  | 0    |
| **6주차** | 5/13 \~ 5/19  | 10    |
| **7주차** | 5/20 \~ 5/26  | 0    |
| **8주차** | 5/27 \~ 6/2  | 0    |
| **9주차** | 6/3 \~ 6/9  | 0    |
| **10주차** | 6/10 \~ 6/16  | 14    |

![화면 캡처 2025-06-16 021704](https://github.com/user-attachments/assets/ffbabb07-1241-4c36-9400-cd6fe83b835c)

![스크린샷 2025-06-16 021620](https://github.com/user-attachments/assets/dc65be94-c311-45a9-aa88-a090840b7c74)

---

## 🎮 4. 개발에 사용된 기술 및 내용
- **사용된 기술**
  </br> Android Studio (Java), </br> Choreographer를 활용한 프레임 업데이트, </br> Sprite 기반 애니메이션 처리, </br> 오브젝트 풀링(Object Recycling)을 통한 메모리 최적화, </br> Joystick 입력 처리, </br> 충돌 판정(Collision Detection), </br> HorzScrollBackground, </br> Bitmap Pool을 활용한 이미지 리소스 재사용 및 메모리 최적화, </br> Scene 기반 Game Framework를 활용한 상태 전환 및 관리
  
- **참고한 것들**
  </br> GitHub에 공개된 학습용 안드로이드 게임 프로젝트, </br> ChatGPT를 통한 코드 구조 설계 및 디버깅 아이디어 조언
  
- **수업내용에서 차용한 것**
  </br> 드래곤플라이트의 EnemyGenerator, </br> 쿠키런의 Game Framework, HorzScrolling, </br> CollisionCheck, </br> AnimSprite, </br> Joystick 
- **직접 개발한 것**
  </br> 캐릭터 선택 시스템과 조작 UI 구성, </br> 적기 출현 및 제거 로직, 점수 시스템. </br> 체력, 점수 등의 인게임 UI 구성, </br> 플레이어 비행기의 상하좌우 이동 및 자동 총알 발사 구현, </br> 캐릭터별 속도, 공격력, 체력 등 밸런싱 조정
  

## 🎮 5. 아쉬웠던 점, 보충할 것, 해결하지 못 한 문제
- **하고 싶었지만 못 한 것들**
  </br> 베지어 곡선을 활용해 곡선형 궤적을 그리는 미사일을 구현하고 싶었지만, 다른 기능들에 더 집중하다 보니 끝내 구현하지 못해 아쉬움이 남습니다. 또한 캐릭터 애니메이션을 위해 다양한 스프라이트를 제작했음에도, 실제 인게임에서는 일부만 사용하게 되어 준비한 리소스를 충분히 활용하지 못한 점도 아쉬웠습니다.
 
- **(앱을 스토어에 판다면) 팔기 위해 보충할 것들**
</br> 몬스터의 패턴이 비교적 단순하게 구성되어 있어, 플레이의 긴장감이나 재미 요소가 다소 부족하다고 느꼈습니다. 따라서 몬스터의 움직임과 공격 방식에 더 다양한 패턴을 추가하고, 특정 구간마다 보스 몬스터와 같은 이벤트성 전투를 도입하면 게임의 완성도와 몰입감을 높일 수 있을 것이라 생각합니다.

- **결국 해결하지 못한 문제/버그**
</br> 수업 시간에 배운 Object Layer 개념을 제 프로젝트에 적용해보려 했지만, 구조적으로 완전히 이해하지 못해 끝내 적용하지 못했습니다. 그 결과, 쿠키런 프로젝트에서처럼 배경 위에 반투명하게 표시되는 결과 화면(UI)을 구현하지 못한 점이 아쉬웠습니다. 향후에는 Object Layer의 구조와 활용 방식에 대해 더 깊이 이해하고, UI 구성에 효과적으로 활용해보고 싶습니다.


## 🎮 6. 수업에 대한 내용
- **이번 수업에서 기대한 것, 얻은 것, 얻지 못한 것**
  </br> 1학년 시절 C언어 수업 시간에 잠깐 보여주셨던 쿠키런 프로젝트가 인상 깊어서, 저도 직접 만들어보고 싶다는 생각에 이 수업을 수강하게 되었습니다. 수업을 들으며 쿠키런뿐 아니라 드래곤 플라이트, 디펜스 게임, 리듬 게임 등 다양한 장르의 게임 제작 과정과 원리를 배울 수 있었고, 덕분에 게임 개발에 대한 시야가 훨씬 넓어졌습니다. 또한 수업에서 활용된 Git Commit 내역을 참고하면서 커밋의 중요성을 체감했고, 실제 제가 진행 중인 프로젝트에서도 세부적으로 커밋하는 습관을 갖게 되었습니다.

- **더 좋은 수업이 되기 위해 변화할 점**
  </br> 강의 초중반과 후반의 진도 속도 차이가 느껴졌습니다. 기말이 가까워질수록 시간적 여유는 줄어들고, 진도는 더 빠르게 나가다 보니 따라가기 다소 어려운 부분이 있었습니다. 따라서 이후 강의에서는 새로운 게임을 하나 줄이는 대신, 기존에 소개된 게임의 주요 기능을 천천히 리뷰하며 정리하는 시간이 있었으면 좋겠습니다. 이를 통해 핵심 개념을 다시 확인하고, 이해를 더 탄탄히 할 수 있을 것 같습니다.


---

## 🎮 7. 인게임 화면 

**타이틀 화면**  
![스크린샷 2025-06-16 032453](https://github.com/user-attachments/assets/7f7954f8-81ca-4d2f-b63b-86e0f2064ca2)

**캐릭터 선택 화면** 
![화면 캡처 2025-06-16 032513](https://github.com/user-attachments/assets/5cd906c9-52b4-4704-8317-99a118ea1543)

**인게임 화면** 
![스크린샷 2025-06-16 032529](https://github.com/user-attachments/assets/b23b62de-a873-487b-8193-63ddd854fcac)

**결과 화면** 
![스크린샷 2025-06-16 032552](https://github.com/user-attachments/assets/0dee5b6d-a028-4bbd-a3e3-6a32b3571506)











