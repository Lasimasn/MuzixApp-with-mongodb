package com.stackroute.muzix.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
//@PropertySource(value="resources/application.properties")
@EnableSwagger2
//@Component
public class Swagger2Config {
//    @Autowired
//    Environment ev;
//    @Bean
//    public void execute()
//    {
//        String attr=this.ev.getProperty("ds.type");
//    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors
                        .basePackage("com.stackroute"))
                .paths(PathSelectors.regex("/api/v1/.*"))
                .build().apiInfo(appInfo());
    }
    private ApiInfo appInfo() {
        return new ApiInfo("Muzix API(playmusic)","Spring boot Application for sound tracks","1.0","Terms Of Service","Muzix aka playmusic","Apache License Version 2.0","https://www.apache.org/license.html");
    }
}

