## 길냥이를 부탁해 

### 프로젝트 개요 
'길냥이를 부탁헤'는 다음카카오에서 출시한 적이 있던 서비스를 모티브로 제작한
우리 지역 길냥이들과 사람이 공존하는 건강한 지역사회를 위한 프로젝트입니다.
해당 웹사이트의 기능이였던 커뮤니티 게시판 기능과 함께 해당 커뮤니티의 문제점이였던 사용자별 정보의 권한 
해결점을 중점으로 제작하였습니다. 

### 프로젝트 목표
- '길냥이를 부탁해'와 같은 커뮤니티 게시판 서비스에 사용자 권한 제한 로직을 통한 문제점 보완
- 객체 지향 디자인 패턴을 적용한 올바른 코드 작성
- 문서화와 TDD를 이용한 코드 작성에 우선순위 
- CI/CD를 통한 자동화를 이용하여 협업이 쉬운 프로젝트 구현 


### 프로젝트 중점 사항
- 버전 관리
- 서버의 확장성 
- 의존적이지 않은 코드 작성
- Redis Cache 이용한 사용자 권한 제어 기능 구현 
- Jenkins 이용 CI/CD 환경 구축 

### 기능 명세서 

### Github PR 방식 
#### 브랜치 전략
- Main : 배포했거나 곧 배포할 코드 관리합니다. 
- Develop : 배포할 것을 개발하는 코드를 관리합니다. 배포할 시에 Master로 merge 합니다. 
- Feature : 기능 개발을 진행할 때 사용합니다. 기능을 완성할 때까지 유지하고 완성 시 Develop 브랜치로 merge 합니다. 
- Release : 배포를 준비하는 브랜치로 배포를 필요한 메타데이터를 준비합니다. 
- Hotfix :  배포한 버전에서 발생한 버그를 수정하는 브랜치입니다.  

#### Commit 메세지 규칙 
FIX - 보통 올바르지 않은 동작을 고친 경우에 사용합니다.
ADD - 코드나 테스트, 예제, 문서 등의 추가가 있을 때 사용합니다
REMOVE - 코드의 삭제가 있을 때 사용
REFACTOR - 전면 수정이 있을 때 사용합니다.
UPDATE - 원래도 정상적으로 동작하고 있었지만, 수정, 추가, 보완을 한다는 개념입니다. 코드보다는 주로 문서나 리소스, 라이브러리등에 사용합니다
IMPROVE - 향상이 있을 때 사용합니다. 호환성, 테스트 커버리지, 성능, 검증 기능, 접근성 등 다양한 것들이 목적
MAKE - 주로 기존 동작의 변경을 명시합니다.
REVISE - 문서의 개정이 있을 때 주로 사용합니다.
CORRECT - 주로 문법의 오류나 타입의 변경, 이름 변경 등에 사용합니다.
MOVE - 코드의 이동이 있을 때 사용합니다.
RENAME - 이름 변경이 있을 때 사용합니다.
VERIFY - 검증 코드를 넣을 때 주로 사용합니다.
SET - 변수 값을 변경하는 등의 작은 수정에 주로 사용합니다.


[다음카카오-길냥이를 부탁해 참고자료] 
- http://www.itdaily.kr/news/articleView.html?idxno=57924
- http://www.ekoreanews.co.kr/news/articleView.html?idxno=7476
