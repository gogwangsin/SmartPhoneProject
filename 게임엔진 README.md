# ✈️ 게임 엔진 프로젝트
---
## 🎮 게임 컨셉
- **귀여운 그래픽의 액션 RPG**
- **몬스터와 보스 몬스터를 처치하는 것이 목표인 전투 중심 게임**

---

## 📝 기획 내용
- **RPG 방식의 캐릭터 조작**으로 필드 몬스터 탐색 및 전투
- **Behavior Tree**를 활용한 **지능형 AI 몬스터** 구현
- **카메라 쉐이크 및 Animation Notify**를 통한 타격감 강화
- **낮/밤 조명 변화 및 머티리얼** 적용으로 **풍부한 시각적 분위기** 제공
- **Cut Scene 연출**을 통해 **보스전 진입 시 몰입도 향상**

---

## RPG 기반 컨트롤 ##
<img width="767" alt="기획" src="https://github.com/user-attachments/assets/7b5d3925-e85a-49eb-b4ea-b54d1bf336e1" />

## 몬스터 AI와 스킬 이펙트 ##
<img width="810" alt="화면 캡처 2025-06-20 145227" src="https://github.com/user-attachments/assets/88ed07a0-077b-4298-89c1-20f87c3b18dc" />

## 밤 라이트 + 바다 머티리얼 ## 
<img width="808" alt="화면 캡처 2025-06-20 145608" src="https://github.com/user-attachments/assets/db77f381-02c0-4201-b3fe-8a05779f6ae2" />

## 보스전 Cut Scene ##
<img width="689" alt="기획2" src="https://github.com/user-attachments/assets/371e254d-de33-4654-9cde-bcff8e393149" />


---
_________________________________________________________________________________

캐릭터  애셋 ( 모델 + 애니메이션 데이터 )
https://fab.com/s/9fb3e003c4a5

맵 애셋 ( 물 머티리얼, 움직이는 나무, 잔디 삭제 후 순수 그래픽 모델 사용 )
https://fab.com/s/052ea194ad68

몬스터 애셋 ( 모델 + 애니메이션 데이터 사용 )
https://fab.com/s/2bc25d57c627

UI 애셋 ( 폰트 + 텍스쳐 사용 ) 
https://fab.com/s/137693f82b50

체력 회복 ( 모델 + 나이아가라 이펙트 사용 )
https://www.fab.com/listings/166d8257-d5af-4b8d-bd6c-1ea6d761b07e

스킬 VFX ( 나이아가라 이펙트 사용 )
https://www.fab.com/listings/9baf4a11-d63a-4d9c-8ee1-bfedfd3eb696
_________________________________________________________________________________

---

## 블루프린트 구조, 직접 구현한 기능, 기술적 어려움과 해결 방법 ## 

</br>

---
1. 스킬 노티파이
2. 컷씬 Cut Scene + 직접 통신
3. 낮밤 NPC + 라이팅, 다이나믹 머티리얼
4. 카메라쉐이크
5. BGM
6. AI + Behavior Tree + Montage Notify

---
**스킬 노티파이**
</br>

<img width="653" alt="스킬" src="https://github.com/user-attachments/assets/6a98a4aa-b617-4c6a-8045-b8a529375b23" />
<img width="718" alt="스킬2" src="https://github.com/user-attachments/assets/07c418fe-4526-4073-99bb-863664f0e138" />
<img width="752" alt="스킬3" src="https://github.com/user-attachments/assets/105c335e-06df-4070-8341-e65aafa5cb49" />


---
**컷씬 Cut Scene + 직접 통신**
</br>
<img width="773" alt="컷씬1" src="https://github.com/user-attachments/assets/31a68436-180c-451f-9f28-661b565bfeaf" />
<img width="1118" alt="컷씬2" src="https://github.com/user-attachments/assets/3aac8cc5-860b-4349-b73e-0cc4be9a70d9" />
<img width="934" alt="cut3" src="https://github.com/user-attachments/assets/bd144982-cf7a-42c3-8750-9a593d28e936" />


