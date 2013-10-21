/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.support.CronTrigger;

/**
 *
 * @author lbillon
 */
public class WiredGPIOService extends GPIOService {

    public GpioController gpioController;
    public GpioPinDigitalOutput lamp;
    public GpioPinDigitalOutput pump;
    private GpioPinDigitalInput button;
    private GpioPinDigitalOutput greenLed;
    private GpioPinDigitalOutput redLed;

    public WiredGPIOService(TaskScheduler taskScheduler) {
        super();
        gpioController = GpioFactory.getInstance();
        lamp = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_07, "MyLamp", PinState.LOW);
        greenLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_05, "MyLamp", PinState.LOW);
        redLed = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_02, "MyLamp", PinState.LOW);
        pump = gpioController.provisionDigitalOutputPin(RaspiPin.GPIO_09, "MyPump", PinState.LOW);
        button = gpioController.provisionDigitalInputPin(RaspiPin.GPIO_04, PinPullResistance.PULL_UP);
        // create and register gpio pin listener
        ButtonListener bl = new ButtonListener(this);
        button.addListener(bl);
        taskScheduler.schedule(new WateringTask(this), new CronTrigger("0 0 */1 * * *"));


    }

    @Override
    public void blinkLed() {
        greenLed.pulse(1000);
    }

    @Override
    public void water() {
        super.water();
        pump.pulse(10000);
    }

    @Override
    public void lightsOn() {
        if (lamp.getState().isLow()) {
            super.lightsOn();
            lamp.high();
        }
    }

    @Override
    public void lightsOff() {
        if (lamp.getState().isHigh()) {
            super.lightsOff();
            lamp.low();
        }
    }

    @Override
    public void setLeds(boolean redLedOn, boolean greenLedOn) {
        super.setLeds(redLedOn, greenLedOn);

        if (redLedOn) {
            this.redLed.high();
        } else {
            this.redLed.low();
        }

        if (greenLedOn) {
            this.greenLed.high();
        } else {
            this.greenLed.low();
        }

    }

    @Override
    public boolean isLampOn() {
        return lamp.isHigh();
    }

    private static class WateringTask implements Runnable {

        private final WiredGPIOService service;

        public WateringTask(WiredGPIOService service) {
            this.service = service;
        }

        @Override
        public void run() {
            service.water();
        }
    }

    private static class ButtonListener implements GpioPinListenerDigital {

        private final GPIOService service;

        public ButtonListener(GPIOService service) {
            this.service = service;
        }

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            if (event.getState().isLow()) {
                service.transitionToNextState();
            }
        }
    }
}
