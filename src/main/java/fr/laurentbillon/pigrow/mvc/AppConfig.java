/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.mvc;

import fr.laurentbillon.pigrow.service.GPIOService;
import fr.laurentbillon.pigrow.service.MockGPIOService;
import fr.laurentbillon.pigrow.service.MockSerialService;
import fr.laurentbillon.pigrow.service.SerialService;
import fr.laurentbillon.pigrow.service.WiredSerialService;
import fr.laurentbillon.pigrow.service.WiredGPIOService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author lbillon
 */
@Configuration
@EnableWebMvc
@ComponentScan
@EnableScheduling
public class AppConfig extends WebMvcConfigurerAdapter {

    @Bean
    public TaskScheduler taskScheduler() {
        return new ThreadPoolTaskScheduler();
    }

    @Bean
    public GPIOService GPIOService() {
        if (System.getProperty("os.arch").equals("arm")) {
            return new WiredGPIOService(taskScheduler());
        } else {
            return new MockGPIOService(taskScheduler());
        }
    }

    @Bean
    public SerialService SerialService() {
        if (System.getProperty("os.arch").equals("arm")) {
            return WiredSerialService.getInstance(GPIOService());
        } else {
            return new MockSerialService(GPIOService());
        }


    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor pool = new ThreadPoolTaskExecutor();
        pool.setCorePoolSize(5);
        pool.setMaxPoolSize(10);
        pool.setWaitForTasksToCompleteOnShutdown(true);
        return pool;
    }

    @Bean
    public InternalResourceViewResolver getInternalResourceViewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/templates/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
}
