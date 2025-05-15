package com.example.mbboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication // @Controller, Service, Mapper 자동스캔 후 bean 등록 
@ServletComponentScan // @WebServlet, Listener 자동스캔 후 bean 등록
public class MbboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(MbboardApplication.class, args);
	}

}
