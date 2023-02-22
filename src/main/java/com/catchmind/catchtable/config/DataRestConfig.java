package com.catchmind.catchtable.config;

import com.catchmind.catchtable.domain.Profile;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

@Configuration // 설정이라는 것을 알려주기 위한 어노테이션
public class DataRestConfig {
    // data rest 기본 설정은 id를 감추는 것인데, 회원 계정에 한해서 userId가 노출되게 함

    @Bean   // 빈이라는 것을 알려주기 위한 어노테이션
    public RepositoryRestConfigurer repositoryRestConfigurer(){
        return RepositoryRestConfigurer.withConfig((config, cors) ->
            config.exposeIdsFor(Profile.class)
        );
        // Bean과 Configuration의 경우 같이 쓰는 경우가 많다
        // 이는 자바에서 Configuration을 등록하면 자동으로 그 클래스를 빈으로 등록하고 @Bean으로 빈을 찾을 수 있게 해주기때문
        // 따라서 @Bean을 쓰는 클래스에는 @Configuration을 써주어야한다.
    }
}
