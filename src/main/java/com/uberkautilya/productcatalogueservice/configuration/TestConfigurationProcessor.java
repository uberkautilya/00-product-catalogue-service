package com.uberkautilya.productcatalogueservice.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Setter
@Getter
@ConfigurationProperties("com.uberkautilya")
@Component
public class TestConfigurationProcessor {
    private String name;
    private Integer number;

    private Long value;
}
