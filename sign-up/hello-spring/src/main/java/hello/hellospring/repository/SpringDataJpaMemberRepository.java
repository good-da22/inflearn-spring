package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {
//스프링 데이터 jpa가 인터페이스에 대한 구현체를 알아서 만들어 준다

    //JPQL select m from Member m where m.name = ?
    //규칙에 따라 메소드 명명
    Optional<Member> findByName(String name);
}
