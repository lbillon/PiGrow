/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.service;

/**
 *
 * @author lbillon
 */
public interface LightingState {

    public void transitionToNextState();

    public void setAmbientLightingValue(int lightingValue);

    public void execute();

    public boolean isLampOn();

    public String getStateName();
}

class AutoLightingState implements LightingState {

    GPIOService service;
    boolean isLampOn = false;

    public AutoLightingState(GPIOService service) {
        this.service = service;
    }

    @Override
    public void transitionToNextState() {
        service.setLightingState(service.getForceLightingOnState());
    }

    @Override
    public void setAmbientLightingValue(int lightingValue) {

        if (lightingValue > service.getAutoLightingThreshold()) {
            service.lightsOn();
            this.isLampOn = true;
        }
        if (lightingValue < service.getAutoLightingThreshold() - 70) {
            service.lightsOff();
            this.isLampOn = false;
        }

    }

    @Override
    public void execute() {
        service.setLeds(true, true);
    }

    @Override
    public boolean isLampOn() {
        return isLampOn;
    }

    @Override
    public String getStateName() {
        return "auto";
    }
}

class ForceLightingOnState implements LightingState {

    GPIOService service;

    public ForceLightingOnState(GPIOService service) {
        this.service = service;
    }

    @Override
    public void transitionToNextState() {
        service.setLightingState(service.getForceLightingOffState());
    }

    @Override
    public void setAmbientLightingValue(int lightingValue) {
    }

    @Override
    public void execute() {
        service.setLeds(true, false);
        service.lightsOn();
    }

    @Override
    public boolean isLampOn() {
        return true;
    }

    @Override
    public String getStateName() {
        return "on";
    }
}

class ForceLightingOffState implements LightingState {

    GPIOService service;

    public ForceLightingOffState(GPIOService service) {
        this.service = service;
    }

    @Override
    public void transitionToNextState() {
        service.setLightingState(service.getAutoLightingState());
    }

    @Override
    public void setAmbientLightingValue(int lightingValue) {
    }

    @Override
    public void execute() {
        service.setLeds(false, true);
        service.lightsOff();
    }

    @Override
    public boolean isLampOn() {
        return false;
    }

    @Override
    public String getStateName() {
        return "off";
    }
}
