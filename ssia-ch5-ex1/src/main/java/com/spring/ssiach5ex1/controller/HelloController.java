package com.spring.ssiach5ex1.controller;

import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello(){
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication a = context.getAuthentication();

        return "Hello, "+ a.getName()+" !";
    }

    @GetMapping("/hello2")
    public String hello2(Authentication a){
        return "Hello2, "+ a.getName()+" !";
    }

    //SecurityContext -> 객체가 저장되는 보관소로 필요 시 언제든지 Authentication 객체를 꺼내어 쓸 수 있음.
    //ThreadLocal에 저장되어 아무 곳에서나 참조가 가능하도록 설계함
    @GetMapping("/bye")
    @Async // 메서드가 별도의 스레드에서 실행
    public void bye(){
        SecurityContext context = SecurityContextHolder.getContext();
        String username = context.getAuthentication().getName();
        System.out.println(username);
    }

    @GetMapping("/ciao") // GET 요청을 처리하는 컨트롤러 메서드를 정의합니다.
// 객체를 정의하고 별도의 스레드에서 작업 실행
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext(); // 보안 컨텍스트를 가져옵니다.
            return context.getAuthentication().getName(); // 현재 인증된 사용자의 이름을 반환합니다.
        };

        ExecutorService e = Executors.newCachedThreadPool(); // 스레드 풀을 생성합니다.
        try {
            //현재 컨텍스트에 새 스레드 제공
            var contextTask = new DelegatingSecurityContextCallable<>(task);

            // 스레드 풀을 사용하여 작업을 제출하고, 작업이 완료될 때까지 대기하며 인증된 사용자의 이름을 얻어와 문자열을 생성합니다.
            return "ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown(); // 스레드 풀을 종료합니다.
        }
    }

    @GetMapping("/hola")
    public String hola() throws Exception{
        Callable<String> task = ()->{
            SecurityContext context =  SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService e = Executors.newCachedThreadPool();
        e = new DelegatingSecurityContextExecutorService(e);
        try{
            return "hola, " + e.submit(task).get() + " !";
        }finally {
            e.shutdown();
        }
    }
}
