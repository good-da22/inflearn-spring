package hello.hellospring.controller;


import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller //컴포넌트 스캔 방식
public class MemberController {

    private final MemberService memberService;
//    @Autowired private MemberService memberService; //필드 주입, 변경힘듬

//    @Autowired
//    public void setMemberService(MemberService memberService) { //setter 주입, final빼고
//        this.memberService = memberService; //public하게 노출 단점
//    }

    @Autowired //스프링 컨테이너에서 연결, 의존관계 주입, 생성자 주입(권장)
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