---
**낮밤 NPC + 라이팅, 다이나믹 머티리얼**
<img width="842" alt="NPC1" src="https://github.com/user-attachments/assets/a35604b5-51c1-41cf-a1f9-6dcf0c0d834a" />
<img width="823" alt="NPC5" src="https://github.com/user-attachments/assets/9dba98ad-7c56-4a23-ad5a-8f1d3c5158ed" />
<img width="816" alt="NPC2" src="https://github.com/user-attachments/assets/21953037-b568-4a55-b7ac-364f3605bc81" />
</br>
**라이팅 + 다이나믹 머티리얼**
</br>
<img width="623" alt="NPC3" src="https://github.com/user-attachments/assets/dcbd8a30-f157-469f-a9ae-f4a5afd3e110" />
<img width="624" alt="NPC4" src="https://github.com/user-attachments/assets/67770c4c-a75d-4952-a7b7-d6f140b6f792" />


</br>

---
**카메라쉐이크**
</br>
<img width="615" alt="카메라" src="https://github.com/user-attachments/assets/017fe3dc-516e-4740-9133-8b07f88193e9" />
<img width="661" alt="카메라2" src="https://github.com/user-attachments/assets/772270fe-b068-4c6b-8ddc-e5da533b2d99" />

---
**BGM**
</br>
<img width="761" alt="BGM1" src="https://github.com/user-attachments/assets/661edc9f-51df-44b7-877d-8ea848a951f6" />
<img width="839" alt="BGM2" src="https://github.com/user-attachments/assets/6dafbb50-e5c1-4c9c-9d70-3f500c6f5b67" />
<img width="956" alt="BGM3" src="https://github.com/user-attachments/assets/6ecb5d8d-83e6-4312-a688-efef00519058" />
<img width="874" alt="Sound2" src="https://github.com/user-attachments/assets/81abac06-78bd-4ed7-9999-a7ab1a7ff59c" />


---
**AI + Behavior Tree + Montage Notify**
</br>

**첫 번째**
</br>
<img width="629" alt="a_1" src="https://github.com/user-attachments/assets/2f6aab11-b3d4-46a1-9b5b-fd24d01f2d5c" />
<img width="1080" alt="a_2" src="https://github.com/user-attachments/assets/3768c325-cc94-4814-a441-f429c3758720" />
</br>
**두 번째**
</br>
<img width="886" alt="a" src="https://github.com/user-attachments/assets/32cd5c49-2b02-4aa1-b678-c6502cdbfd3b" />
<img width="673" alt="a2" src="https://github.com/user-attachments/assets/db881194-ba79-4332-9fad-0700622c93ad" />
<img width="1110" alt="a22" src="https://github.com/user-attachments/assets/11730606-a251-409b-9924-8c42668c1e2d" />
</br>
**세 번째**
</br>
<img width="691" alt="a3" src="https://github.com/user-attachments/assets/e3763703-ca12-4858-a38b-a5352b43fc9e" />
<img width="716" alt="a4" src="https://github.com/user-attachments/assets/24afa1bd-f786-403e-ade8-ae664dda4074" />
</br>
**네 번째**
</br>
<img width="685" alt="a5" src="https://github.com/user-attachments/assets/10b06e0b-ba17-4530-904e-24ee94e7e75c" />
</br>
**다섯 번째**
</br>
<img width="791" alt="a6" src="https://github.com/user-attachments/assets/95b66f24-e757-4864-ba8d-1a7ad0c2664a" />
</br>
**여섯 번째**
</br>
<img width="569" alt="a7" src="https://github.com/user-attachments/assets/21f02c92-6a75-47e1-bcc9-1aee79e4d3fe" />
<img width="680" alt="a8" src="https://github.com/user-attachments/assets/6c7f55e1-8dca-4139-a639-ed7279e5d5d6" />

---

## 📝 평가표에 제시된 언리얼 기능 활용 여부
**앞서 설명한 부분**
- 블루프린트 클래스
- 레벨 블루프린트
- 애니메이션 몽타주
- 머티리얼 인스턴스
- 라이팅
- 직접 통신
- 행동 트리

