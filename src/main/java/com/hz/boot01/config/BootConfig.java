package com.hz.boot01.config;

import com.hz.boot01.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Configuration
public class BootConfig {

    /*@Value("${uname}")*/
    public String uname;


    @Bean
    public User newUser(){
        User user = new User();

        System.out.println("uname==="+uname);
        user.setUserName(uname);
        return user;
    }

}
