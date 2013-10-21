/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.laurentbillon.pigrow.mvc;

import fr.laurentbillon.pigrow.service.GPIOService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private GPIOService service;

    public BaseController() {
        System.out.println("fuck");

    }

    @RequestMapping(value = "/nextlightingstate.do", method = RequestMethod.GET)
    @ResponseBody
    public void nextLightingState() {
        service.transitionToNextState();
    }

    @RequestMapping(value = "/autolighting.do", method = RequestMethod.POST)
    @ResponseBody
    public void setAutoLightingThreshold(@RequestParam("t") int threshold) {
        service.setAutoLightingThreshold(threshold);
    }

    @RequestMapping(value = "/status.do", method = RequestMethod.GET)
    @ResponseBody
    public LightingInfo status() {
        return new LightingInfo(service.isLampOn(), service.getLightingState().getStateName(), service.getAmbientLightingValue(), service.getAutoLightingThreshold());
    }

    @RequestMapping(value = "/water.do", method = RequestMethod.GET)
    @ResponseBody
    public void water() {
        service.water();
    }

    @RequestMapping(value = "/forcestate.do", method = RequestMethod.GET)
    @ResponseBody
    public void forceState(@RequestParam("state") String state) {
        service.forceState(state);
    }

    private static class LightingInfo {

        public final boolean lighting;
        public final String lightingState;
        public final int ambientLighting;
        public final int autoLightingThreshold;

        public LightingInfo(boolean lighting, String lightingState, int ambientLighting, int autoLightingThreshold) {
            this.lighting = lighting;
            this.lightingState = lightingState;
            this.ambientLighting = ambientLighting;
            this.autoLightingThreshold = autoLightingThreshold;
        }
    }
}
