package com.uberkautilya.productcatalogueservice;

import com.uberkautilya.productcatalogueservice.filters.CustomOrderFilter;
import lombok.AllArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Collections;

@AllArgsConstructor
@SpringBootApplication
public class ProductCatalogueServiceApplication {
    ApplicationContext applicationContext;

    public static void main(String[] args) {
        SpringApplication.run(ProductCatalogueServiceApplication.class, args);
    }

    @Bean
    FilterRegistrationBean<CustomOrderFilter> registrationBean(CustomOrderFilter filter) {
        FilterRegistrationBean<CustomOrderFilter> customOrderFilterRegBean = new FilterRegistrationBean<CustomOrderFilter>(filter);
        customOrderFilterRegBean.setOrder(-1);
        customOrderFilterRegBean.setBeanName("customOrderAppliedBean");
        //This filter will only apply to URLs of this given pattern
        customOrderFilterRegBean.setUrlPatterns(Collections.singleton("/blocked/*"));
        return customOrderFilterRegBean;
    }

}
