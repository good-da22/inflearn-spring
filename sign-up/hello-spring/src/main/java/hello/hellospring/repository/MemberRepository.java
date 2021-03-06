package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

//Repository에서 데이터를 저장

public interface MemberRepository {
    Member save(Member member); //저장소에 저장
    Optional<Member> findById(Long id); //id로 찾기
    Optional<Member> findByName(String name); //name으로 찾기
    List<Member> findAll(); //저장된 모든 회원 리스트 찾기

}
