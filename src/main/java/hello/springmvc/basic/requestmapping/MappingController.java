package hello.springmvc.basic.requestmapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {
    @RequestMapping("/hello-basic") //배열형식으로도 지원
    public String helloBasic(){
        log.info("helloBasic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1() {
        log.info("mappingGetV1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mappingGetV2");
        return "ok";
    }

    /**
     *
     * pathVariable 사용
     * 변수명 같다면 생략
     * @@PathVariable("userId") String data => @PathVariable userId
     */
    @GetMapping("/mapping/{userId}") //url 자체에 값이 들어있음
    public String mappingPath(@PathVariable("userId") String data){
        log.info("mappingPath userId = {}", data);
        return "ok";
    }

//    변수명에 맞춰 사용
//    @GetMapping("/mapping/{userId}") //url 자체에 값이 들어있음
//    public String mappingPath1(@PathVariable("userId") String userId){
//        log.info("mappingPath userId = {}", userId);
//        return "ok";
//    }

    /**
     * PathVariable 다중 사용
     */

    @GetMapping("/mapping/users/{userId}/orders/{orderId}")
    public String mappingPath1(@PathVariable String userId, @PathVariable Long oderId){
        log.info("mappingPath userId ={}, oderId={}", userId, oderId);
        return "ok";
    }

    /**
     * 파라미터로 추가 매핑
     * params="mode",
     * params="!mode"
     * params="mode=debug"
     * params="mode!=debug" (! = )
     * params = {"mode=debug","data=good"}
     */

    //파라미터에 mode = debug라는 것이 있어야 호출
    @GetMapping(value = "/mapping-param", params = "mode = debug")
    public String mappingParam2(){
        log.info("mappingParam");
        return "ok";
    }

    /**
     * 특정 헤더로 추가 매핑
     * headers="mode",
     * headers="!mode"
     * headers="mode=debug"
     * headers="mode!=debug" (! = )
     */
    //postman에서 header값 setting
    @GetMapping(value = "/mapping-header", headers = "mode=debug")
    public String mappingHeader() {
        log.info("mappingHeader");
        return "ok";
    }

    /**
     * Content-Type 헤더 기반 추가 매핑 Media Type
     * consumes="application/json"
     * consumes="!application/json"
     * consumes="application/*"
     * consumes="*\/*"
     * MediaType.APPLICATION_JSON_VALUE
     */

    //ContentType에 따라 분리 할 수 있다.
    //Body -> JSON으로 변경해서 사용
    //요청의 정보를 소비 하는 입장 - consumes
    @PostMapping(value = "/mapping-consume", consumes = "application/json")
    public String mappingConsumes() {
        log.info("mappingConsumes");
        return "ok";
    }

    /**
     * Accept 헤더 기반 Media Type
     * produces = "text/html"
     * produces = "!text/html"
     * produces = "text/*"
     * produces = "*\/*"
     */
    // 생산해 내는 controller type
    // Accept와 관련된 type들만 생성
    @PostMapping(value = "/mapping-produce", produces = "text/html")
    public String mappingProduces() {
        log.info("mappingProduces");
        return "ok";
    }
}
