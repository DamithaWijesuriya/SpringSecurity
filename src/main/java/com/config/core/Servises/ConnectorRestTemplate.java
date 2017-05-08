package com.config.core.Servises;

import com.config.core.Interfaces.IConnector;
import com.config.core.ModifiedUrlGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Service
public class ConnectorRestTemplate implements IConnector {
   // private static final Logger logger = LogManager.getLogger(ConnectorRestTemplate.class);
   HashMap<String,Object> result;

    @Autowired
    ModifiedUrlGenerator modifiedUrlGenerator;

    static String testdata;
    static JSONObject jsonObjectn;
    RestTemplate restTemplate = new RestTemplate();
    String urlForGetAllLanguages ="https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20170503T091049Z.71525def9ab833ab.c8ab2a94ef606d3d378564c7e3e1536a56b45e40";

    JSONParser jsonParser = new JSONParser();

    public HashMap getAllLanguagesList() {

        String getAllLanguagesList = restTemplate.getForObject(urlForGetAllLanguages, String.class);

        try {
            JSONObject json = (JSONObject) jsonParser.parse(getAllLanguagesList);
            testdata = json.get("langs").toString();

            //jsonObjectn = (JSONObject) jsonParser.parse(testdata);
            result = new ObjectMapper().readValue(testdata, HashMap.class);

        } catch (Exception e) {
            //logger.error(e.getMessage());
        }
        // logger.info(testdata);


        return result;

    }

    public String getTranslate(String textToTranslate, String fromLanguage, String toLanguage){

        //            String translatorReply = restTemplate.getForObject(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage), String.class);
//            JSONObject json = (JSONObject) parser.parse(translatorReply);

        String sendTranslated = restTemplate.getForObject(modifiedUrlGenerator.modifiedUrl(textToTranslate, fromLanguage, toLanguage), String.class);

//        logger.info(sendTranslated.toString());
        return sendTranslated;
    }

}
