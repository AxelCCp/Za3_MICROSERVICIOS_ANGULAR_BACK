package ms.students.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EnableEurekaClient
@SpringBootApplication
@EntityScan({"ms.students.commons.models.entity"})	
public class MsStudentsServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsStudentsServerApplication.class, args);
	}

}
