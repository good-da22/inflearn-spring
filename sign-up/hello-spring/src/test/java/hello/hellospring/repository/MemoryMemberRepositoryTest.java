package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() { //각 테스트 종료시 실행 메모리 db에 저장된 데이터 삭제
        repository.clearStore(); //테스트를 독립적으로 실행하게, 테스트 순서 보장X, 순서 의존관계 없어야 한다.
    }
    
    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get(); //Optional로 매개변수 저장해서 get을 통해 가져온다
        //System.out.println("result = " + (result == member));
        //Assertions.assertEquals(member, result); //juint api, 기대값 member와 result를 비교, 성공시 통과, 실패시 에러 메세지
        Assertions.assertThat(member).isEqualTo(result); //assertj.core.api
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);

    }
}
