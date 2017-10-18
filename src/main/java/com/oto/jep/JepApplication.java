/**
 * 
 */
package com.oto.jep;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author thuyntp
 *
 */
@SpringBootApplication
@ComponentScan(basePackages = "com.oto.jep")
@Configurable
public class JepApplication {
	public static void main(String[] args) {
		SpringApplication.run(JepApplication.class, args);
	}
}
