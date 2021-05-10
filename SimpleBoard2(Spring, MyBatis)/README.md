## SimpleBoard2(Spring, MyBatis)

---

### 사용 기술

- JSP
- JSTL
- EL
- MySql (8.0.21)
- Java 1.8
- Spring 5.2.6.RELEASE
- MyBatis

### 기능

- SimpleBoard1과 동일

### IDE

- STS

### Model2 패턴 (MVC)

![springmvcstructure](https://user-images.githubusercontent.com/37106432/116291278-5b7e3a00-a7cf-11eb-8232-eec573144b1c.jpg)

- SimpleBoard1에서 Spring프레임워크를 추가함으로써 기존의 Servlet이 Dispatcher Servlet, HandlerMapping, Controller, View Resolver의 역활을 전부다 하고 있던게 쪼개짐
- Dispather Servlet, HandlerMapping, ViewResolver를 Spring에서 알아서 처리해줌
- 나는 Controller만 구현하면됨 (Controller, Service, Dao(Mapper))
- SimpleBoard1에서 Service, Dao를 싱글턴 패턴으로 생성하여 사용하던것들이 Spring에서는 DI, IOC 를 이용해 싱글턴 패턴으로 생성이되어 필요한 곳에 알아서 주입시켜줌
- 거기에 MyBatis를 활용하면서 Dao를 직접 생성할 필요가 없게 되고 SqlSession을 통해 Dao를 구현할 필요없이 사용하게됨


### 배운내용

- DI(Dependency Injection) 의존성 주입
    - 클래스에서 필요한 객체들을 new 또는 싱글턴 패턴 이런걸로 직접 받아오는게 아닌 객체들을 필요로 하는곳에 알아서 집어 넣어줌 (IOC를 통해 프레임워크가 알아서 해줌)
    - Spring에서는 DI방식 3가지
        1. XML
        2. Anotation + XML
        3. Anotation + Java
- IOC(Inversion of Control) 제어역행
    - Spring같은 프레임워크가 제어권을 가지는 것
    - IOC 기능 덕분에 DI를 Spring에서 알아서 주입시켜줄수 있음

- POJO(Plain Old Java Object)
    - 특정 프레임워크나 기술에 의존적이지 않은 자바객체
    - 특정 기술에 종속적이지 않음
    - (ex)웹에선 HttpServlet같은객체를 무조건 가지고 와야함
    - 내부적으로 필요한 것들을 스프링이 미리 생성해놓음
    - 개발자는 종속성을 고려하지않고 프로그램을 만들수 있음
- DI설정 Anotation
    - Dao: @Repository, controllor: @Controller, Service: @Sevice
    - @Autowired를 해놓으면 타입을 비교해서 동일한 타입을 주입시켜줌
        - 만약 동일한 타입이 여러개일 경우 @Qualifier("")를 이용
        - ex)@Repository("rep") 인 Dao를 삽입할려면 @Qualifier("rep")를 @Autowired밑에 적어주면됌
        - 만약 @Repository에 value를 안적었다면 해당 이름으로 매칭됨 (앞에 대문자를 소문자로 바꿈)
    - ComponentScan을 통해 Anotation을 확인하여 필요한 부분에 DI해줌
- Spring 세팅
    - root-context.xm에는 web과 관련 없는 애들 넣어줌 (Service, Dao 이런애들은 web뿐만 아니라 키오스크에 해당기능 넣는다면 그대로 쓸수 있음)
    - servlet-context.xml에는 web관련 (Controller)
- Filter, Interceptor차이
    - Filter는 Client와 Dispather Servlet사이에 존재
    - Interceptor는 Dispather Servlet과 Controller사이에 존재

- Spring에서의 Forward, Redirect
    - 스프링은 기본적으로 Controller에서 모델과 뷰의 이름을 받아 Veiw Resolver를 거쳐 뷰의 주소를 얻어 해당 주소의 뷰를 얻어 Forward시켜줌
    - 리다이렉트는 "redirect:index" 이런식으로 View이름 앞에 redirect:을 붙혀줌
    - Controller에서 뷰를 바로 뿌리는게 아닌 다른 컨트롤러로 접근하고싶다면 어떻게 해야할까
        - ex) 글 작성(Post방식)이 이루어 질 경우 다시 글목록으로 보내준다 할경우 return "redirect:/list"이런식으로 해주면  @GetMapping("/list")어노테이션이 달린 Controller로 가게됨 (forward방식으로 하고싶으면 "forward:list"로 해주면됨)
    - 만약 redirect로 값을 뿌리고 싶다면 어떻게 해야할까 (redirect는 request가 새로 생성됨)
        1. SimpleBoard1에서처럼 세션에 값을 저장해도 된다
        2. RedirectAttributes.addAttribute() 를 이용하면 Redirect(GET방식)의 쿼리스트링에 값이 따라가게된다
            - 만약 1번만 뿌려지는 값을 원한다면 RedirectAttributes.addFlashAttribute()를 쓰면 된다
