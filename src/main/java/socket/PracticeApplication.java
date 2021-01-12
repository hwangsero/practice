package socket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class PracticeApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/practice/real-application.yml";
	public static final String AWS_S3_IMAGE_KEY = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/app/config/practice/aws-s3-image-key.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(PracticeApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.properties(AWS_S3_IMAGE_KEY)
				.run(args);
	}

}
