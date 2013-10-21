/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.scheduling.TaskScheduler;

public class MockGPIOService extends GPIOService {

    public MockGPIOService(TaskScheduler taskScheduler) {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Created Mock GPIO Service");

    }
}
