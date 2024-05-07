package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //String이 그대로 반환 (restAPI 만들때 핵심적인 controller), HTTP 메시지 바디에 바로 입력
// @Controller 반환값이 String이면 뷰 이름으로 인식 -> 뷰를 찾고 뷰가 렌더링
@Slf4j
public class LogTestController {

    //private final Logger log = LoggerFactory.getLogger(getClass()); == slf4j

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "spring";

        System.out.println("name = " + name); // 다 출력된다.

        // 어떤 상태의 level
        log.trace("trace log = {}", name); // 다 볼것이다.
        log.debug("debug log = {}", name); // 개발 정보 (개발 단계)
        log.info(" info log = {}" , name); // 비즈니스 정보 (운영 서버)
        log.warn(" warn log = {}", name); // 경고
        log.error("error log = {}", name); // error

        return "ok";
    }
}
