# [JPA] 민감한 데이터 암호화 / 마스킹하기 (Attribute Converter, @Converter)

[블로그 글](https://itchipmunk.tistory.com/)과 함께 합니다.

`Spring Data JPA` 에서 '**Attribute Converter**' 활용을 실습합니다.  
JPA 속성 값과 데이터베이스 칼럼 사이의 표현을 정의합니다.  
데이터베이스에 그대로 저장하기 민감한 데이터를 암호화합니다.  
데이터베이스에서 읽을 때 개인정보를 마스킹합니다.  
위 요구사항에 맞게 비즈니스 로직 변경 없이 `AttributeConverter` 를 활용하는 법을 익힙니다.

## 도움되는 점

1. **Attribute Converter 학습** : JPA 속성 값과 데이터베이스 칼럼 값 사이 변환해봄
2. **JPA 영역만 테스트 해보기** : `@DataJpaTest`와 `@InjectMocks`, `@Mock` 으로 유닛 테스트와 통합 테스트 작성

## 프로젝트 환경

- **언어** : Java 17.0.11
- **프레임워크** : Spring Boot 3.3.1
- **빌드 도구** : Gradle 8.8
- **기타**
  - H2 Database Engine(Embedded)
  - Spring Data JPA
  - Lombok

## 프로젝트 실습 방법

### 1. IDE 사용

* IntelliJ IDE 에서 프로젝트 폴더 Open
* 루트 경로의 `./build.gradle` 에서 의존성 설치
* `./src/test` 폴더 우측 클릭 -> `"demo.test'에 있는 테스트' 실행`
* 테스트 결과 확인

### 2. Gradle 사용

```bash
$ ./gradlew test
$ open ./build/reports/tests/test/index.html
```

* 빌드 도구로 테스트를 완료해 HTML 파일로 결과를 확인
