package com.web.controller;

import com.config.core.Servises.ConnectorRestTemplate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TranslatorController {
    
    JSONParser jsonParser = new JSONParser();
    
    @Autowired
    ConnectorRestTemplate connectorRestTemplate;

    @RequestMapping(value = "/translator", method = RequestMethod.GET)
    public ModelAndView sendTranslateView() {
        ModelAndView model = new ModelAndView();
        model.addObject("languages",connectorRestTemplate.getAllLanguagesList());
        model.setViewName("translator");
        return model;
    }

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslate(HttpServletRequest request) throws ParseException {

        String fromLanguage = request.getParameter("fromLang");
        String toLanguage = request.getParameter("toLang");
        String textToTranslate = request.getParameter("text");
        String translatedReplyJson = connectorRestTemplate.getTranslate(textToTranslate, fromLanguage, toLanguage);
        JSONObject reply = (JSONObject) jsonParser.parse(translatedReplyJson);
        return reply;
    }

    @RequestMapping(value = "/sendAllLanguages", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslated() {
        return (JSONObject) connectorRestTemplate.getAllLanguagesList();
    }
}
