package ActivityManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication

public class ActivitymanagerApplication extends SpringBootServletInitializer {
//   用于打war包
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ActivitymanagerApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ActivitymanagerApplication.class, args);
    }

}
