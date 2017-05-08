/*
package com.translator;

//import com.google.gson.Gson;
import javassist.bytecode.stackmap.TypeData;
import jdk.nashorn.internal.parser.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.WebUtils;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.logging.Logger;


*/
/**
 * Created by hsenid on 5/4/17.
 *//*

@Service
public class Translate {
    private static final Logger logger = Logger.getLogger( TypeData.ClassName.class.getName() );

    static final String PostUrl = "https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20170503T091519Z.9f30c24402100dfb.91f7ddaca07e07cddb27fd1cd769dd2b43d5c765";
    public ArrayList<String> languages;
    //Gson gson = new Gson();
    Languages lang;
    Letters convertedWord;

*/
/*

    RestTemplate restTemplate = new RestTemplate();
   String quote = restTemplate.getForObject("https://translate.yandex.net/api/v1.5/tr/getLangs?key=trnsl.1.1.20170503T091519Z.9f30c24402100dfb.91f7ddaca07e07cddb27fd1cd769dd2b43d5c765", String.class);
    JSONParser parser = new JSONParser();
    JSONObject json = (JSONObject) parser.parse(quote);
*//*



    public Languages getLanguages() {
        try {

            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/getLangs?ui=en&key=trnsl.1.1.20170503T091049Z.71525def9ab833ab.c8ab2a94ef606d3d378564c7e3e1536a56b45e40");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            //lang = gson.fromJson(br.readLine(), Languages.class);
            conn.disconnect();

        } catch (Exception e) {
            logger.info(String.valueOf(e));
        }
        return lang;
    }

    public Letters translate(String inputLang, String outputLang, String wordToConvert) {
        try {

            URL url = new URL("https://translate.yandex.net/api/v1.5/tr.json/translate ?key=trnsl.1.1.20170503T091519Z.9f30c24402100dfb.91f7ddaca07e07cddb27fd1cd769dd2b43d5c765&lang" +
                    inputLang + "-" + outputLang + "&text=" + wordToConvert);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            //convertedWord = gson.fromJson(br.readLine(), Letters.class);
            conn.disconnect();

        } catch (Exception e) {
            logger.info(String.valueOf(e));
        }
        return convertedWord;
    }
}
*/