- Spring에서의 Model
    - 마치 SimpleBoard1에서의 Request와 유사하게 사용되는 모습을 볼수 있었다
    - 하지만 실제로 보면 Map형태의 자료구조이다 Model에 원하는 값들을 담으면 내부적으로 넘어가면서 Request에 담아주는(?) 형태이지 않은가 하고 생각한다

- MyBatis
    - MyBatis를 독자적으로 쓰는방식과 Spring에 붙혀서 사용하는 MyBatis-Spring이 존재
    - 독자적 MyBatis

        src/main/resources에 만드는것들

        1. product.xml이런거 만들어서 mapper만들고 namespace지정 그리고 select, insert이런 필요한 sql문 작성후 각 sql문에 id할당
        2. mybatis-config.xml을 만들어 디비 연결정보(연결할때 properties사용해도됨)와 typeAliase를 지정후 mappers로 sql문이 담긴 매퍼 연결해줌
        3. 그리고 class한개(ex. SqlMapConfig) 만들어서 
        SqlSessionFactoryBuild().build("mybatis정보 경로")이렇게 해서 SqlSessionFactory만듬 
        (mybatis정보 경로는 mybatis-config.xml을 넣어줌) 
        그리고 만들어진 factory를 이용해 DAO에서 SqlSession을 요청하면 factory.openSession()을 통해 SqlSession넘겨줌
        4. 그럼 DAO에서 받은 SqlSession을 이용해 namespace, id를 통해 매퍼 찾아서 db실행해줌
        (insert같은거 하고나면 commit꼭 해줘야함(autocommit안됨))
    - MyBatis-Spring
        1. 위랑 똑같이 src/main/resources에 product.xml(위랑 똑같음), mybatis-conig.xml(여기선 typeAliase만 적음, DB정보는 다른곳에서함)만듬
        2. webapp/META-INF(만들어야됨)/context.xml에 DB정보 넣음
        3. root-context.xml에 MyBatis-Spring 설정(connection pool도 해줌)
        1.  jndi를 이용해 webapp/META-INF/context.xml에 있는 DB정보로 DataSource를 bean으로 등록
        2.  SqlSessionFactoryBean에 DataSource/ configLocation(mybatis-conig.xml경로)/ mapperLocations(product.xml) 넣어서 bean 등록
        3.  SqlSessionTemplate에 SqlSessionFactoryBean 넣어서 SqlSession bean으로 등록
        4. DAO는 인터페이스만 남기고 Impl지움
        5. Service에서 SqlSession 변수 @Autowired해서 의존성 주입받음
        6. sqlSession.getMapper(ProductDAO.class).insert(product); 이런식으로 사용
        주의점: mybatis-spring에서는 sqlSession에 따로 namespace나 id를 주지 않기 때문에
        ProductDAO의 패키지와 메서드명을 product.xml의 namespace, id 와 같게 해줘야한다

- DBCP(Data Base Connection Pooling)
    - 매번 DB Connection을 생성하고 닫고 생성하고 닫고 하는게 아니라
    pool에 DB Connection을 여러개 연결 시켜놓고 필요할때마다 가져다 썼다가 다쓰면 반납함
    - DB를 연결하고 close하는 시간이 많이듬 그래서 미리 연결해놓고 쓰는거임
    - 대신 한번에 DB에 연결할수 있는 Connection이 제한되어있음
    - 동시 연결 가능 Connection을 늘릴수롤 비용 발생
    - 스프링에서 쓸려면 JNDI필요

- @RequestParam 과 @ModelAttribute에 대해
    - @RequestParam
        - @RequestParam(value = "pprice",defaultValue = "-1" , required=false)이런식으로 사용
        - 보통 int, String 이런 원시타입들을 받아오는데 사용
        ~~DTO나 map도 받아올수 있지만 DTO의 필드중에 int인 타입이 있다고 할때 공백 ""이 넘어오게되면 NumberFomatingException이 발생함~~
        - map은 받을수 있지만 DTO는 못받음
        
    - @ModelAttribut
        - DTO 같은 오브젝트를 받아오는데 좀 더 좋음
        - DTO의 필드중에 int인 타입이 있다고 할때 공백 ""이 넘어오게되면 알아서 0으로 바꿔줌
        - BindingResult 를 통해 오류가 발생했는지 확인 할 수 있음

여기선 안썻지만 더 공부해야할 사항

- AOP
- Interceptor
- Filter를 이용한 request, response재정의
