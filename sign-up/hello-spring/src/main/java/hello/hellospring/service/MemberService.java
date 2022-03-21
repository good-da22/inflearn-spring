package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

//서비스는 비지니스에 의존적으로 네이밍, 개발

//@Service //스프링 컨테이너에 서비스 등록
public class MemberService {
//테스트 케이스 단축키 <window> alt + enter
    //private final MemoryMemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

//    @Autowired //스프링 빈으로 등록되어 있어야 작동
    public MemberService(MemberRepository memberRepository) { //외부에서 넣어주도록
        this.memberRepository = memberRepository; //di, dependency injection
    }

    /**
     * 회원 가입
     */
    public Long join(Member member) {
        //같은 이름이 있는 중복 회원 X
        /*
        Optional<Member> result = memberRepository.findByName(member.getName());
        result.ifPresent(m -> { //Optional의 메소드 사용
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
        */
        validateDuplicateMember(member); //중복 회원 검증
        //Extract Method <window> ctrl + alt + m

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> { //바로 사용
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                }); //메소드로 리팩토링
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
