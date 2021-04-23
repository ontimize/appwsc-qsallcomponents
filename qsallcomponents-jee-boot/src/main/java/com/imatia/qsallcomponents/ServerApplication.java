package com.imatia.qsallcomponents;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan({ "com.ontimize.jee.server.services.preferences",
//    "com.ontimize.jee.webclient.remoteconfiguration"})
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

}
