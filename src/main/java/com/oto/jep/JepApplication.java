/**
 * 
 */
package com.oto.jep;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafAutoConfiguration;
import org.springframework.boot.autoconfigure.web.DispatcherServletAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.DispatcherServlet;

import com.sdc.sttb.SpeechToTextApplication;

/**
 * @author thuyntp
 *
 */
@EnableAutoConfiguration(exclude = { ThymeleafAutoConfiguration.class })
@SpringBootApplication
@ComponentScan(basePackages = "com.oto.jep")
@EnableAsync
@Configuration
public class JepApplication extends SpringBootServletInitializer {
	public static void main(String[] args) {
		SpringApplication.run(JepApplication.class, args);
	}
	
	/**
     * Register Customized {@link DispatcherServlet}.
     * @return {@link ServletRegistrationBean}
     */
    @Bean
    public ServletRegistrationBean dispatcherRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(dispatcherServlet());
        registration.addUrlMappings("/*");
        return registration;
    }
    
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(SpeechToTextApplication.class);
    }


    /**
     * Customize {@link DispatcherServlet}.
     * activate throwExceptionIfNoHandlerFound
     * @return DispatcherServlet
     */
    @Bean(name = DispatcherServletAutoConfiguration.DEFAULT_DISPATCHER_SERVLET_BEAN_NAME)
    public DispatcherServlet dispatcherServlet() {
        DispatcherServlet servlet = new DispatcherServlet();
        servlet.setThrowExceptionIfNoHandlerFound(true);
        
        return servlet;
    }
}
