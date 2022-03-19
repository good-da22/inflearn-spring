package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "hello!!");
        return "hello";
    }

    @GetMapping("hello-mvc") //mvc 컨텐츠 접근, hello-mvc?name=
    public String helloMvc(@RequestParam(name = "name") String name, Model model){ //외부에서 파라미터 받기
        model.addAttribute("name", name);
        return "hello-template"; //뷰 이름
    }

    @GetMapping("hello-string") //API 방식, 문자만 실사용은 거의 없음
    @ResponseBody //http body에 데이터를 직접 주겠다, 단순 문자는 StringConverter
    public String helloString(@RequestParam("name") String name) {
        return "hello " +name; //데이터를 그대로 내려준다.
    }

    @GetMapping("hello-api") //json 방식 넘겨주기, 주로 사용
    @ResponseBody //http 응답에 바로 반응, 객체가 오면 json 방식으로, JsonConverter
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
