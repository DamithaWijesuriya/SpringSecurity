/*
package com.web.controller;

import com.config.core.Servises.ConnectorRestTemplate;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TranslatorController {
    // private static final Logger logger = LogManager.getLogger(TranslatorController.class);

    @Autowired
    ConnectorRestTemplate connectorRestTemplate;

    @RequestMapping(value = "/translate", method = RequestMethod.GET)
    public String sendTranslateView(ModelMap model) {
        // logger.error("test");
        return "translate";
    }

    @RequestMapping(value = "/sendAllLanguages", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslated() {
        return (JSONObject) connectorRestTemplate.getAllLanguagesList();
    }
}
*/
