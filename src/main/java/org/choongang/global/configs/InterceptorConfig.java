package org.choongang.global.configs;

import lombok.RequiredArgsConstructor;
import org.choongang.global.interceptors.CommonInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {

    //사이트 공통 인터셉터
    private final CommonInterceptor commonInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //공통 적용 - 모든 페이지에 적용할때 ** 설정은 생략 가능함
        registry.addInterceptor(commonInterceptor);
    }
}
