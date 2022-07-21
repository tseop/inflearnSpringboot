package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.annotation.Annotation;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    public void pureContainer() {
        AppConfig appConfig = new AppConfig();

        // 1. 호출 시 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();

        // 2. 호출 시 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();

        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService12 : " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    public void singletonServiceTest() {
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        System.out.println("instance1 : " + instance1);
        System.out.println("instance2 : " + instance2);

        assertThat(instance1).isSameAs(instance2);
    }

    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    public void springContainer() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        // 1. 호출 시 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);

        // 2. 호출 시 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);

        System.out.println("memberService1 : " + memberService1);
        System.out.println("memberService2 : " + memberService2);

        // memberService1 != memberService2
        assertThat(memberService1).isSameAs(memberService2);
    }
}
