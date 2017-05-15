package com.config.core.Interfaces;


import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.HashMap;


public interface IConnector {

    //public JSONObject getAllLanguagesList() throws IOException, SAXException, ParserConfigurationException, ParseException;
    public String  getTranslate(String textToTranslate, String fromLanguage, String toLanguage) throws IOException, ParseException;


    public HashMap<String, String> getAllLanguagesList();

}

