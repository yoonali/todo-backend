# peep-todo-backend

<img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white"> 


### **📝 Git 커밋 전략 (Git Commit Strategy)**

본 문서는 프로젝트에서 **일관된 Git 커밋 메시지 작성 규칙**을 정의하여 협업의 효율성을 높이고, 변경 이력을 명확하게 추적할 수 있도록 돕습니다.

---

## **✅ 1. 커밋 메시지 작성 규칙**
커밋 메시지는 다음과 같은 형식을 따릅니다:

```
<타입>(#이슈번호): <변경 사항 요약>
```

### **🔹 커밋 타입 (Commit Types)**
| 타입 (`<type>`) | 설명 |
|------------|------------|
| `feat`     | 새로운 기능 추가 |
| `fix`      | 버그 수정 |
| `refactor` | 코드 리팩토링 (기능 변경 없음) |
| `chore`    | 패키지 업데이트, 빌드 설정 변경 등 |
| `docs`     | 문서 추가 및 수정 (`README.md` 포함) |
| `test`     | 테스트 코드 추가 및 수정 |
| `style`    | 코드 스타일 변경 (포맷팅, 띄어쓰기 등, 기능 변경 없음) |

---

### **🔹 커밋 메시지 예시**
```bash
feat: #30일 후 소프트 삭제된 사용자 자동 삭제 기능 추가
```
✅ **변경 사항 요약 (`#변경사항요약`)**를 포함하여 관련된 작업을 알아보기 쉽게 합니다.  
---

## **✅ 2. 브랜치 전략**
팀 내 협업을 원활하게 하기 위해 **브랜치 전략**을 설정합니다.
dev-(자기이름 약자) 
자기 이름 약자 : ex) yoona -> yn
---

## **✅ 3. PR 및 머지 (Pull Request & Merge)**
- 모든 변경 사항은 **PR(Pull Request)**을 통해 병합합니다.
- PR 제목은 `[#브랜치명] 커밋 타입: 변경 사항 요약` 형식을 사용합니다.
- PR 예시:
  ```
  [#브랜치명] feat: 30일 후 소프트 삭제된 사용자 자동 삭제 기능 추가
  ```
- **`main` 또는 `develop` 브랜치로 머지(Merge) 진행**합니다.

---

## **🚀 4. 협업 규칙**
1. `main` 브랜치는 **직접 푸시 금지**, 반드시 PR을 통해 머지.
2. 하나의 PR에는 **하나의 기능/수정만 포함** (작은 단위로 작업).
3. 코드 리뷰를 거쳐 승인된 후 머지.

---


