/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.service;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lbillon
 */
public abstract class GPIOService {

    LightingState lightingState;
    LightingState autoLightingState;
    LightingState forceLightingOnState;
    LightingState forceLightingOffState;
    protected int ambientLightingValue;
    protected boolean autoLighting = false;
    protected int autoLightingThreshold = 600;

    public LightingState getLightingState() {
        return lightingState;
    }

    public final void setLightingState(LightingState lightingState) {
        this.lightingState = lightingState;
        Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Lighting state is now : {0}", getLightingState().getStateName());
        getLightingState().execute();
    }

    public LightingState getAutoLightingState() {
        return autoLightingState;
    }

    public LightingState getForceLightingOnState() {
        return forceLightingOnState;
    }

    public LightingState getForceLightingOffState() {
        return forceLightingOffState;
    }

    public GPIOService() {
        this.autoLightingState = new AutoLightingState(this);
        this.forceLightingOnState = new ForceLightingOnState(this);
        this.forceLightingOffState = new ForceLightingOffState(this);
        this.lightingState = this.getAutoLightingState();

    }

    public void water() {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Watering...");
    }

    public void lightsOn() {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Lights On...");
    }

    public void lightsOff() {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Lights Off...");
    }

    public int getAmbientLightingValue() {
        return ambientLightingValue;
    }

    public void setAmbientLightingValue(int lightingValue) {
        this.ambientLightingValue = lightingValue;
        this.getLightingState().setAmbientLightingValue(lightingValue);
    }

    public void setAutoLighting(boolean autoLighting) {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Autolighting set to " + String.valueOf(autoLighting));
        this.autoLighting = autoLighting;
    }

    public int getAutoLightingThreshold() {
        return autoLightingThreshold;
    }

    public void setAutoLightingThreshold(int autoLightingThreshold) {
        Logger.getLogger(WiredGPIOService.class.getName()).log(Level.INFO, "Set autolighting threshold to " + String.valueOf(autoLightingThreshold));
        this.autoLightingThreshold = autoLightingThreshold;
    }

    public boolean isLampOn() {
        return getLightingState().isLampOn();
    }

    public void blinkLed() {
        Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Blink !");
    }

    public void forceState(String state) {
        switch (state) {
            case "on":
                this.setLightingState(this.getForceLightingOnState());
                break;
            case "off":
                this.setLightingState(this.getForceLightingOffState());
                break;
            case "auto":
                this.setLightingState(this.getAutoLightingState());
                break;
        }
    }

    void setLeds(boolean redLedOn, boolean greenLedOn) {
        if (redLedOn) {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Red led is now ON");
        } else {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Red led is now OFF");
        }

        if (greenLedOn) {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Green led is now ON");;
        } else {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Green led is now OFF");
        }

    }

    public void transitionToNextState() {
        this.getLightingState().transitionToNextState();
    }
}
