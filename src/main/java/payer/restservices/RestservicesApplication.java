package payer.restservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@SpringBootApplication
@EnableCaching
public class RestservicesApplication {
	
	

    public static void main(String[] args) {
        SpringApplication.run(RestservicesApplication.class, args);
    }
    
    
  
}
