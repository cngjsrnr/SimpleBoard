## SimpleBoard3(REST API)

---

### 사용 기술

- JSP
- JSTL
- EL
- MySql (8.0.21)
- Java 1.8
- Spring 5.2.6.RELEASE
- MyBatis
- REST API

### 기능

- SimpleBoard1과 동일
- +추가 : 댓글기능 (REST API이용 Get/POST/PUT/DELETE)

### IDE

- STS

### REST API (Representational State Transfer)

- 하나의 URI는 하나의 고유한 리소스를 대표하도록 설계된다
- HTTP URI를 통해 제어할 자원을 명시하고 HTTP Method(GET,POST,PUT,DELETE)를 통해 해당 자원을 제어하는 명령을 내리는 방식의 아키텍처 
(시멘틱 URL) (HTML form에서는 put,delete지원안함 그래서 비동기 통신 필요)
- 딱 정해진 표준이없음 (암묵적인 표준만 있음)
    - 하이픈(-)은 쓰지만 언더바(_)는 쓰지않는다
    - 특별한 경우를 제외하고는 대문자 사용은 않는다
    - URI마지막에 /를 쓰지않는다
    - /로 계층관계를 나타낸다
    - 확장자가 포함된 파일 이름을 직접 포함시키지 않는다
    - URI는 명사를 사용한다
- Rest api에서는 더이상 서버에서 view를 관리하지 않음 
(그렇기때문에 Rest api로만 구현한다면 mvc패턴이 유지되지않음)

### 배운내용

- 관련 Annotation
    - @RestController	
    Controller내부 모두가 @ResponseBody가 붙은거랑 같음
    (@RestController는 return값이 넘겨야하는 데이터로 인식됨)
    (비동기통신하는 애들끼리 모아서 @RestController로 적는게 권장됨)
    - @ResponseBody	
    이게 있으면 return해주는 값이 뷰의 이름이 아닌 넘겨야하는 데이터로써 인식함
    - @PathVariable	
    URL이 /blog/cngjsrnr/3222 일때 cngjsrnr은 블로그이름, 3222는 글번호인데 이걸 파라미터로 추출
    - @CrossOrigin	
    Ajax의 크로스 도메인 문제를 해결
    - @RequestBody	
    Request로 넘어온 JSON파일을 원하는 타입으로 바인딩

- URI, URL
    - URL: 도메인 네임주소를 구분
    - URI: 조금더 상위의 주소 자원을 관리하는 주소
    (파일 시스템의 C://Users/~~이런거도 URI임)
    - URI: URL에 포함된 일부분을 URI라고 부르기도함(쿼리스트링 앞부분)
    - URI두가지 존재

- jacson-databind
    - Controller에서 Rest방식으로 값을 넘겨줄때 리턴값을 따로 JSON으로 변환시키지 않아도 알아서 변환해서 넘어감
    (DTO그대로 리턴해도 JSON으로 변환시켜줌)
