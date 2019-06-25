package myssm.config;


import myssm.conventer.StringToDateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;

@ComponentScan(basePackages = "myssm.controller")
@EnableWebMvc
public class MvcConfig implements WebMvcConfigurer {


    //取出后添加converter再注册回容器
    @Autowired
    ConfigurableConversionService configurableConversionService;

    //添加converter
    @PostConstruct
    public void addConverter(){
        configurableConversionService.addConverter(new StringToDateConverter());
    }

    //放回
    @Bean
    @Primary
    public ConfigurableConversionService configurableConversionService(){
        return configurableConversionService;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**").addResourceLocations("/WEB-INF/upload/");
        //registry.addResourceHandler("/pic2/**").addResourceLocations("classpath:/jpg/");
        //registry.addResourceHandler("/pic3/**").addResourceLocations("file:e://jpg/");
    }


}
