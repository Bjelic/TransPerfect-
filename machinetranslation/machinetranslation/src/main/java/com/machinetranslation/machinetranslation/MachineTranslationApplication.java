package com.machinetranslation.machinetranslation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@SpringBootConfiguration
public class MachineTranslationApplication {

	public static void main(String[] args) {
		SpringApplication.run(MachineTranslationApplication.class, args);
	}

}
