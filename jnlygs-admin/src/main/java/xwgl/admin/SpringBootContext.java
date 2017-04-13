package xwgl.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@ComponentScan(basePackages={"xwgl.**","cmcc.core.sys.service"})
@SpringBootApplication
@EnableAutoConfiguration
@EnableAsync
@EnableJpaRepositories({"xwgl.**.repository"})
@EntityScan("xwgl.**.entity") 
@ImportResource( {"classpath:applicationContext-shiro.xml"} ) 
public class SpringBootContext extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootContext.class);
	}
	
	public static void main(String args[]){
		SpringApplication.run(SpringBootContext.class, args);
	}
}
