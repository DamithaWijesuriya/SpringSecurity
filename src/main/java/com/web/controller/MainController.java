package com.web.controller;
/*
import com.translator.Translate;*/

import com.config.core.ModifiedUrlGenerator;
import com.config.core.Servises.ConnectorRestTemplate;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@Controller
public class MainController {
    @Autowired
    ConnectorRestTemplate connectorRestTemplate;

    JSONParser jsonParser = new JSONParser();
    /*@Autowired
    Translate translate;
*/
    @RequestMapping(value = {"/", "/welcome**"}, method = RequestMethod.GET)
    public ModelAndView DefaultPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This is default page!");
        model.setViewName("welcome");
        return model;

    }

    @RequestMapping(value = "/admin**", method = RequestMethod.GET)
    public ModelAndView AdminPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_ADMIN only!");
        model.setViewName("admin");

        return model;

    }

    @RequestMapping(value = "/hello**", method = RequestMethod.GET)
    public ModelAndView UserPage() {

        ModelAndView model = new ModelAndView();
        model.addObject("title", "Spring Security Login Form - Database Authentication");
        model.addObject("message", "This page is for ROLE_USER only!");
        model.setViewName("hello");

        return model;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView Login(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username and password!");
        }

        if (logout != null) {
            model.addObject("msg", "You've been logged out successfully.");
        }
        model.setViewName("login");

        return model;

    }

    //for 403 access denied page
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public ModelAndView AccesssDenied() {

        ModelAndView model = new ModelAndView();

        //check if user is login
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!(auth instanceof AnonymousAuthenticationToken)) {
            UserDetails userDetail = (UserDetails) auth.getPrincipal();
            System.out.println(userDetail);

            model.addObject("username", userDetail.getUsername());

        }

        model.setViewName("403/");
        return model;

    }

   /* @Autowired
    @RequestMapping(value = "/translator", method = {RequestMethod.POST, RequestMethod.GET})
    public ModelAndView TranslatePage() {
        ModelAndView model = new ModelAndView();
        model.addObject("languages", translate.getLanguages().getDirs());

        model.setViewName("translator");
        return model;
    }*/
/*
    */@RequestMapping(value = "/translator", method = RequestMethod.GET)
    public ModelAndView sendTranslateView() {
       ModelAndView model = new ModelAndView();
       model.addObject("languages",connectorRestTemplate.getAllLanguagesList());
        // logger.error("test");
        model.setViewName("translator");
       return model;
    }

    /*@RequestMapping(value = "/convert", method = RequestMethod.GET)
    public JSONObject convert(HttpServletRequest request) throws ParseException {
        String textToTranslate = request.getParameter("text");
        String from = request.getParameter("fromLang");
        String to = request.getParameter("toLang");

        JSONObject json = (JSONObject) jsonParser.parse(connectorRestTemplate.getTranslate(textToTranslate, from, to));

    *//*    ModelAndView model = new ModelAndView();
        model.addObject("languages",modifiedUrlGenerator.modifiedUrl(textToTranslate, from, to));
        // logger.error("test");
        model.setViewName("translator");
        *//*
        return json;
    }*/

    @RequestMapping(value = "/convert", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslate(HttpServletRequest request) throws ParseException {

        String fromLanguage = request.getParameter("fromLang");
        String toLanguage = request.getParameter("toLang");
        String textToTranslate = request.getParameter("text");

//        logger.info(fromLanguage+toLanguage+textToTranslate);

        JSONObject reply = (JSONObject) jsonParser.parse(connectorRestTemplate.getTranslate(textToTranslate, fromLanguage, toLanguage));
        return reply;
    }

    @RequestMapping(value = "/sendAllLanguages", method = RequestMethod.GET)
    @ResponseBody
    public JSONObject getTranslated() {
        return (JSONObject) connectorRestTemplate.getAllLanguagesList();
    }
}