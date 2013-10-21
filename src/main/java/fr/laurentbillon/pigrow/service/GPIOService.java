/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.service;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

/**
 *
 * @author lbillon
 */
public abstract class GPIOService {

    LightingState lightingState;
    final LightingState autoLightingState;
    final LightingState forceLightingOnState;
    final LightingState forceLightingOffState;
    protected int ambientLightingValue;
    protected int autoLightingThreshold = 600;

    public LightingState getLightingState() {
        return lightingState;
    }

    public final void setLightingState(LightingState lightingState) {
        this.lightingState = lightingState;
        Logger.getLogger(GPIOService.class.getName()).info("Lighting state is now : " + getLightingState().getStateName());
        getLightingState().execute();
    }

    public final LightingState getAutoLightingState() {
        return autoLightingState;
    }

    public final LightingState getForceLightingOnState() {
        return forceLightingOnState;
    }

    public final LightingState getForceLightingOffState() {
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

    public void forceState(String state) throws Exception {
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
            default:
                throw new Exception("Unknown GPIOController State");
                    
        }
    }

    void setLeds(boolean redLedOn, boolean greenLedOn) {
        if (redLedOn) {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Red led is now ON");
        } else {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Red led is now OFF");
        }

        if (greenLedOn) {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Green led is now ON");
        } else {
            Logger.getLogger(GPIOService.class.getName()).log(Level.INFO, "Green led is now OFF");
        }

    }

    public void transitionToNextState() {
        this.getLightingState().transitionToNextState();
    }
}
