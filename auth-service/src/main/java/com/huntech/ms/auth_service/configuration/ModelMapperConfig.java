package com.huntech.ms.auth_service.configuration;

import com.huntech.ms.auth_service.model.User;
import com.huntech.ms.auth_service.model.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.getConfiguration()
                .setSkipNullEnabled(true)
                .setAmbiguityIgnored(true)
                .setFieldMatchingEnabled(true);

        mapper.createTypeMap(User.class, UserDTO.class)
                .addMapping(u -> u.getRole().getName(), UserDTO::setRole);
        return mapper;
    }

/*    @Bean("roleMapper")
    public ModelMapper roleMapper() {
        ModelMapper mapper = new ModelMapper();

        mapper.createTypeMap(User.class, UserDTO.class)
                .addMapping(u -> u.getRole().getName(), UserDTO::setRole);

        return mapper;
    }*/
}

