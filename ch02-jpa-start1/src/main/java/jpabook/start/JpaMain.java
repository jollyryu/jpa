package jpabook.start;

import javax.persistence.*;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

        	//test
            tx.begin(); //트랜잭션 시작
            logic(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void logic(EntityManager em) {

        Member memberA = new Member();
        memberA.setId("1");
        memberA.setUsername("가");
        memberA.setAge(10);
        
        Member memberB= new Member();
        memberB.setId("2");
        memberB.setUsername("나");
        memberB.setAge(20);
        
        em.persist(memberA);
        em.persist(memberB);
//        
//        em.find(Member.class, "1");
        
    	String id = "id1";
        Member member = new Member();
        member.setId(id);
        member.setUsername("지한");
        member.setAge(2);
        
        em.persist(member);
        
        member.setAge(20);
        
//       Member findMember = em.find(Member.class, id);
//       System.out.println("fMember=" + findMember.getUsername() + ", age=" + findMember.getAge());

       List<Member> members = em.createQuery("select m from Member m", Member.class).getResultList();
       
       System.out.println("members.size=" + members.size());
//       
//       System.out.println(members.get(0).getId());
        
//       em.remove(member);
      

    }
    
}