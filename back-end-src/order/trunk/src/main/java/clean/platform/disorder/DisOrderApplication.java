package clean.platform.disorder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//@SpringBootApplication
//public class DisOrderApplication extends SpringBootServletInitializer {
//
//    private static final Logger logger = LogManager.getLogger(DisOrderApplication.class);
//
//    @Override
//    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//        return builder.sources(DisOrderApplication.class);
//    }
//
//    public static void main(String[] args) {
//
//        logger.info("-----------启动服务---------------");
//        SpringApplication.run(DisOrderApplication.class, args);
//    }
//
//}
@SpringBootApplication
public class DisOrderApplication  {

    private static final Logger logger = LogManager.getLogger(DisOrderApplication.class);


    public static void main(String[] args) {

        logger.info("-----------启动服务---------------");
        SpringApplication.run(DisOrderApplication.class, args);
    }

}

