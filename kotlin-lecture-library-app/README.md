### 키워드 리스트
- elvis operator
- backing field / backing properties
- data class (equals와 hashCode 자동 재정의)
- platform type
- trailing comma
- it (람다에서 파라미터를 명시적으로 적어주지 않아도 it으로 사용 가능)
- 확장함수 (CrudRepositoryExtentions.kt - findByIdOrNull 등)


### Kotlin & JPA항 유의사항
- setter
  - 프로퍼티를 사용하면 열리기 때문에 public이 아닌 private setter를 만들 수 있다.
  - 하지만 이렇게 되면 한 프로퍼티당 여러 줄의 코드가 생성되기 때문에 효율적이지 않다.
  - 팀원 모두가 setter를 사용하지 않는 것으로, 컨벤션을 맞추면 해결할 수 있다.
- 생성자 안의 프로퍼티 <-> 클래스 body 안의 프로퍼티
  - 생성자와 클래스 내부 둘 다 프로퍼티를 선언할 수 있다.
  - 생성자 안에 있으면 생성할 때 프로퍼티 지정하고, 클래스 내부에 있으면 객체 생성 후 지정할 수 있다.
  - 이 기준을 팀원끼리 명확히 지킨다면, 생성자와 클래스 body에 따로따로 넣고 사용할 수 있다. 
- JPA와 data class
  - Entity는 data class를 피하는 것이 좋다
    - equals, hashCode, toString은 entity와 어울리지 않는다.
    - user <-> userLoanHistory 연관관계시
      - user의 equals는 userLoanHistory 호출
      - userLoanHistory의 equals는 user 호출
- 생성자 추적
  - entity가 생성이 되는 로직을 찾고 싶으면 "constructor" 지시어를 명시적으로 작성하고 추적하면 생성이 되는 로직만 추적 가능