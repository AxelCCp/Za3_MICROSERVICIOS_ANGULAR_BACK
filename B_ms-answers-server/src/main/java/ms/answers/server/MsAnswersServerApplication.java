package ms.answers.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"ms.answers.server.models.entity","ms.students.commons.models.entity","ms.exams.commons.models.entity"})
public class MsAnswersServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsAnswersServerApplication.class, args);
	}

}
