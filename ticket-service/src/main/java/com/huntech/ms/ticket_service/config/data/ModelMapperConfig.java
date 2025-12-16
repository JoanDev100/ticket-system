package com.huntech.ms.ticket_service.config.data;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper defaultMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true)
                .setFieldMatchingEnabled(true);
        return mapper;
    }


}
