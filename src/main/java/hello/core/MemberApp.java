package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // Spring은 모든게 ApplicationContext로 시작함. Spring Container임. @Bean이 있는 모든 클래스를 관리함
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); // AppConfig에 있는 @Bean 붙은 클래스들을 Spring Container에 넣어 관리함
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "member A", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findById(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("find findMember = " + findMember.getName());
    }
}
