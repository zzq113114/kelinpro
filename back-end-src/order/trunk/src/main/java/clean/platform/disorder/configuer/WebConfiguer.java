package clean.platform.disorder.configuer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自定义配置类
 *
 * @author ZX
 * @create 2020-03-10 2:21
 **/

@Configuration
//@JsonComponent
public class WebConfiguer  {

    private Logger logger = LogManager.getLogger(getClass());

//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//
//        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
//        ObjectMapper objectMapper = new ObjectMapper();
//        SimpleModule simpleModule = new SimpleModule();
//        simpleModule.addSerializer(BigInteger.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
//        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        objectMapper.registerModule(simpleModule);
//        jackson2HttpMessageConverter.setObjectMapper(objectMapper);
//        converters.add(jackson2HttpMessageConverter);
//        converters.add(new StringHttpMessageConverter(Charset.forName("UTF-8")));
//    }

//    @Bean
//    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder build){
//        logger.info("---------------------create Long to String Mapper-----------------------------");
//        ObjectMapper objectMapper = build.createXmlMapper(false).build();
//        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
//        SimpleModule module = new SimpleModule();
//        module.addSerializer(Long.class, ToStringSerializer.instance);
//        module.addSerializer(Long.TYPE, ToStringSerializer.instance);
//        module.addSerializer(BigInteger.class, ToStringSerializer.instance);
//        objectMapper.registerModule(module);
//        return objectMapper;
//    }

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
//
//        Jackson2ObjectMapperBuilderCustomizer build = new Jackson2ObjectMapperBuilderCustomizer(){
//            @Override
//            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
//                jacksonObjectMapperBuilder.serializerByType(Long.TYPE, ToStringSerializer.instance);
//            }
//        };
//        return build;
//    }

    @Bean
    public UIDGenerater createIdGenerater(){
        logger.info("--------------创建分布式ID生成器---------------------");
        UIDGenerater generater = new UIDGenerater(1,1);
        return generater;
    }
}
