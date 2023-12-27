# 프로젝트 정보
쇼핑몰을 참고해서 만든 온라인 상품 판매 사이트입니다.

# 개발 방식
## Git Flow

![image](https://github.com/f-lab-edu/OnlineMarket/assets/75728196/3b7c7354-3b7c-4ad4-b8c1-44872c0fa292)

✅ master : 릴리스 버전을 관리하는 메인 브랜치  
✅ develop : 개발이 진행되는 통합 브랜치   
✅ feature : 새로운 기능을 개발하는 브랜치   
✅ release : 다가오는 릴리스를 준비하는 브랜치  
✅ hotfix : 실제 프로덕션에서 발생한 버그를 수정하는 브랜치  
✅ issue/* : issue를 나누어서 개발에 적용하는 브랜치

Reference : [우아한 형제들 기술블로그 : gitFlow](https://techblog.woowahan.com/2553/) 

## Rule & Convention
### Git Commit Rule
- Format
  - Type
      * feat: 새로운 기능 추가
      * fix: 버그 수정
      * build: 빌드 관련 파일 수정
      * ci: CI관련 설정 수정
      * docs: 문서(문서 추가, 수정, 삭제)
      * style: 스타일(코드 형식, 세미콜론 추가: 비즈니스 로직에 변경 없는 경우)
      * refactor: 코드 리팩토링
      * test: 테스트(테스트 코드 추가, 수정, 삭제: 비즈니스 로직에 변경 없는 경우)
      * chore: 기타 변경사항(빌드 스크립트 수정 등)
  - Subject
      * 제목은 50자를 넘기지 않고, 마침표를 붙이지 않습니다.
      * 제목에 커밋 타입을 함께 작성합니다.
      * 과거 시제를 사용하지 않고 명령조로 작성합니다.
      * 제목과 본문은 한 줄 띄워 분리합니다.
      * 이슈에 관련된 내용이라면 이슈 번호를 붙힙니다.
  - Body
      * 선택 사항이므로 모든 커밋에 작성할 필요는 없습니다.
      * 한 줄에 72자를 넘기면 안 됩니다.
      * 어떻게(how)보다 무엇(what), 왜(why)에 집중하여 내용을 작성합니다.
      * 설명뿐만 아니라 커밋의 이유를 작성할 때도 작성합니다.
   
  ### Code Convention

  [Naver Code Convention](https://naver.github.io/hackday-conventions-java/#_intellij)
  

  ### PR Rules
  - PR 메시지는 다음을 따른다.
    - 제목 : [#이슈번호] 제목
    - 내용 : 왜, 무엇이 바뀌었는지 이해하기 쉽게 적는다.
  - 1개의 커밋에는 1개의 행위만, 1개의 PR에는 1개의 작업만 담을 것을 권장한다.
  - PR은 반드시 1명 이상의 리뷰어로부터 approved 를 받아야 merge 할 수 있다.
 
  
