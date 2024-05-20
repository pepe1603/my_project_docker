package ApiAgenda.demoAgenda.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		// TODO Auto-generated method stub
		registry.addMapping("/**")
			//.allowedOrigins("http://frontend_service:5175") //para entonros dentro de docker
			.allowedOrigins("http://localhost:5175")
			.allowedMethods("GET","POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
			.allowedHeaders("*")
			.allowCredentials(true);
	}

}
