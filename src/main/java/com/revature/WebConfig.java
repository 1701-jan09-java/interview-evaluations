package com.revature;

import java.time.LocalDate;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger documentation url: http://localhost:8080/swagger-ui.html
 * 
 * 
 * @author tjkemper
 *
 */
@Configuration
@EnableSwagger2
public class WebConfig extends WebMvcConfigurerAdapter {
	
	/**
	 * Swagger 2 configuration
	 */
	public Docket mainConfig() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any())
				.build()
				.directModelSubstitute(LocalDate.class, String.class)
				.genericModelSubstitutes(ResponseEntity.class)
				;
	}
	
}
