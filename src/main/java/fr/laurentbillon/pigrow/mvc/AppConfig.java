/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.mvc;

import fr.laurentbillon.pigrow.service.GPIOService;
import fr.laurentbillon.pigrow.service.MockGPIOService;
import fr.laurentbillon.pigrow.service.MockSerialService;
import fr.laurentbillon.pigrow.service.SerialService;
import fr.laurentbillon.pigrow.service.WiredGPIOService;
import fr.laurentbillon.pigrow.service.WiredSerialService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
    public SerialService getSerialService() {
        if (System.getProperty("os.arch").equals("arm")) {
            return WiredSerialService.getInstance(GPIOService());
        } else {
            MockSerialService mockSerialService = new MockSerialService(GPIOService());
            mockSerialService.run();
            return mockSerialService;
        }

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/webjars/**").addResourceLocations("/webjars/");
    }
}
