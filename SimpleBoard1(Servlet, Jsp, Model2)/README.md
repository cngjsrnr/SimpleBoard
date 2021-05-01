## SimpleBoard1(Servlet,Jsp,Model2)

---

### 사용 기술

- servlet
- JSP
- JSTL
- EL
- MySql (8.0.21)
- Java 1.8

### 기능

- 유저 로그인 (select)
- 유저 회원가입 (insert)
- 유저 정보 수정 (update)
- 게시글 목록 불러오기 (select)
- 게시글 상세보기 (select)
- 게시글 작성 (insert)
- 게시글 수정 (update)
- 게시글 삭제 (delete)

    → 지금은 수정, 삭제를 insert, update로 하지만 나중엔 변경사항을 관리해야하기때문에 전부 insert만 한다고 함

### IDE

- Eclipse 2018-09

### Model2 패턴 (MVC)

- M(model): Dto, Service, Dao
- V(view): JSP
- C(controller): Servlet

SimpleBoard1은 위와같이 구성되어 작성되었다

- 프론트 컨트롤러 패턴(?)

![springmvcstructure](https://user-images.githubusercontent.com/37106432/116291278-5b7e3a00-a7cf-11eb-8232-eec573144b1c.jpg)

지금은 해당 이미지에서 Servlet이 Dispatcher Servlet, HandlerMapping, Controller, View Resolver의 역활을 전부다 하고 있다

여기에 여러 프레임워크들을 추가해가며 각 기능별로 객체를 나누어 관리하게 되는것임

참고:[https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:ptl:spring_mvc_architecture](https://www.egovframe.go.kr/wiki/doku.php?id=egovframework:rte2:ptl:spring_mvc_architecture)

### 배운점

- model2패턴은 url상에 index.jsp같이 직접적인 파일명이 나오면 model2패턴이 깨진것이라 했다

    그래서 main에 매핑 시켜주어 main?act=mvregist이런식으로 관리를 해줬다

- sendRedirect와 forward에 관해

    큰 차이점은 sendRedirect는 request를 유지하지 않은채 넘어가고 url이 변경된다

    forward는 request가 유지되고 url은 그대로지만 페이는 넘어가게 된다

    만약 게시글 목록을 불러오는 작업을 sendRedirect와 forward로 처리했을때 새로고침을 하게 될 경우를 생각해보자

    /main?act=getboardlist를 통해 controller에서 게시글 리스트를 받아와 세션에 넣어준뒤 /main?art=mvboradlist 으로 이동한다고 할 경우 

    1. sendRedirect:  /main?art=mvboradlist로 sendRedirect

        게시물 리스트가 뿌려지는 화면은 /main?art=mvboradlist이 될것이다 여기에서 새로고침을 하게되면 페이지는 여전히 mboardlist에 머물러 있을것이다 그렇기때문에 새로고침을 아무리해도 새로운 글을 불러오지 않는다

    2. forward:  /main?art=mvboradlist로 forward

        게시물 리스트가 뿌려지는 화면은 여전히 /main?act=getboardlist이 될것이다 여기에서 똑같이 새로고침을 하게되면 페이지는 매번 컨트롤러를 통해 DB에서 새로운 게시글 리스트를 받아와 글 목록이 매번 업데이트 되게 된다

    → 새로고침할때마다 새로운 목록을 가져오는게 필요한 경우 forward로 처리해야함

    반대로 생각하게 될경우 post를 통해 값을 받아와 DB에 삽입하는경우 새로고침할때마다 값이 들어가는 경우가 생길수도 있겠다

    → post는 무조건 sendRedirect해줘야겠다!

- Service, DAO의 싱글턴 패턴에 관해

    서비스나 DAO는 싱글톤으로 만듬
    Controller부분이 객체 하나만 만들어지고 쓰레드 추가해서 접속되기 때문에
    다른애들도 객체 한개만 만들어 질수 있게 싱글톤으로 만든다
    근데 사실 new해서 쓰레드 생길때마다 DAO, Service를 new해서 계속 만들어도된다고 한다

    차이점(한꺼번에 많은 유저들이 동시 접속 할때 차이가 생김)

    - 싱글톤	
    병행 처리 같은걸 생각안해도됨
    그러나 동시 접속되는 상황에선 결국 내부적으로 순차적으로 처리가 되서 new하는거보다 느림
    메모리적 이득
    - 새로운 객체 생성(new)
    병행 처리 같은걸 생각해야됨
    (예를 들어 디비에 동시에 3명이 접속해서 같은 기차좌석을 3명이 예매하면 어떻게 할것인가)
    → DB등록할때 기차좌석을 Primary Key로 두면 중복된 키가 발생하면 SQLException이 발생함
    → 이런 상황이 자주 발생하는 서비스일경우 싱글톤인게 더 안전하다함
    동시접속이 발생된다면 병렬적으로 처리가 되기때문에 싱글톤 방식에 비해 빠름
    메모리 싱글톤에비해 더 듬
    
    

### 문제점

---

유저가 회원정보를 변경할경우 게시글의 유저 이름이 안바낌 (유저의 이름은 바꼇는데 게시글 유저이름은 안바껴서 자기글인데도 수정, 삭제 안됨)

- [x]  세션상의 유저 정보를 유저정보를 수정할때마다 업데이트 해줘야함 (해결)
- [x]  게시글 작성시 유저 id를 넣어두고 작성자를 유저테이블과 join해서 가져와야함 (해결)
- [x]  .jsp로 접근이 가능함(참고:[https://okky.kr/article/33603](https://okky.kr/article/33603)) (해결)
