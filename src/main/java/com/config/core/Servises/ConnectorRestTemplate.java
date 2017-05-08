package com.config.core.Servises;

import com.config.core.Interfaces.IConnector;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectorRestTemplate implements IConnector {
   // private static final Logger logger = LogManager.getLogger(ConnectorRestTemplate.class);

    static String test;
    static JSONObject sendJson;
    RestTemplate restTemplate = new RestTemplate();
    String UrlForGetAllLanguages =
            "https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20170503T091049Z.71525def9ab833ab.c8ab2a94ef606d3d378564c7e3e1536a56b45e40";

    JSONParser parser = new JSONParser();

    public JSONObject getAllLanguagesList() {

        String getAllLanguagesList = restTemplate.getForObject(UrlForGetAllLanguages, String.class);

        try {
            JSONObject json = (JSONObject) parser.parse(getAllLanguagesList);

            test = json.get("langs").toString();
            sendJson = (JSONObject) parser.parse(test);
        } catch (ParseException e) {
            //logger.error(e.getMessage());
        }
       // logger.info(test);

        return sendJson;

    }

}
