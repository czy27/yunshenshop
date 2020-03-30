package com.yunshen.shop.config;

import config.SwaggerConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * swagger配置
 *
 * @author czy
 * @date 2020/3/29
 */
@Configuration
@EnableSwagger2
public class PaymentSwaggerConfig extends SwaggerConfig {

    {
        apiInfo = apiInfo();
    }

    @Bean(value = "base")
    public Docket baseAPI() {
        return createDocket("通用管理", "com.zhehekeji.neobio.admin.controller.base");
    }



    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("云深数智")
                .description("云深数智后台API文档")
                .version("1.0")
                .build();
    }

}
