package ApiAgenda.demoAgenda;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

import ApiAgenda.demoAgenda.Config.CorsConfig;

@SpringBootApplication
@Import(CorsConfig.class)
public class DemoAgendaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoAgendaApplication.class, args);
	}

}
