package config;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Value;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * swagger配置
 *
 * @author czy
 * @date 2020/3/29
 */

public class SwaggerConfig {

    protected  ApiInfo apiInfo;

    @Value("${spring.profiles.active}")
    private String environment;

    protected Docket createDocket(String groupName, String basePackage) {
        String host = "";
        new HashMap<>()
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(!"prod".equals(environment))
                .host(host)
                .apiInfo(apiInfo)
                //将时间类型全部转为String类型
                .directModelSubstitute(LocalDateTime.class, String.class)
                //将日期类型全部转为String类型
                .directModelSubstitute(LocalDate.class, String.class)
                .groupName(groupName)
                .select()
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(generateParameters());
    }

    private List<Parameter> generateParameters() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> parameters = Lists.newArrayList();
        parameterBuilder.name("authorization").description("token令牌").modelRef(new ModelRef("String"))
                .parameterType("header").defaultValue("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsInBhc3N3b3JkIjoiJDJhJDEwJEhmOTRUaWtEUkJxMmRpb0JkLy9DSU9IWmlkSWptazBtWHFyUTF5aGNNcUVTZEZ2SmtGdFFXIiwidXNlcl9pZCI6MSwicm9sZV9pZCI6MSwiZXhwIjoxNTUyMTEzODQyfQ.ACw7R1qPs9DN0xhm4gY3vui8B1DR1DvMo-KY9zUJOPXz-_jFY-IYtPCynplNrSA474kmXFHmDdj6EclmMMlMZA")
                .required(true).build();
        parameters.add(parameterBuilder.build());
        return parameters;
    }

}
