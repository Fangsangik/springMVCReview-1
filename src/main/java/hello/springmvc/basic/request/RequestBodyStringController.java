package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.stream.Stream;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response)
        throws IOException{

        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}" , messageBody);
        response.getWriter().write("ok");
    }

    /**
     * InputStream(Reader): HTTP 요청 메시지 바디의 내용을 직접 조회
     * OutputStream(Writer): HTTP 응답 메시지의 바디에 직접 결과 출력
     */
    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter)
            throws IOException{

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}" , messageBody);
        responseWriter.write("ok");
    }

    //HTTP converter
    @PostMapping("/request-body-string-v3")
    //Http body에 있는 것을 바꿔서 넣어줌
    public HttpEntity<String> requestBodyStringV3 (HttpEntity<String> httpEntity){

        //http Body에 있는 것 꺼내서 사용
        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("messageBody = {}" , messageBody);

        //body message 전달
        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v3_1")
    //Http body에 있는 것을 바꿔서 넣어줌
    public HttpEntity<String> requestBodyStringV3_1 (ResponseEntity<String> httpEntity){

        //http Body에 있는 것 꺼내서 사용
        String messageBody = httpEntity.getBody();
        HttpHeaders headers = httpEntity.getHeaders();
        log.info("messageBody = {}" , messageBody);

        //body message 전달
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody //HTTP 응답코드에 return 값 넣어서 반응
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String message){
        log.info("message ={}", message);
        return "ok";
    }
}
