package com.juno.spring.config;

import com.juno.spring.annotation.V1;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.method.HandlerTypePredicate;
import org.springframework.web.servlet.config.annotation.AsyncSupportConfigurer;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.parameterName("contentType")
                .favorParameter(true)
                .ignoreAcceptHeader(true)
                .defaultContentType(MediaType.APPLICATION_JSON) // 기본 설정은 json
                .mediaType("json", MediaType.APPLICATION_JSON)  // json인 경우에는 json
                .mediaType("xml", MediaType.APPLICATION_XML)    // xml인 경우에는 xml
        ;
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer
                .setUseTrailingSlashMatch(true) // true : `/v1/test` == `/v1/test/`
                .addPathPrefix("/api/v1", HandlerTypePredicate.forAnnotation(V1.class))
        .setPathMatcher(new AntPathMatcher())   // @RequestMapping과 사용자 요청에 대한 url 매칭
        .setUrlPathHelper(new UrlPathHelper())  // @PathVariable 값을 처리하는데 사용
        ;
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setThreadNamePrefix("Async");  // 쓰레드 풀 이름
        pool.setCorePoolSize(50);   // 기본 쓰레드 개수 = 50
        pool.setMaxPoolSize(100);   // 최대 쓰레드 개수 = 100
        pool.setQueueCapacity(300); // 대기 쓰레드 개수 = 300까지 대기 가능
        pool.initialize();

        configurer.setTaskExecutor(pool);       // 비동기를 위해 사용될 쓰레드 등록
        configurer.setDefaultTimeout(10_000L);  // _ = , 이라고 생각하면 쉽다. 그냥 구분자
    }
}
