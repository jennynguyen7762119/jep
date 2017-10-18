package com.oto.jep.config;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import nz.net.ultraq.thymeleaf.LayoutDialect;

/**
 * 
 * @author thuyntp
 *
 */
@Configurable
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	
	 @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler("/assets/**/*").addResourceLocations("/WEB-INF/assets/");
	    }


	/**
     * ServletContextTemplateResolver initialization.
     * 
     * @return {@link ServletContextTemplateResolver} instance.
     */
	@Bean
	public ServletContextTemplateResolver templateResolver() {
		ServletContextTemplateResolver resolver = new ServletContextTemplateResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix("html");
		resolver.setTemplateMode("HTML5");
		resolver.setCacheable(false);
		return resolver;
	}
	
	/**
     * SpringTemplateEngine.
     * 
     * @return SpringTemplateEngine
     */
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        Set<IDialect> additionalDialects = new HashSet<IDialect>();
        additionalDialects.add(new LayoutDialect());
        templateEngine.setAdditionalDialects(additionalDialects);
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setTemplateEngineMessageSource(messageSource());
        return templateEngine;
    }

    /**
     * Initialize Thymeleaf View Resolver as FIRST {@link ViewResolver} in application.
     * 
     * @return ThymeleafViewResolver instance.
     */
    @Bean
    public ThymeleafViewResolver thymeleafViewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setOrder(0);
        return resolver;
    }
    
    /**
     * Message Source Initialization. (Read messages_{country}.properties). Sample: messaages_ja.properties for
     * Japanese.
     * 
     * @return {@link MessageSource} instance.
     */
    @Bean
    public MessageSource messageSource() {
        Properties properties = new Properties();
        properties.put("classpath:error-message", "UTF-8");
        
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:define-text", "classpath:error-message");
        messageSource.setUseCodeAsDefaultMessage(true);
        messageSource.setDefaultEncoding("UTF-8");
        messageSource.setFileEncodings(properties);
        // # -1 : never reload, 0 always reload
        messageSource.setCacheSeconds(0);
        return messageSource;
    }
}