**나머지 활용 기능**
- 액터 컴포넌트 ㅇ
- 컨스트럭션 스크립트 x
- 입력 시스템 ㅇ
- 게임 플레이 프레임워크 ㅇ
- 랜드스케이프 x
- 이벤트 디스패치 ㅇ
- 블루프린트 인터페이스 ㅇ
- UMG ㅇ
</br>

**액터 컴포넌트 + 이벤트 디스패치**
</br>
-> NPC가 밤으로 변경할 때 Spot Light한테 메시지를 전송해서 보스나 NPC에게 빛을 비추게 했습니다.
<img width="809" alt="a4" src="https://github.com/user-attachments/assets/9030249f-259b-49e1-bc78-b4ab3a4403ca" />
<img width="686" alt="액터1" src="https://github.com/user-attachments/assets/3a032e1f-c9b0-45fb-9e0c-f1cd24ccf73e" />
<img width="805" alt="108" src="https://github.com/user-attachments/assets/797dda80-ef9f-4f1c-9775-515d1c7c5c7d" />
<img width="1115" alt="d2" src="https://github.com/user-attachments/assets/50b6aaf9-84d7-40cc-a1e2-d3ba0a56c87e" />
<img width="1111" alt="a3" src="https://github.com/user-attachments/assets/286175b4-66ad-41ce-a58e-ea13808e0950" />
</br>

**RPG 입력 시스템 + 플레이어 콤보 Montage**
</br>
<img width="557" alt="화면 캡처 2025-06-20 173958" src="https://github.com/user-attachments/assets/1b4724fd-07d4-4db6-b427-8fcabb3c5fa7" />
<img width="728" alt="IN1" src="https://github.com/user-attachments/assets/1076a57b-c771-485d-894b-6b928a54e261" />
<img width="658" alt="IN2" src="https://github.com/user-attachments/assets/1dce810d-4e54-45cc-b2ef-cb3a303da13b" />
<img width="656" alt="IN3" src="https://github.com/user-attachments/assets/1bb0384c-947a-4252-bf66-d1380392e50a" />
</br>

**게임 플레이 프레임워크**
</br>
<img width="911" alt="프레임워크1" src="https://github.com/user-attachments/assets/06563868-9e42-4cab-8c96-f034f8c7d41b" />
<img width="662" alt="프레임워크2" src="https://github.com/user-attachments/assets/64427958-ec21-4b8f-bf41-d87f8347cc10" />
<img width="638" alt="프레임워크3" src="https://github.com/user-attachments/assets/0ed6b115-a286-4a48-81f9-a87c7d44aa3e" />
<img width="512" alt="프레임워크4" src="https://github.com/user-attachments/assets/affc85aa-7336-49fb-9491-a58d008ce25f" />
<img width="693" alt="프레임워크_5" src="https://github.com/user-attachments/assets/7a2c3faf-159f-49b8-9778-26d3671ebafa" />
<img width="741" alt="프레임워크6" src="https://github.com/user-attachments/assets/e3a11a35-94a5-405e-bff1-bbb3e9743f9d" />
</br>

**블루프린트 인터페이스**
</br>
-> Player와 충돌하면 Farming을 호출하면 Interface를 갖는 GS_Heart가 자기가 정의한 함수를 통해 회복 이펙트를 실행하고 사라진다. 
<img width="973" alt="화면 캡처 2025-06-20 191726" src="https://github.com/user-attachments/assets/12dfcbd9-845c-4b73-a026-df490dc7fdaa" />
<img width="708" alt="화면 캡처 2025-06-20 191751" src="https://github.com/user-attachments/assets/d6ca7f26-5338-4bc0-ab77-758b8c130584" />
<img width="920" alt="화면 캡처 2025-06-20 191813" src="https://github.com/user-attachments/assets/b0e5df26-ebe7-4743-b690-d98ee579a2a7" />
</br>

**UMG**
</br>
-> Player를 바인딩해서 체력 동기화
<img width="896" alt="UMG1" src="https://github.com/user-attachments/assets/73b7bc9a-9937-4d1e-aea2-c8074ead5456" />
<img width="585" alt="UMG2" src="https://github.com/user-attachments/assets/91e4cdf1-59e7-4c9d-89e1-d9070bc6eb54" />
</br>

---



















