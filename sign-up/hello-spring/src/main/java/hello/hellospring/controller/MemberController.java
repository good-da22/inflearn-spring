package hello.hellospring.controller;


import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

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
        //프록시 memberService 확인 가능(AOP 적용 후)
        System.out.println("memberService = " + memberService.getClass());
    }

    @GetMapping("/members/new") //Get 메소드, 주로 조회시
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //Post 메소드, 주로 등록시
    public String creat(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/member_list";
    }
}
