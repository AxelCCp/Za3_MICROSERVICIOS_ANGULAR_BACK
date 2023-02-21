package ms.courses.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EntityScan({"ms.courses.server.models.entity","ms.students.commons.models.entity"})
@EnableEurekaClient
@SpringBootApplication
public class MsCoursesServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCoursesServerApplication.class, args);
	}

}
