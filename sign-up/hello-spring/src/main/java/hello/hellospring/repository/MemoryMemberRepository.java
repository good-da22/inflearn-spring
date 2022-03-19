package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>(); //실무에서는 동시성 문제 발생 가능
    private static long sequence = 0L; //키값 생성, 동시성 고려 X

    @Override
    public Member save(Member member) {
        member.setId(++sequence); //id 세팅
        store.put(member.getId(), member); //store(map)에 저장
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //null이 반환되도 감싸준다
    }

    @Override
    public Optional<Member> findByName(String name) {
       return store.values().stream() //map에서 돌면서 같은 이름이 나오면 반환, 없으면 Optional의 null 반환
               .filter(member -> member.getName().equals(name))
               .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); //store 비우기
    }
}
