package ms.exams.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"ms.exams.commons.models.entity"})   
public class MsExamsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsExamsServerApplication.class, args);
	}

}
