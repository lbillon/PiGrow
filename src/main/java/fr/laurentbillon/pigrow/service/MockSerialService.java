package fr.laurentbillon.pigrow.service;

import java.util.logging.Level;
import java.util.logging.Logger;

public class MockSerialService extends SerialService {

    @Override
    public void close() {
    }

    public MockSerialService(GPIOService gPIOService) {
        this.gPIOService = gPIOService;
        MockAmbientLightingSensorThread mockAmbientLightingSensorThread = new MockAmbientLightingSensorThread(gPIOService);
        mockAmbientLightingSensorThread.start();
    }

    private class MockAmbientLightingSensorThread extends Thread {

        private final GPIOService gPIOService;

        public MockAmbientLightingSensorThread(GPIOService gPIOService) {
            this.gPIOService = gPIOService;
        }

        @Override
        public void run() {
            try {
                while (true) {
                    gPIOService.setAmbientLightingValue(100);
                    Thread.sleep(5000);
                    gPIOService.setAmbientLightingValue(900);
                    Thread.sleep(5000);
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(MockSerialService.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
