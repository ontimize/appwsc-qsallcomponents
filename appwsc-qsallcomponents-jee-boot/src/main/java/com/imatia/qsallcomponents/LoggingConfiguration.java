package com.imatia.qsallcomponents;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;
import org.springframework.security.web.firewall.HttpStatusRequestRejectedHandler;
import org.springframework.security.web.firewall.RequestRejectedHandler;

@Configuration
public class LoggingConfiguration {

    @Bean
    RequestRejectedHandler requestRejectedHandler() {
        return new HttpStatusRequestRejectedHandler();
    }

    @Bean
    public HttpFirewall allowUrlSemicolonHttpFirewall() {
        return new DefaultHttpFirewall();
    }

}
