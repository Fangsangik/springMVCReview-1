package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response)
        throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);
        response.getWriter().write("ok");
    }

    @ResponseBody //view 조회를 무시하고 HttpMessageBody에 직접 해당 내용 입력
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {
        log.info("username = {}, age = {}", memberName, memberAge);
        return "ok";
    }

    // HTTP 파라미터 이름이 변수 이름과 동일 하면 생략 가능
    @ResponseBody //view 조회를 무시하고 HttpMessageBody에 직접 해당 내용 입력
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //String, int, Integer 단순 타입이라면 @RequestParam 또한 생략 가능
    @ResponseBody //view 조회를 무시하고 HttpMessageBody에 직접 해당 내용 입력
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    //필수 파라미터
    //@RequestParam(required = true) -> url에 필수로 입력
    //@RequestParam(required = false) -> url에 없어도 됨
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired (
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age //기본형에 null 넣을 수 X
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age //값이 안들어오면 -1로 설정 된다.
    ) {
        log.info("username = {}, age = {}", username, age);
        return "ok";
    }


    //파라미터 값 모두 다 받고 싶다면 Map
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap (
            @RequestParam Map<String, Object> paramMap
            ) {
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*
    modelAttribute => 아래 과정을 생략
    HelloData data = new HelloData();
    data.setUsername(username);
    data.setAge(age)
     */

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){
        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
