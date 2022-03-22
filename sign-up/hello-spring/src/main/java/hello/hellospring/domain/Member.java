package hello.hellospring.domain;

import javax.persistence.*;

@Entity //jpa 사용 시 매핑위해
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //db가 자동으로 생성하는 것
    private Long id; //시스템이 저장하는 id

    //@Column(name = "username") //db의 이름을 저장하는 컬럼명이 username일 경우우
   private String name; //이름

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
