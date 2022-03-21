package hello.hellospring;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration //자바 코드로 스프링 빈 등록
//기존 @Service, @Repository, @Autowired 삭제필요
//Controller는 유지
public class SpringConfig {

    private DataSource dataSource;

    @Autowired
    public SpringConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() { //인터페이스를 두고 구현체 바꾸기, 기존 코드 수정 필요 없음!
        //다형성을 활용, 개방-폐쇄 원칙(OCP) 기존 코드를 손대지 않고 설정만으로 구현 클래스 변경 가능
//        return new MemoryMemberRepository();
        return new JdbcMemberRepository(dataSource); //Config 파일 변경 만으로 재구성 가능
    }
}
