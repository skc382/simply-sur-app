package com.simply.sur.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

import java.util.concurrent.TimeUnit;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/webapp/SimplySurFrontEnd/src/app/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("LEGACYHTML5");
        templateResolver.setCacheable(true);
        templateResolver.setCacheTTLMs(3600000L);
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        return templateEngine;
    }

    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        if (!registry.hasMappingForPattern("/.well-known/**")) {
            registry.addResourceHandler("/.well-known/**")
                    .addResourceLocations("classpath:/.well-known/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/webapp/**")) {
            registry.addResourceHandler("/webapp/**")
                    .addResourceLocations("classpath:/webapp/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/resources/**")) {
            registry.addResourceHandler("/resources/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/resources/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/about-page/**")) {
            registry.addResourceHandler("/about-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/about-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/event-card-directive/**")) {
            registry.addResourceHandler("/event-card-directive/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/event-card-directive/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/events-page/**")) {
            registry.addResourceHandler("/events-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/events-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/gallery-page/**")) {
            registry.addResourceHandler("/gallery-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/gallery-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/home-page/**")) {
            registry.addResourceHandler("/home-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/home-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/classes-page/**")) {
            registry.addResourceHandler("/classes-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/classes-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/portfolio-page/**")) {
            registry.addResourceHandler("/portfolio-page/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/portfolio-page/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/services/**")) {
            registry.addResourceHandler("/services/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/services/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }

        if (!registry.hasMappingForPattern("/thumbnail-directive/**")) {
            registry.addResourceHandler("/thumbnail-directive/**")
                    .addResourceLocations("classpath:/webapp/SimplySurFrontEnd/src/app/thumbnail-directive/")
                    .setCacheControl(CacheControl.maxAge(3600000, TimeUnit.MILLISECONDS));
        }
    }
}
